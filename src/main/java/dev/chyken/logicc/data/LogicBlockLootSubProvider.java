package dev.chyken.logicc.data;

import dev.chyken.logicc.block.LogicBlocks;
import dev.chyken.logicc.block.adders.FullAdderBlock;
import dev.chyken.logicc.block.adders.HalfAdderBlock;
import dev.chyken.logicc.block.latches.SRLatchBlock;
import dev.chyken.logicc.block.state.properties.DoubleBlockPart;
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

        this.add(
                LogicBlocks.HALF_ADDER.get(),
                block -> createSinglePropConditionTable(
                        block,
                        HalfAdderBlock.PART,
                        DoubleBlockPart.RIGHT
                )
        );
        this.add(
                LogicBlocks.FULL_ADDER.get(),
                block -> createSinglePropConditionTable(
                        block,
                        FullAdderBlock.PART,
                        DoubleBlockPart.RIGHT
                )
        );
        this.add(
                LogicBlocks.SR_LATCH.get(),
                block -> createSinglePropConditionTable(
                        block,
                        SRLatchBlock.PART,
                        DoubleBlockPart.RIGHT
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return LogicBlocks.BLOCKS.getEntries().stream().map(holder -> (Block) holder.value())::iterator;
    }
}