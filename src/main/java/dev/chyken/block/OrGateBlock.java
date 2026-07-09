package dev.chyken.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.SignalGetter;
import net.minecraft.world.level.block.state.BlockState;

public class OrGateBlock extends LogicGateBlock {
    public OrGateBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        if (blockAccess instanceof SignalGetter level) {
            return (getAlternateSignal(level, pos, blockState) > 0) && blockState.getValue(FACING) == side ? 15 : 0;
        } else {
            return 0;
        }
    }
}
