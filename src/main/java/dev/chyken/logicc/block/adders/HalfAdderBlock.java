package dev.chyken.logicc.block.adders;

import dev.chyken.logicc.block.state.properties.DoubleBlockPart;
import dev.chyken.logicc.block.types.TwoPartLogicBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.EventHooks;

import java.util.EnumSet;

public class HalfAdderBlock extends TwoPartLogicBlock {
    public HalfAdderBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void updateNeighborsInFront(Level level, BlockPos pos, BlockState state) {
        if (state.getValue(PART) == DoubleBlockPart.LEFT) {
            Direction direction = state.getValue(FACING);
            BlockPos blockpos = pos.relative(direction.getOpposite());
            if (!EventHooks.onNeighborNotify(level, pos, level.getBlockState(pos), EnumSet.of(direction.getOpposite()), false).isCanceled()) {
                level.neighborChanged(blockpos, this, pos);
                level.updateNeighborsAtExceptFromFacing(blockpos, this, direction);
            }
        } else {
            Direction direction2 = state.getValue(FACING).getClockWise();
            BlockPos blockpos2 = pos.relative(direction2.getOpposite());
            if (!EventHooks.onNeighborNotify(level, pos, level.getBlockState(pos), EnumSet.of(direction2.getOpposite()), false).isCanceled()) {
                level.neighborChanged(blockpos2, this, pos);
                level.updateNeighborsAtExceptFromFacing(blockpos2, this, direction2);
            }
        }
    }

    @Override
    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        if (!(Boolean)blockState.getValue(POWERED)) {
            return 0;
        } else {
            return (blockState.getValue(FACING) == side || blockState.getValue(FACING).getClockWise() == side) ? this.getOutputSignal(blockAccess, pos, blockState) : 0;
        }
    }

    @Override
    protected boolean shouldTurnOn(Level level, BlockPos pos, BlockState state) {
        BlockPos secondaryPos = pos.relative(getNeighbourDirection(state.getValue(PART), state.getValue(FACING)));
        BlockState secondaryState = level.getBlockState(secondaryPos);

        if (!secondaryState.is(this)) {
            return false;
        }

        if ((this.getInputSignal(level, pos, state) > 0 ^ this.getInputSignal(level, secondaryPos, secondaryState) > 0) && state.getValue(PART) == DoubleBlockPart.LEFT) {
            return true;
        }

        if ((this.getInputSignal(level, pos, state) > 0 && this.getInputSignal(level, secondaryPos, secondaryState) > 0) && state.getValue(PART) == DoubleBlockPart.RIGHT) {
            return true;
        }

        return false;
    }
}