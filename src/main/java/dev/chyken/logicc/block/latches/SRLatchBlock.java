package dev.chyken.logicc.block.latches;

import dev.chyken.logicc.block.types.TwoPartLogicBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

public class SRLatchBlock extends TwoPartLogicBlock {
    public SRLatchBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (facing != getNeighbourDirection(state.getValue(PART), state.getValue(FACING))) {
            return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        } else {
            return facingState.is(this) && facingState.getValue(PART) != state.getValue(PART) ? state.setValue(POWERED, !facingState.getValue(POWERED)) : Blocks.AIR.defaultBlockState();
        }
    }

    @Override
    protected boolean shouldTurnOn(Level level, BlockPos pos, BlockState state) {
        BlockPos setPos = pos.relative(getNeighbourDirection(state.getValue(PART), state.getValue(FACING)));
        BlockState setState = level.getBlockState(setPos);

        if (!setState.is(this)) {
            return state.getValue(POWERED);
        }

        if (this.getInputSignal(level, pos, state) <= 0 == this.getInputSignal(level, setPos, setState) <= 0) {
            return state.getValue(POWERED);
        }

        return !(this.getInputSignal(level, pos, state) > 0);
    }
}