package dev.chyken.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum SRLatchPart implements StringRepresentable {
    PRIMARY("primary"),
    SECONDARY("secondary");

    private final String name;

    SRLatchPart(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String getSerializedName() {
        return this.name;
    }
}
