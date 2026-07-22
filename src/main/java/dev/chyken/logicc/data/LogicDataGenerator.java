package dev.chyken.logicc.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;

public class LogicDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        boolean includeClient = event.includeClient();

        generator.addProvider(
                event.includeServer(),
                new LootTableProvider(
                        output,
                        Set.of(),
                        List.of(new LootTableProvider.SubProviderEntry(
                                LogicBlockLootSubProvider::new,
                                LootContextParamSets.BLOCK
                        )),
                        event.getLookupProvider()
                )
        );

        generator.addProvider(
                includeClient,
                new LogicItemModelProvider(output, existingFileHelper)
        );

        generator.addProvider(
                includeClient,
                new LogicBlockStateProvider(output, existingFileHelper)
        );
    }
}