package dev.chyken.data;

import dev.chyken.block.LogicBlocks;
import dev.chyken.block.LogicGateBlock;
import dev.chyken.block.SRLatchBlock;
import dev.chyken.block.state.properties.SRLatchPart;
import dev.chyken.logicc.LogicCircuitsMod;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class LogicBlockStateProvider extends BlockStateProvider {

    public LogicBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LogicCircuitsMod.MODID, existingFileHelper);
    }

    private void registerLogicGate(net.minecraft.world.level.block.Block block, String baseName) {
        ModelFile gateOff = models().getExistingFile(modLoc("block/" + baseName));
        ModelFile gateOn = models().getExistingFile(modLoc("block/" + baseName + "_on"));

        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(LogicGateBlock.FACING);
            boolean powered = state.getValue(LogicGateBlock.POWERED);

            ModelFile activeModel = powered ? gateOn : gateOff;

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

        ModelFile resetOff = models().getExistingFile(modLoc("block/reset"));
        ModelFile resetOn = models().getExistingFile(modLoc("block/reset_on"));
        ModelFile setOff = models().getExistingFile(modLoc("block/set"));
        ModelFile setOn = models().getExistingFile(modLoc("block/set_on"));

        getVariantBuilder(LogicBlocks.SR_LATCH.get()).forAllStates(state -> {
            Direction facing = state.getValue(SRLatchBlock.FACING);
            boolean powered = state.getValue(SRLatchBlock.POWERED);
            var part = state.getValue(SRLatchBlock.PART);

            ModelFile activeModel;
            if (part == SRLatchPart.RESET) {
                activeModel = powered ? resetOn : resetOff;
            } else {
                activeModel = powered ? setOn : setOff;
            }

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
        });
    }
}