package dev.chyken.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
public class NotGateBlock extends DiodeBlock {
    public static final MapCodec<NotGateBlock> CODEC = simpleCodec(NotGateBlock::new);

    @Override
    public MapCodec<NotGateBlock> codec() {
        return CODEC;
    }

    public NotGateBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(POWERED, Boolean.valueOf(false))
        );
    }

    @Override
    protected int getDelay(BlockState state) { return 2; }

    @Override
    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        if (blockState.getValue(POWERED)) {
            return 0;
        } else {
            return blockState.getValue(FACING) == side ? this.getOutputSignal(blockAccess, pos, blockState) : 0;
        }
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !this.canSurviveOn(level, neighborPos, neighborState) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected boolean sideInputDiodesOnly() {
        return true;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(POWERED)) {
            Direction direction = state.getValue(FACING);
            double d0 = (double)pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
            double d1 = (double)pos.getY() + 0.4 + (random.nextDouble() - 0.5) * 0.2;
            double d2 = (double)pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
            float f = -5.0F;
            if (random.nextBoolean()) {
                f = (float)(1);
            }

            f /= 16.0F;
            double d3 = (double)(f * (float)direction.getStepX());
            double d4 = (double)(f * (float)direction.getStepZ());
            level.addParticle(DustParticleOptions.REDSTONE, d0 + d3, d1, d2 + d4, 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED);
    }
}
