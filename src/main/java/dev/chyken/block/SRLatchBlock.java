package dev.chyken.block;

import com.mojang.serialization.MapCodec;
import dev.chyken.block.state.properties.SRLatchPart;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nullable;

public class SRLatchBlock extends HorizontalDirectionalBlock {
    public static final MapCodec<SRLatchBlock> CODEC = simpleCodec(SRLatchBlock::new);
    public static final EnumProperty<SRLatchPart> PART;
    public static final BooleanProperty POWERED;

    public MapCodec<SRLatchBlock> codec() {
        return CODEC;
    }

    public SRLatchBlock(Properties properties) {
        super(properties.instabreak().sound(SoundType.STONE).pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(POWERED, Boolean.valueOf(false))
                        .setValue(PART, SRLatchPart.SECONDARY)
        );
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, PART);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection();
        BlockPos secondaryPos = context.getClickedPos();
        BlockPos primaryPos = secondaryPos.relative(direction.getClockWise());
        Level level = context.getLevel();

        if (level.getBlockState(primaryPos).canBeReplaced(context) && level.getWorldBorder().isWithinBounds(primaryPos)) {
            return this.defaultBlockState()
                    .setValue(FACING, direction)
                    .setValue(PART, SRLatchPart.SECONDARY)
                    .setValue(POWERED, false);
        }
        return null;
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            BlockPos primaryPos = pos.relative(state.getValue(FACING).getClockWise());

            level.setBlock(primaryPos, state.setValue(PART, SRLatchPart.PRIMARY), 3);
            level.blockUpdated(pos, Blocks.AIR);
            level.blockUpdated(primaryPos, Blocks.AIR);
        }
    }

    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && player.isCreative()) {
            SRLatchPart latchpart = (SRLatchPart)state.getValue(PART);
            if (latchpart == SRLatchPart.SECONDARY) {
                BlockPos blockpos = pos.relative(getNeighbourDirection(latchpart, (Direction)state.getValue(FACING)));
                BlockState blockstate = level.getBlockState(blockpos);
                if (blockstate.is(this) && blockstate.getValue(PART) == SRLatchPart.PRIMARY) {
                    level.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
                    level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
                }
            }
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (facing != getNeighbourDirection((SRLatchPart) state.getValue(PART), (Direction)state.getValue(FACING))) {
            return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        } else {
            return facingState.is(this) && facingState.getValue(PART) != state.getValue(PART) ? (BlockState)state.setValue(POWERED, (Boolean)facingState.getValue(POWERED)) : Blocks.AIR.defaultBlockState();
        }
    }

    private static Direction getNeighbourDirection(SRLatchPart part, Direction direction) {
        return part == SRLatchPart.SECONDARY ? direction.getClockWise() : direction.getCounterClockWise();
    }

    static {
        POWERED = BlockStateProperties.POWERED;
        PART = EnumProperty.create("part", SRLatchPart.class);
    }
}
