package com.slprime.chromatictooltipscompat.mixins;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import com.gtnewhorizon.gtnhmixins.builders.TargetModBuilder;

public enum TargetedMod implements ITargetMod {

    // spotless:off
    CODECHICKEN_LIB(null, "CodeChickenCore"),
    CODECHICKEN_NEI(null, "NotEnoughItems"),
    BETTER_QUESTING( null, "betterquesting"),
    ENDER_IO_CORE( null, "endercore"),
    FORESTRY( null, "Forestry"),
    MODULAR_UI( null, "modularui"),
    MODULAR_UI2( null, "modularui2"),
    BOTANIA( null, "Botania"),
    BINNIE_CORE( null, "BinnieCore"),
    RAILCRAFT( null, "Railcraft"),
    APPLIED_ENERGISTICS_2( null, "appliedenergistics2"),
    TINKERS_CONSTRUCT( null, "TConstruct"),
    WAILA( null, "Waila"),
    WAWLA( null, "wawla"),
    NEI_CUSTOM_DIAGRAM( null, "neicustomdiagram"),
    GTNH_LIB( null, "gtnhlib"),
    GREGTECH( null, "gregtech");
    // spotless:on

    private final TargetModBuilder builder;

    TargetedMod(TargetModBuilder builder) {
        this.builder = builder;
    }

    TargetedMod(String coreModClass, String modId) {
        this.builder = new TargetModBuilder().setCoreModClass(coreModClass)
            .setModId(modId);
    }

    @Nonnull
    @Override
    public TargetModBuilder getBuilder() {
        return builder;
    }
}
