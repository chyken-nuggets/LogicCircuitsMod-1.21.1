package dev.chyken.logicc.data;

import dev.chyken.logicc.item.LogicItems;
import dev.chyken.logicc.LogicCircuitsMod;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class LogicItemModelProvider extends ItemModelProvider {

    public LogicItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LogicCircuitsMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(LogicItems.NOT_GATE_ITEM.get());
        basicItem(LogicItems.OR_GATE_ITEM.get());
        basicItem(LogicItems.AND_GATE_ITEM.get());
        basicItem(LogicItems.NOR_GATE_ITEM.get());
        basicItem(LogicItems.NAND_GATE_ITEM.get());
        basicItem(LogicItems.XOR_GATE_ITEM.get());
        basicItem(LogicItems.XNOR_GATE_ITEM.get());

        basicItem(LogicItems.HALF_ADDER_ITEM.get());
        basicItem(LogicItems.FULL_ADDER_ITEM.get());
        basicItem(LogicItems.SR_LATCH_ITEM.get());
    }
}