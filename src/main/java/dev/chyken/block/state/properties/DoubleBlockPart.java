package dev.chyken.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum DoubleBlockPart implements StringRepresentable {
    LEFT("left"),
    RIGHT("right");

    private final String name;

    DoubleBlockPart(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}
