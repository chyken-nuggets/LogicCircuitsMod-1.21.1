package dev.chyken.logicc.block;

import dev.chyken.logicc.block.adders.FullAdderBlock;
import dev.chyken.logicc.block.adders.HalfAdderBlock;
import dev.chyken.logicc.block.gates.*;
import dev.chyken.logicc.block.latches.SRLatchBlock;
import dev.chyken.logicc.LogicCircuitsMod;
import dev.chyken.logicc.item.LogicItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class LogicBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(LogicCircuitsMod.MODID);

    public static final DeferredBlock<NotGateBlock> NOT_GATE = registerBlock("not_gate", () -> new NotGateBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<OrGateBlock> OR_GATE = registerBlock("or_gate", () -> new OrGateBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<AndGateBlock> AND_GATE = registerBlock("and_gate", () -> new AndGateBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<NorGateBlock> NOR_GATE = registerBlock("nor_gate", () -> new NorGateBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<NandGateBlock> NAND_GATE = registerBlock("nand_gate", () -> new NandGateBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<XorGateBlock> XOR_GATE = registerBlock("xor_gate", () -> new XorGateBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<XnorGateBlock> XNOR_GATE = registerBlock("xnor_gate", () -> new XnorGateBlock(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<HalfAdderBlock> HALF_ADDER = registerBlock("half_adder", () -> new HalfAdderBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<FullAdderBlock> FULL_ADDER = registerBlock("full_adder", () -> new FullAdderBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<SRLatchBlock> SR_LATCH = registerBlock("sr_latch", () -> new SRLatchBlock(BlockBehaviour.Properties.of()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> supplier) {
        DeferredBlock<T> block = BLOCKS.register(name, supplier);
        LogicItems.ITEMS.registerSimpleBlockItem(block);

        return block;
    }
}
