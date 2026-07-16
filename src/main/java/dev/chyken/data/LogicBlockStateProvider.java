package dev.chyken.data;

import dev.chyken.block.LogicBlocks;
import dev.chyken.block.types.LogicGateBlock;
import dev.chyken.block.state.properties.DoubleBlockPart;
import dev.chyken.block.types.TwoPartLogicBlock;
import dev.chyken.logicc.LogicCircuitsMod;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class LogicBlockStateProvider extends BlockStateProvider {

    public LogicBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LogicCircuitsMod.MODID, existingFileHelper);
    }

    @NotNull
    private ConfiguredModel[] getConfiguredModels(Direction facing, ModelFile activeModel) {
        int yRotation = switch (facing) {
            case SOUTH -> 0;
            case WEST -> 90;
            case NORTH -> 180;
            case EAST -> 270;
            default -> 0;
        };

        return ConfiguredModel.builder()
                .modelFile(activeModel)
                .rotationY(yRotation)
                .build();
    }

    private void registerLogicGate(net.minecraft.world.level.block.Block block, String baseName) {
        ModelFile gateOff = models().getExistingFile(modLoc("block/" + baseName));
        ModelFile gateOn = models().getExistingFile(modLoc("block/" + baseName + "_on"));

        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(LogicGateBlock.FACING);
            boolean powered = state.getValue(LogicGateBlock.POWERED);

            ModelFile activeModel = powered ? gateOn : gateOff;

            return getConfiguredModels(facing, activeModel);
        });
    }

    private void registerDouble(net.minecraft.world.level.block.Block block, String primaryName, String secondaryName) {
        ModelFile primaryOff = models().getExistingFile(modLoc("block/" + primaryName));
        ModelFile primaryOn = models().getExistingFile(modLoc("block/" + primaryName + "_on"));
        ModelFile secondaryOff = models().getExistingFile(modLoc("block/" + secondaryName));
        ModelFile secondaryOn = models().getExistingFile(modLoc("block/" + secondaryName + "_on"));

        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(TwoPartLogicBlock.FACING);
            boolean powered = state.getValue(TwoPartLogicBlock.POWERED);
            var part = state.getValue(TwoPartLogicBlock.PART);

            ModelFile activeModel;
            if (part == DoubleBlockPart.LEFT) {
                activeModel = powered ? primaryOn : primaryOff;
            } else {
                activeModel = powered ? secondaryOn : secondaryOff;
            }

            return getConfiguredModels(facing, activeModel);
        });
    }

    @Override
    protected void registerStatesAndModels() {
        registerLogicGate(LogicBlocks.NOT_GATE.get(), "not_gate");
        registerLogicGate(LogicBlocks.OR_GATE.get(), "or_gate");
        registerLogicGate(LogicBlocks.AND_GATE.get(), "and_gate");
        registerLogicGate(LogicBlocks.NOR_GATE.get(), "nor_gate");
        registerLogicGate(LogicBlocks.NAND_GATE.get(), "nand_gate");
        registerLogicGate(LogicBlocks.XOR_GATE.get(), "xor_gate");
        registerLogicGate(LogicBlocks.XNOR_GATE.get(), "xnor_gate");

        registerDouble(LogicBlocks.HALF_ADDER.get(), "half_sum", "carry");
        registerDouble(LogicBlocks.FULL_ADDER.get(), "full_sum", "carry");
        registerDouble(LogicBlocks.SR_LATCH.get(), "reset", "set");
    }
}