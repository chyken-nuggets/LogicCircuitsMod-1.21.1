package dev.chyken.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.SignalGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
public class AndGateBlock extends DiodeBlock {
    public static final MapCodec<AndGateBlock> CODEC = simpleCodec(AndGateBlock::new);

    public MapCodec<AndGateBlock> codec() {
        return CODEC;
    }

    public AndGateBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(POWERED, Boolean.valueOf(false))
        );
    }

    protected int getDelay(BlockState state) { return 2; }

    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        Direction direction = blockState.getValue(FACING);
        Direction left = direction.getClockWise();
        Direction right = direction.getCounterClockWise();
        boolean flag = this.sideInputDiodesOnly();

        if (blockAccess instanceof SignalGetter level) {
            return (level.getControlInputSignal(pos.relative(left), left, flag) > 0 && level.getControlInputSignal(pos.relative(right), right, flag) > 0) && blockState.getValue(FACING) == side ? 15 : 0;
        } else {
            return 0;
        }
    }

    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !this.canSurviveOn(level, neighborPos, neighborState) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    protected boolean sideInputDiodesOnly() {
        return false;
    }

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

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED);
    }
}
