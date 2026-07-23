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
        LogicItems.ITEMS.getEntries().forEach(item -> basicItem(item.get()));
    }
}