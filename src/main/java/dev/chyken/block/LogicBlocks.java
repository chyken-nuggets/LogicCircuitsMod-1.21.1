package dev.chyken.block;

import dev.chyken.block.gates.*;
import dev.chyken.block.latches.SRLatchBlock;
import dev.chyken.logicc.LogicCircuitsMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LogicBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, LogicCircuitsMod.MODID);

    public static final DeferredHolder<Block, Block> NOT_GATE = BLOCKS.register("not_gate", () -> new NotGateBlock(BlockBehaviour.Properties.of()));
    public static final DeferredHolder<Block, Block> OR_GATE = BLOCKS.register("or_gate", () -> new OrGateBlock(BlockBehaviour.Properties.of()));
    public static final DeferredHolder<Block, Block> AND_GATE = BLOCKS.register("and_gate", () -> new AndGateBlock(BlockBehaviour.Properties.of()));
    public static final DeferredHolder<Block, Block> NOR_GATE = BLOCKS.register("nor_gate", () -> new NorGateBlock(BlockBehaviour.Properties.of()));
    public static final DeferredHolder<Block, Block> NAND_GATE = BLOCKS.register("nand_gate", () -> new NandGateBlock(BlockBehaviour.Properties.of()));
    public static final DeferredHolder<Block, Block> XOR_GATE = BLOCKS.register("xor_gate", () -> new XorGateBlock(BlockBehaviour.Properties.of()));
    public static final DeferredHolder<Block, Block> XNOR_GATE = BLOCKS.register("xnor_gate", () -> new XnorGateBlock(BlockBehaviour.Properties.of()));

    public static final DeferredHolder<Block, Block> SR_LATCH = BLOCKS.register("sr_latch", () -> new SRLatchBlock(BlockBehaviour.Properties.of()));
}
