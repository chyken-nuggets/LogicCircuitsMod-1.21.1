package dev.chyken.block.types;

import dev.chyken.block.state.properties.DoubleBlockPart;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import javax.annotation.Nullable;

public class TwoPartLogicBlock extends LogicGateBlock {
    public static final EnumProperty<DoubleBlockPart> PART = EnumProperty.create("part", DoubleBlockPart.class);

    public TwoPartLogicBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(POWERED, Boolean.valueOf(false))
                        .setValue(PART, DoubleBlockPart.LEFT)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, PART);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection().getOpposite();
        BlockPos blockpos = context.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(direction.getCounterClockWise());
        Level level = context.getLevel();
        return level.getBlockState(blockpos1).canBeReplaced(context) && level.getWorldBorder().isWithinBounds(blockpos1) ? (BlockState)this.defaultBlockState().setValue(FACING, direction) : null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            BlockPos setPos = pos.relative(state.getValue(FACING).getCounterClockWise());

            level.setBlock(setPos, state.setValue(PART, DoubleBlockPart.RIGHT), 3);
            level.blockUpdated(pos, Blocks.AIR);
            level.blockUpdated(setPos, Blocks.AIR);
        }

        if (this.shouldTurnOn(level, pos, state)) {
            level.scheduleTick(pos, this, 1);
        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && player.isCreative()) {
            DoubleBlockPart latchpart = state.getValue(PART);
            if (latchpart == DoubleBlockPart.LEFT) {
                BlockPos blockpos = pos.relative(getNeighbourDirection(latchpart, state.getValue(FACING)));
                BlockState blockstate = level.getBlockState(blockpos);
                if (blockstate.is(this) && blockstate.getValue(PART) == DoubleBlockPart.RIGHT) {
                    level.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
                    level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
                }
            }
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (facing != getNeighbourDirection(state.getValue(PART), state.getValue(FACING))) {
            return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        } else {
            return facingState.is(this) && facingState.getValue(PART) != state.getValue(PART) ? state.setValue(POWERED, state.getValue(POWERED)) : Blocks.AIR.defaultBlockState();
        }
    }

    protected static Direction getNeighbourDirection(DoubleBlockPart part, Direction direction) {
        return part == DoubleBlockPart.LEFT ? direction.getCounterClockWise() : direction.getClockWise();
    }
}
