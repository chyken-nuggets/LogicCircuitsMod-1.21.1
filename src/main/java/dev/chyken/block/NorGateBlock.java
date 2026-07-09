package dev.chyken.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class NorGateBlock extends LogicGateBlock {
    public NorGateBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean shouldTurnOn(Level level, BlockPos pos, BlockState state) {
        return getAlternateSignal(level, pos, state) <= 0;
    }
}
