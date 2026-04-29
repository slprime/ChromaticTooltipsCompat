package com.slprime.chromatictooltipscompat.mixins;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;
import com.slprime.chromatictooltipscompat.CompatConfig;

public enum Mixins implements IMixins {

    BETTER_QUESTING(new MixinBuilder("BetterQuesting").addRequiredMod(TargetedMod.BETTER_QUESTING)
        .addClientMixins(
            "betterquesting.RenderUtilsMixin",
            "betterquesting.GuiScreenCanvasMixin",
            "betterquesting.GuiContainerCanvasMixin",
            "betterquesting.PanelItemSlotMixin")
        .setApplyIf(() -> CompatConfig.betterQuestingEnabled)
        .setPhase(Phase.LATE)),

    CODECHICKEN_NEI(new MixinBuilder("NotEnoughItems").addRequiredMod(TargetedMod.CODECHICKEN_NEI)
        .addClientMixins(
            "notenoughitems.IGuiContainerCreativeAccessor",
            "notenoughitems.GuiContainerManagerMixin",
            "notenoughitems.LayoutManagerMixin",
            "notenoughitems.TooltipFilterMixin")
        .setApplyIf(() -> CompatConfig.notEnoughItemsEnabled)
        .setPhase(Phase.LATE)),

    CODECHICKEN_LIB(new MixinBuilder("CodeChickenLib").addRequiredMod(TargetedMod.CODECHICKEN_LIB)
        .addClientMixins("codechickenlib.GuiDrawMixin")
        .setApplyIf(() -> CompatConfig.codeChickenCoreEnabled)
        .setPhase(Phase.LATE)),

    ENDER_IO_CORE(new MixinBuilder("Ender IO").addRequiredMod(TargetedMod.ENDER_IO_CORE)
        .addClientMixins(
            "enderio.ToolTipManagerMixin",
            "enderio.OreDictTooltipHandlerMixin",
            "enderio.SpecialTooltipHandlerMixin",
            "enderio.EnchantTooltipHandlerMixin",
            "enderio.ClientHandlerMixin",
            "enderio.TooltipHandlerBurnTimeMixin",
            "enderio.GuiMachineBaseMixin",
            "enderio.GuiMachineBaseInvoker",
            "enderio.GuiToolTipMixin")
        .setApplyIf(() -> CompatConfig.enderCoreEnabled)
        .setPhase(Phase.LATE)),

    FORESTRY(new MixinBuilder("Forestry").addRequiredMod(TargetedMod.FORESTRY)
        .addClientMixins("forestry.GuiUtilMixin")
        .setApplyIf(() -> CompatConfig.forestryEnabled)
        .setPhase(Phase.LATE)),

    GALACTICRAFT_CORE(new MixinBuilder("Galacticraft Core").addRequiredMod(TargetedMod.GALACTICRAFT_CORE)
        .addClientMixins("galacticraft.GuiElementInfoRegionMixin")
        .setApplyIf(() -> CompatConfig.galacticraftcoreEnabled)
        .setPhase(Phase.LATE)),

    MODULAR_UI(new MixinBuilder("Modular UI").addRequiredMod(TargetedMod.MODULAR_UI)
        .addClientMixins("modularui.ModularGuiMixin", "modularui.GuiHelperMixin")
        .setApplyIf(() -> CompatConfig.modularUIEnabled)
        .setPhase(Phase.LATE)),

    MODULAR_UI2(new MixinBuilder("Modular UI 2").addRequiredMod(TargetedMod.MODULAR_UI2)
        .addClientMixins("modularui2.RichTooltipMixin")
        .setApplyIf(() -> CompatConfig.modularUI2Enabled)
        .setPhase(Phase.LATE)),

    WAILA(new MixinBuilder("Waila").addRequiredMod(TargetedMod.WAILA)
        .addClientMixins("waila.TooltipHandlerWailaMixin")
        .setPhase(Phase.LATE)),

    WAILA_RENDER(new MixinBuilder("Waila").addRequiredMod(TargetedMod.WAILA)
        .addClientMixins("waila.OverlayRendererMixin", "waila.TooltipInvoker", "waila.TooltipMixin")
        .setApplyIf(() -> CompatConfig.wailaEnabled)
        .setPhase(Phase.LATE)),

    BOTANIA(new MixinBuilder("Botania").addRequiredMod(TargetedMod.BOTANIA)
        .addClientMixins("botania.RenderHelperMixin")
        .setApplyIf(() -> CompatConfig.botaniaEnabled)
        .setPhase(Phase.LATE)),

    AKASHIC_TOME(new MixinBuilder("Akashic Tome").addRequiredMod(TargetedMod.AKASHIC_TOME)
        .addClientMixins("akashictome.RenderHelperMixin")
        .setApplyIf(() -> CompatConfig.akashictomeEnabled)
        .setPhase(Phase.LATE)),

    BAUBLES_EXPANDED(new MixinBuilder("Baubles Expanded").addRequiredMod(TargetedMod.BAUBLES_EXPANDED)
        .addClientMixins("baublesExpanded.GuiBaublesButtonMixin")
        .setApplyIf(() -> CompatConfig.baublesExpandedEnabled)
        .setPhase(Phase.LATE)),

    RAILCRAFT(new MixinBuilder("Railcraft").addRequiredMod(TargetedMod.RAILCRAFT)
        .addClientMixins("railcraft.GuiContainerRailcraftMixin", "railcraft.ToolTipMixin")
        .setApplyIf(() -> CompatConfig.railcraftEnabled)
        .setPhase(Phase.LATE)),

    APPLIED_ENERGISTICS(new MixinBuilder("Applied Energistics 2").addRequiredMod(TargetedMod.APPLIED_ENERGISTICS_2)
        .addClientMixins("appliedenergistics2.GuiInterfaceTerminalMixin")
        .setApplyIf(() -> CompatConfig.appliedEnergisticsEnabled)
        .setPhase(Phase.LATE)),

    APPLIED_ENERGISTICS_AMOUNT(
        new MixinBuilder("Applied Energistics 2").addRequiredMod(TargetedMod.APPLIED_ENERGISTICS_2)
            .addClientMixins("appliedenergistics2.GuiMEMonitorableMixin")
            .setApplyIf(() -> CompatConfig.appliedEnergisticsEnabled && CompatConfig.appliedEnergisticsAmountEnabled)
            .setPhase(Phase.LATE)),

    TINKERS_CONSTRUCT(new MixinBuilder("Tinkers Construct").addRequiredMod(TargetedMod.TINKERS_CONSTRUCT)
        .addClientMixins("tconstruct.SmelteryGuiMixin")
        .setApplyIf(() -> CompatConfig.tinkersConstructEnabled)
        .setPhase(Phase.LATE)),

    BINNIE_CORE(new MixinBuilder("Binnie Core").addRequiredMod(TargetedMod.BINNIE_CORE)
        .addClientMixins("binniecore.GuiCraftGUIMixin")
        .setApplyIf(() -> CompatConfig.binnieCoreEnabled)
        .setPhase(Phase.LATE)),

    WAWLA(new MixinBuilder("Waila").addRequiredMod(TargetedMod.WAWLA)
        .addClientMixins("wawla.AddonGenericTooltipsMixin")
        .setApplyIf(() -> CompatConfig.wawlaEnabled)
        .setPhase(Phase.LATE)),

    NEI_CUSTOM_DIAGRAM(new MixinBuilder("NEI Custom Diagram").addRequiredMod(TargetedMod.NEI_CUSTOM_DIAGRAM)
        .addClientMixins("neicustomdiagram.DrawMixin")
        .setApplyIf(() -> CompatConfig.neicustomdiagramEnabled)
        .setPhase(Phase.LATE)),

    GTNH_LIB(new MixinBuilder("GTNHLib").addRequiredMod(TargetedMod.GTNH_LIB)
        .addClientMixins("gtnhlib.AnimatedTooltipHandlerMixin")
        .setApplyIf(() -> CompatConfig.gtnhlibEnabled)
        .setPhase(Phase.LATE)),

    GREGTECH_LATE(new MixinBuilder("GregTech").addRequiredMod(TargetedMod.GREGTECH)
        .addClientMixins("gregtech.ItemFluidDisplayMixin")
        .setApplyIf(() -> CompatConfig.gregtechEnabled)
        .setPhase(Phase.LATE)),

    GREGTECH_EARLY(new MixinBuilder("GregTech").addRequiredMod(TargetedMod.GREGTECH)
        .addClientMixins("gregtech.GTGenericItemMixin")
        .setApplyIf(() -> CompatConfig.gregtechEnabled)
        .setPhase(Phase.EARLY)),

    AE2FC(new MixinBuilder("AE2 Fluid Crafting").addRequiredMod(TargetedMod.AE2FC)
        .addClientMixins("ae2fc.BlockCertusQuartzTankMixin")
        .setApplyIf(() -> CompatConfig.ae2fcEnabled)
        .setPhase(Phase.LATE)),

    LOGISTICS_PIPES(new MixinBuilder("Logistics Pipes").addRequiredMod(TargetedMod.LOGISTICS_PIPES)
        .addClientMixins("logisticspipes.GuiGraphicsMixin")
        .setApplyIf(() -> CompatConfig.logisticsPipesEnabled)
        .setPhase(Phase.LATE)),

    MALISIS_DOORS(new MixinBuilder("Malisis Doors").addRequiredMod(TargetedMod.MALISIS_DOORS)
        .addClientMixins("malisisdoors.GuiRendererMixin", "malisisdoors.UITooltipAccessor")
        .setApplyIf(() -> CompatConfig.malisisDoorsEnabled)
        .setPhase(Phase.LATE)),

    APPLECORE(new MixinBuilder("AppleCore").addRequiredMod(TargetedMod.APPLECORE)
        .addClientMixins("applecore.TooltipOverlayHandlerMixin")
        .setApplyIf(() -> CompatConfig.appleCoreFoodStatsDisabled)
        .setPhase(Phase.LATE)),

    JECALCULATION(new MixinBuilder("Just Enough Calculation").addRequiredMod(TargetedMod.JECALCULATION)
        .addClientMixins("jecalculation.JecaGuiMixin", "jecalculation.WTooltipMixin")
        .setApplyIf(() -> CompatConfig.jecalculationEnabled)
        .setPhase(Phase.LATE)),

    JOURNEYMAP(new MixinBuilder("JourneyMap").addRequiredMod(TargetedMod.JOURNEYMAP)
        .addClientMixins("journeymap.JmUIMixin")
        .setApplyIf(() -> CompatConfig.journeymapEnabled)
        .setPhase(Phase.LATE)),

    THAUMCRAFT(new MixinBuilder("Thaumcraft").addRequiredMod(TargetedMod.THAUMCRAFT)
        .addClientMixins("thaumcraft.UtilsFXMixin", "thaumcraft.GuiResearchBrowserMixin")
        .setApplyIf(() -> CompatConfig.thaumcraftEnabled)
        .setPhase(Phase.LATE)),

    STEVES_CARTS_2(new MixinBuilder("Steve's Carts 2").addRequiredMod(TargetedMod.STEVES_CARTS_2)
        .addClientMixins("stevescarts2.GuiBaseMixin")
        .setApplyIf(() -> CompatConfig.stevesCarts2Enabled)
        .setPhase(Phase.LATE)),

    SCIENCE_NOT_LEISURE(new MixinBuilder("GT Not Leisure").addRequiredMod(TargetedMod.SCIENCE_NOT_LEISURE)
        .addClientMixins("sciencenotleisure.AnimatedTooltipHandlerMixin")
        .setApplyIf(() -> CompatConfig.scienceNotLeisureEnabled)
        .setPhase(Phase.LATE)),

    INDUSTRIAL_CRAFT_2(new MixinBuilder("IndustrialCraft 2").addRequiredMod(TargetedMod.INDUSTRIAL_CRAFT_2)
        .addClientMixins("industrialcraft2.GuiTooltipHelperMixin", "industrialcraft2.FluidCannerRecipeHandlerMixin")
        .setApplyIf(() -> CompatConfig.industrialCraft2Enabled)
        .setPhase(Phase.LATE)),

    BUILDCRAFT(new MixinBuilder("BuildCraft").addRequiredMod(TargetedMod.BUILDCRAFT)
        .addClientMixins("buildcraft.GuiBuildCraftMixin", "buildcraft.ToolTipMixin", "buildcraft.LedgerManagerMixin")
        .setApplyIf(() -> CompatConfig.buildcraftEnabled)
        .setPhase(Phase.LATE)),

    HARDCORE_ENDER_EXPANSION(
        new MixinBuilder("Hardcore Ender Expansion").addRequiredMod(TargetedMod.HARDCORE_ENDER_EXPANSION)
            .addClientMixins("hardcoreenderexpansion.GuiItemRenderHelperMixin")
            .setApplyIf(() -> CompatConfig.hardcoreEnderExpansionEnabled)
            .setPhase(Phase.LATE));

    private final MixinBuilder builder;

    Mixins(MixinBuilder builder) {
        this.builder = builder;
    }

    @Nonnull
    @Override
    public MixinBuilder getBuilder() {
        return builder;
    }
}
