package dev.chyken.block.gates;

import dev.chyken.block.types.LogicGateBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class NotGateBlock extends LogicGateBlock {
    public NotGateBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean shouldTurnOn(Level level, BlockPos pos, BlockState state) {
        return this.getInputSignal(level, pos, state) == 0;
    }

    @Override
    protected boolean sideInputDiodesOnly() {
        return true;
    }
}
