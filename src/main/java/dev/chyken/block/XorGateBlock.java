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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class XorGateBlock extends LogicGateBlock {
    public XorGateBlock(Properties properties) {
        super(properties);
    }

    protected int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        Direction direction = blockState.getValue(FACING);
        Direction left = direction.getClockWise();
        Direction right = direction.getCounterClockWise();
        boolean flag = this.sideInputDiodesOnly();

        if (blockAccess instanceof SignalGetter level) {
            return (level.getControlInputSignal(pos.relative(left), left, flag) > 0 ^ level.getControlInputSignal(pos.relative(right), right, flag) > 0) && blockState.getValue(FACING) == side ? 15 : 0;
        } else {
            return 0;
        }
    }
}
