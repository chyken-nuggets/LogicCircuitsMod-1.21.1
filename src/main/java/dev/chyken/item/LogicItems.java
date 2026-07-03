package dev.chyken.item;

import dev.chyken.block.LogicBlocks;
import dev.chyken.logicc.LogicCircuitsMod;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LogicItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LogicCircuitsMod.MODID);

    public static final DeferredItem<BlockItem> NOT_GATE_ITEM = ITEMS.registerSimpleBlockItem("not_gate", LogicBlocks.NOT_GATE);
    public static final DeferredItem<BlockItem> OR_GATE_ITEM = ITEMS.registerSimpleBlockItem("or_gate", LogicBlocks.OR_GATE);
}
