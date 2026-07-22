package dev.chyken.logicc.block.gates;

import dev.chyken.logicc.block.types.LogicGateBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class AndGateBlock extends LogicGateBlock {
    public AndGateBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean shouldTurnOn(Level level, BlockPos pos, BlockState state) {
        Direction direction = state.getValue(FACING);
        Direction left = direction.getClockWise();
        Direction right = direction.getCounterClockWise();
        boolean flag = this.sideInputDiodesOnly();

        return level.getControlInputSignal(pos.relative(left), left, flag) > 0 && level.getControlInputSignal(pos.relative(right), right, flag) > 0;
    }
}
