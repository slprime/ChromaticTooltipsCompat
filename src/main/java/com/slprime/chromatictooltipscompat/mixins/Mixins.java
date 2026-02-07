package com.slprime.chromatictooltipscompat.mixins;

import javax.annotation.Nonnull;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;
import com.slprime.chromatictooltipscompat.Config;

public enum Mixins implements IMixins {

    BETTER_QUESTING(new MixinBuilder("BetterQuesting").addRequiredMod(TargetedMod.BETTER_QUESTING)
        .addClientMixins(
            "betterquesting.RenderUtilsMixin",
            "betterquesting.GuiScreenCanvasMixin",
            "betterquesting.GuiContainerCanvasMixin",
            "betterquesting.PanelItemSlotMixin")
        .setApplyIf(() -> Config.betterQuestingEnabled)
        .setPhase(Phase.LATE)),

    CODECHICKEN_NEI(new MixinBuilder("NotEnoughItems").addRequiredMod(TargetedMod.CODECHICKEN_NEI)
        .addClientMixins(
            "notenoughitems.GuiContainerManagerMixin",
            "notenoughitems.LayoutManagerMixin",
            "notenoughitems.TooltipFilterMixin")
        .setApplyIf(() -> Config.notEnoughItemsEnabled)
        .setPhase(Phase.LATE)),

    CODECHICKEN_LIB(new MixinBuilder("CodeChickenLib").addRequiredMod(TargetedMod.CODECHICKEN_LIB)
        .addClientMixins("codechickenlib.GuiDrawMixin")
        .setApplyIf(() -> Config.codeChickenCoreEnabled)
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
        .setApplyIf(() -> Config.enderCoreEnabled)
        .setPhase(Phase.LATE)),

    FORESTRY(new MixinBuilder("Forestry").addRequiredMod(TargetedMod.FORESTRY)
        .addClientMixins("forestry.GuiUtilMixin")
        .setApplyIf(() -> Config.forestryEnabled)
        .setPhase(Phase.LATE)),

    MODULAR_UI(new MixinBuilder("Modular UI").addRequiredMod(TargetedMod.MODULAR_UI)
        .addClientMixins("modularui.ModularGuiMixin", "modularui.GuiHelperMixin")
        .setApplyIf(() -> Config.modularUIEnabled)
        .setPhase(Phase.LATE)),

    MODULAR_UI2(new MixinBuilder("Modular UI 2").addRequiredMod(TargetedMod.MODULAR_UI2)
        .addClientMixins("modularui2.RichTooltipMixin")
        .setApplyIf(() -> Config.modularUI2Enabled)
        .setPhase(Phase.LATE)),

    WAILA(new MixinBuilder("Waila").addRequiredMod(TargetedMod.WAILA)
        .addClientMixins("waila.TooltipHandlerWailaMixin")
        .setPhase(Phase.LATE)),

    WAILA_RENDER(new MixinBuilder("Waila").addRequiredMod(TargetedMod.WAILA)
        .addClientMixins("waila.OverlayRendererMixin", "waila.TooltipInvoker", "waila.TooltipMixin")
        .setApplyIf(() -> Config.wailaEnabled)
        .setPhase(Phase.LATE)),

    BOTANIA(new MixinBuilder("Botania").addRequiredMod(TargetedMod.BOTANIA)
        .addClientMixins("botania.RenderHelperMixin")
        .setApplyIf(() -> Config.botaniaEnabled)
        .setPhase(Phase.LATE)),

    BAUBLES_EXPANDED(new MixinBuilder("Baubles Expanded").addRequiredMod(TargetedMod.BAUBLES_EXPANDED)
        .addClientMixins("baublesExpanded.GuiBaublesButtonMixin")
        .setApplyIf(() -> Config.baublesExpandedEnabled)
        .setPhase(Phase.LATE)),

    RAILCRAFT(new MixinBuilder("Railcraft").addRequiredMod(TargetedMod.RAILCRAFT)
        .addClientMixins("railcraft.GuiContainerRailcraftMixin", "railcraft.ToolTipMixin")
        .setApplyIf(() -> Config.railcraftEnabled)
        .setPhase(Phase.LATE)),

    APPLIED_ENERGISTICS_AMOUNT(new MixinBuilder("Applied Energistics 2")
        .addRequiredMod(TargetedMod.APPLIED_ENERGISTICS_2)
        .addClientMixins("appliedenergistics2.GuiInterfaceTerminalMixin", "appliedenergistics2.GuiMEMonitorableMixin")
        .setApplyIf(() -> Config.appliedEnergisticsEnabled && Config.appliedEnergisticsAmountEnabled)
        .setPhase(Phase.LATE)),

    TINKERS_CONSTRUCT(new MixinBuilder("Tinkers Construct").addRequiredMod(TargetedMod.TINKERS_CONSTRUCT)
        .addClientMixins("tconstruct.SmelteryGuiMixin")
        .setApplyIf(() -> Config.tinkersConstructEnabled)
        .setPhase(Phase.LATE)),

    BINNIE_CORE(new MixinBuilder("Binnie Core").addRequiredMod(TargetedMod.BINNIE_CORE)
        .addClientMixins("binniecore.GuiCraftGUIMixin")
        .setApplyIf(() -> Config.binnieCoreEnabled)
        .setPhase(Phase.LATE)),

    WAWLA(new MixinBuilder("Waila").addRequiredMod(TargetedMod.WAWLA)
        .addClientMixins("wawla.AddonGenericTooltipsMixin")
        .setApplyIf(() -> Config.wawlaEnabled)
        .setPhase(Phase.LATE)),

    NEI_CUSTOM_DIAGRAM(new MixinBuilder("NEI Custom Diagram").addRequiredMod(TargetedMod.NEI_CUSTOM_DIAGRAM)
        .addClientMixins("neicustomdiagram.DrawMixin")
        .setApplyIf(() -> Config.neicustomdiagramEnabled)
        .setPhase(Phase.LATE)),

    GTNH_LIB(new MixinBuilder("GTNHLib").addRequiredMod(TargetedMod.GTNH_LIB)
        .addClientMixins("gtnhlib.AnimatedTooltipHandlerMixin")
        .setApplyIf(() -> Config.gtnhlibEnabled)
        .setPhase(Phase.LATE)),

    GREGTECH_LATE(new MixinBuilder("GregTech").addRequiredMod(TargetedMod.GREGTECH)
        .addClientMixins("gregtech.ItemFluidDisplayMixin")
        .setApplyIf(() -> Config.gregtechEnabled)
        .setPhase(Phase.LATE)),

    GREGTECH_EARLY(new MixinBuilder("GregTech").addRequiredMod(TargetedMod.GREGTECH)
        .addClientMixins("gregtech.GTGenericItemMixin")
        .setApplyIf(() -> Config.gregtechEnabled)
        .setPhase(Phase.EARLY)),

    AE2FC(new MixinBuilder("AE2 Fluid Crafting").addRequiredMod(TargetedMod.AE2FC)
        .addClientMixins("ae2fc.BlockCertusQuartzTankMixin")
        .setApplyIf(() -> Config.ae2fcEnabled)
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
