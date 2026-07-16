package dev.chyken.data;

import dev.chyken.block.LogicBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Collections;

public class LogicBlockLootSubProvider extends BlockLootSubProvider {

    protected LogicBlockLootSubProvider(HolderLookup.Provider registries) {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        this.dropSelf(LogicBlocks.NOT_GATE.get());
        this.dropSelf(LogicBlocks.OR_GATE.get());
        this.dropSelf(LogicBlocks.AND_GATE.get());
        this.dropSelf(LogicBlocks.NOR_GATE.get());
        this.dropSelf(LogicBlocks.NAND_GATE.get());
        this.dropSelf(LogicBlocks.XOR_GATE.get());
        this.dropSelf(LogicBlocks.XNOR_GATE.get());

        this.dropSelf(LogicBlocks.HALF_ADDER.get());
        this.dropSelf(LogicBlocks.FULL_ADDER.get());
        this.dropSelf(LogicBlocks.SR_LATCH.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return LogicBlocks.BLOCKS.getEntries().stream().map(holder -> (Block) holder.value())::iterator;
    }
}