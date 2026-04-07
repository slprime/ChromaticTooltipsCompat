package com.slprime.chromatictooltipscompat.mixins;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import com.gtnewhorizon.gtnhmixins.builders.TargetModBuilder;

public enum TargetedMod implements ITargetMod {

    // spotless:off
    BUILDCRAFT(null, "BuildCraft|Core"),
    CODECHICKEN_LIB(null, "CodeChickenCore"),
    CODECHICKEN_NEI(null, "NotEnoughItems"),
    BETTER_QUESTING(null, "betterquesting"),
    ENDER_IO_CORE(null, "endercore"),
    FORESTRY(null, "Forestry"),
    MODULAR_UI(null, "modularui"),
    MODULAR_UI2(null, "modularui2"),
    BOTANIA(null, "Botania"),
    BINNIE_CORE(null, "BinnieCore"),
    RAILCRAFT(null, "Railcraft"),
    APPLIED_ENERGISTICS_2(null, "appliedenergistics2"),
    TINKERS_CONSTRUCT(null, "TConstruct"),
    WAILA(null, "Waila"),
    WAWLA(null, "wawla"),
    NEI_CUSTOM_DIAGRAM(null, "neicustomdiagram"),
    GTNH_LIB(null, "gtnhlib"),
    GREGTECH( "gregtech.asm.GTCorePlugin", "gregtech"),
    AE2FC(null, "ae2fc"),
    LOGISTICS_PIPES(null, "LogisticsPipes"),
    MALISIS_DOORS(null, "malisisdoors"),
    BAUBLES_EXPANDED(null, "Baubles|Expanded"),
    APPLECORE(null, "AppleCore"),
    JECALCULATION(null, "jecalculation"),
    JOURNEYMAP(null, "journeymap"),
    SCIENCE_NOT_LEISURE(null, "sciencenotleisure"),
    HARDCORE_ENDER_EXPANSION(null, "HardcoreEnderExpansion");
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
