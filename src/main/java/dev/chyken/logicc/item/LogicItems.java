package dev.chyken.logicc.item;

import dev.chyken.logicc.block.LogicBlocks;
import dev.chyken.logicc.LogicCircuitsMod;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LogicItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LogicCircuitsMod.MODID);

    public static final DeferredItem<BlockItem> NOT_GATE_ITEM = ITEMS.registerSimpleBlockItem("not_gate", LogicBlocks.NOT_GATE);
    public static final DeferredItem<BlockItem> OR_GATE_ITEM = ITEMS.registerSimpleBlockItem("or_gate", LogicBlocks.OR_GATE);
    public static final DeferredItem<BlockItem> AND_GATE_ITEM = ITEMS.registerSimpleBlockItem("and_gate", LogicBlocks.AND_GATE);
    public static final DeferredItem<BlockItem> NOR_GATE_ITEM = ITEMS.registerSimpleBlockItem("nor_gate", LogicBlocks.NOR_GATE);
    public static final DeferredItem<BlockItem> NAND_GATE_ITEM = ITEMS.registerSimpleBlockItem("nand_gate", LogicBlocks.NAND_GATE);
    public static final DeferredItem<BlockItem> XOR_GATE_ITEM = ITEMS.registerSimpleBlockItem("xor_gate", LogicBlocks.XOR_GATE);
    public static final DeferredItem<BlockItem> XNOR_GATE_ITEM = ITEMS.registerSimpleBlockItem("xnor_gate", LogicBlocks.XNOR_GATE);

    public static final DeferredItem<BlockItem> HALF_ADDER_ITEM = ITEMS.registerSimpleBlockItem("half_adder", LogicBlocks.HALF_ADDER);
    public static final DeferredItem<BlockItem> FULL_ADDER_ITEM = ITEMS.registerSimpleBlockItem("full_adder", LogicBlocks.FULL_ADDER);
    public static final DeferredItem<BlockItem> SR_LATCH_ITEM = ITEMS.registerSimpleBlockItem("sr_latch", LogicBlocks.SR_LATCH);
}
