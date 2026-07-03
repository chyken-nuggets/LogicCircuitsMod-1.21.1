package dev.chyken.block;

import dev.chyken.logicc.LogicCircuitsMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LogicBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, LogicCircuitsMod.MODID);

    public static final DeferredHolder<Block, Block> NOT_GATE = BLOCKS.register("not_gate", () -> new NotGateBlock(BlockBehaviour.Properties.of().instabreak().sound(SoundType.STONE).pushReaction(PushReaction.DESTROY)));
    public static final DeferredHolder<Block, Block> OR_GATE = BLOCKS.register("or_gate", () -> new OrGateBlock(BlockBehaviour.Properties.of().instabreak().sound(SoundType.STONE).pushReaction(PushReaction.DESTROY)));
}
