package dev.chyken.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.SignalGetter;
import net.minecraft.world.level.block.state.BlockState;

public class XnorGateBlock extends LogicGateBlock {
    public XnorGateBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        Direction direction = blockState.getValue(FACING);
        Direction left = direction.getClockWise();
        Direction right = direction.getCounterClockWise();
        boolean flag = this.sideInputDiodesOnly();

        if (blockAccess instanceof SignalGetter level) {
            return level.getControlInputSignal(pos.relative(left), left, flag) > 0 == level.getControlInputSignal(pos.relative(right), right, flag) > 0 && blockState.getValue(FACING) == side ? 15 : 0;
        } else {
            return 0;
        }
    }
}
