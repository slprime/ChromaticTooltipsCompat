package com.slprime.chromatictooltipscompat.event;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import org.lwjgl.input.Keyboard;

import com.slprime.chromatictooltips.api.TooltipLines;
import com.slprime.chromatictooltipscompat.CompatConfig;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CompatHander {

    protected static class TooltipLineModifier {

        public final String modifier;
        public final String line;

        public TooltipLineModifier(String line, String modifier) {
            this.line = line;
            this.modifier = modifier;
        }
    }

    private static final List<TooltipLineModifier> TOOLTIPS = new ArrayList<>();

    public static void registerHandler() {
        MinecraftForge.EVENT_BUS.register(new CompatHander());
    }

    public static void reload() {
        TOOLTIPS.clear();

        if (CompatConfig.gregtechEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("mbdesc.signAndFinalize.todisplay"),
                    TooltipLines.SHIFT_MODIFIER));
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("gt.behaviour.paintspray.infinite.tooltip.more_info"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.appliedEnergisticsEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("gui.appliedenergistics2.HoldShift"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.tinkersConstructEnabled) {
            TOOLTIPS.add(new TooltipLineModifier(translateToLocal("tooltip.shiftprompt"), TooltipLines.SHIFT_MODIFIER));
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocalFormatted("tictooltips.hold.key.for.materials", "Ctrl"),
                    TooltipLines.CTRL_MODIFIER));
        }

        if (CompatConfig.ae2fcEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("ae2fc.tooltip.ctrl_for_more"), TooltipLines.CTRL_MODIFIER));
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("ae2fc.tooltip.shift_for_more"), TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.ae2wctEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("gui.tooltips.ae2wct.PressShift"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.forestryEnabled) {
            TOOLTIPS.add(new TooltipLineModifier(translateToLocal("for.gui.tooltip.tmi"), TooltipLines.CTRL_MODIFIER));
            TOOLTIPS.add(new TooltipLineModifier(translateToLocal("for.gui.tooltip.tml"), TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.backpackEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("tooltip.more_information"), TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.binnieCoreEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("botany.item.tooltip.holdMore"), TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.logisticsPipesEnabled) {
            TOOLTIPS.add(new TooltipLineModifier(translateToLocal("misc.holdshift"), TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.thaumicenergisticsEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("thaumicenergistics.tooltip.itemstack.details"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.supersolarpanelEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("supsolpans.all.PressShift"), TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.gendustryEnabled) {
            TOOLTIPS
                .add(new TooltipLineModifier(translateToLocal("gendustry.label.shift"), TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.emtEnabled) {
            TOOLTIPS.add(new TooltipLineModifier(translateToLocal("emt.PressShift"), TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.AWWayofTimeEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("tooltip.ritualdiviner.moreinfo"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.storageDrawersEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("storageDrawers.drawers.sealed.descriptionShift"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.modularUIEnabled) {
            TOOLTIPS
                .add(new TooltipLineModifier(translateToLocal("modularui.tooltip.shift"), TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.modularUI2Enabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("modularui2.tooltip.shift"), TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.sfmEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("gui.sfm.TooltipExtraInfo"), TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.enderCoreEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("endercore.tooltip.showDetails"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.bloodArsenalEnabled) {
            TOOLTIPS.add(new TooltipLineModifier(translateToLocal("tooltip.shiftinfo"), TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.avaritiaEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("tooltip.matter_cluster.desc2"), TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.baublesEnabled) {
            TOOLTIPS.add(new TooltipLineModifier(translateToLocal("tooltip.shiftprompt"), TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.holoinventoryEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("hologlasses.tooltip.hold_shift"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.akashictomeEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("akashictome.misc.shiftForInfo"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.botaniaEnabled) {
            TOOLTIPS
                .add(new TooltipLineModifier(translateToLocal("botaniamisc.shiftinfo"), TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.spiceoflifeEnabled) {
            final String moreinfo = translateToLocalFormatted("spiceoflife.tooltip.hold.key.for.details", "Shift");

            TOOLTIPS.add(new TooltipLineModifier(moreinfo, TooltipLines.SHIFT_MODIFIER));
        }

        if (CompatConfig.adventurebackpackEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("adventurebackpack:tooltips.hold.shift"),
                    TooltipLines.SHIFT_MODIFIER));

            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("adventurebackpack:tooltips.hold.ctrl"),
                    TooltipLines.CTRL_MODIFIER));

        }

        if (CompatConfig.draconicEvolutionEnabled) {
            final String ctrlinfo = translateToLocal("info.de.hold.txt") + " "
                + translateToLocal("info.de.ctrl.txt")
                + " "
                + translateToLocal("info.de.forUpgrades.txt");
            final String shiftinfo = translateToLocal("info.de.hold.txt") + " "
                + translateToLocal("info.de.shift.txt")
                + " "
                + translateToLocal("info.de.forDetails.txt");

            TOOLTIPS.add(new TooltipLineModifier(shiftinfo, TooltipLines.SHIFT_MODIFIER));
            TOOLTIPS.add(new TooltipLineModifier(ctrlinfo, TooltipLines.CTRL_MODIFIER));
        }

        if (CompatConfig.galacticraftcoreEnabled) {
            final String shift1 = translateToLocalFormatted(
                "itemDesc.shift.name",
                GameSettings.getKeyDisplayString(Keyboard.KEY_LSHIFT));
            final String shift2 = translateToLocalFormatted(
                "itemDesc.shift.name",
                GameSettings.getKeyDisplayString(Keyboard.KEY_RSHIFT));

            final String alt1 = translateToLocalFormatted(
                "itemDesc.shift.name",
                GameSettings.getKeyDisplayString(Keyboard.KEY_LMENU));
            final String alt2 = translateToLocalFormatted(
                "itemDesc.shift.name",
                GameSettings.getKeyDisplayString(Keyboard.KEY_RMENU));

            final String ctrl1 = translateToLocalFormatted(
                "itemDesc.shift.name",
                GameSettings
                    .getKeyDisplayString(Minecraft.isRunningOnMac ? Keyboard.KEY_LMETA : Keyboard.KEY_LCONTROL));
            final String ctrl2 = translateToLocalFormatted(
                "itemDesc.shift.name",
                GameSettings
                    .getKeyDisplayString(Minecraft.isRunningOnMac ? Keyboard.KEY_RMETA : Keyboard.KEY_RCONTROL));

            TOOLTIPS.add(new TooltipLineModifier(shift1, TooltipLines.SHIFT_MODIFIER));
            TOOLTIPS.add(new TooltipLineModifier(shift2, TooltipLines.SHIFT_MODIFIER));

            TOOLTIPS.add(new TooltipLineModifier(alt1, TooltipLines.ALT_MODIFIER));
            TOOLTIPS.add(new TooltipLineModifier(alt2, TooltipLines.ALT_MODIFIER));

            TOOLTIPS.add(new TooltipLineModifier(ctrl1, TooltipLines.CTRL_MODIFIER));
            TOOLTIPS.add(new TooltipLineModifier(ctrl2, TooltipLines.CTRL_MODIFIER));
        }

        if (CompatConfig.computronicsEnabled) {
            final String shift1 = translateToLocalFormatted(
                "oc:tooltip.TooLong",
                GameSettings.getKeyDisplayString(Keyboard.KEY_LSHIFT));
            final String shift2 = translateToLocalFormatted(
                "oc:tooltip.TooLong",
                GameSettings.getKeyDisplayString(Keyboard.KEY_RSHIFT));

            final String alt1 = translateToLocalFormatted(
                "oc:tooltip.TooLong",
                GameSettings.getKeyDisplayString(Keyboard.KEY_LMENU));
            final String alt2 = translateToLocalFormatted(
                "oc:tooltip.TooLong",
                GameSettings.getKeyDisplayString(Keyboard.KEY_RMENU));

            final String ctrl1 = translateToLocalFormatted(
                "oc:tooltip.TooLong",
                GameSettings
                    .getKeyDisplayString(Minecraft.isRunningOnMac ? Keyboard.KEY_LMETA : Keyboard.KEY_LCONTROL));
            final String ctrl2 = translateToLocalFormatted(
                "oc:tooltip.TooLong",
                GameSettings
                    .getKeyDisplayString(Minecraft.isRunningOnMac ? Keyboard.KEY_RMETA : Keyboard.KEY_RCONTROL));

            TOOLTIPS.add(new TooltipLineModifier(shift1, TooltipLines.SHIFT_MODIFIER));
            TOOLTIPS.add(new TooltipLineModifier(shift2, TooltipLines.SHIFT_MODIFIER));

            TOOLTIPS.add(new TooltipLineModifier(alt1, TooltipLines.ALT_MODIFIER));
            TOOLTIPS.add(new TooltipLineModifier(alt2, TooltipLines.ALT_MODIFIER));

            TOOLTIPS.add(new TooltipLineModifier(ctrl1, TooltipLines.CTRL_MODIFIER));
            TOOLTIPS.add(new TooltipLineModifier(ctrl2, TooltipLines.CTRL_MODIFIER));
        }

        if (CompatConfig.baublesExpandedEnabled) {
            TOOLTIPS.add(new TooltipLineModifier(translateToLocal("tooltip.shiftprompt"), TooltipLines.SHIFT_MODIFIER));
        }

    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onItemTooltipEvent(ItemTooltipEvent event) {
        for (TooltipLineModifier rule : TOOLTIPS) {
            if (event.toolTip.removeIf(
                s -> EnumChatFormatting.getTextWithoutFormattingCodes(s)
                    .contains(rule.line))) {
                event.toolTip.add(rule.modifier);
            }
        }
    }

    private static String translateToLocal(String key) {
        return EnumChatFormatting.getTextWithoutFormattingCodes(
            StatCollector.translateToLocal(key)
                .replaceAll("&[0-9a-fk-or]", ""));
    }

    private static String translateToLocalFormatted(String key, Object... format) {
        return EnumChatFormatting.getTextWithoutFormattingCodes(
            StatCollector.translateToLocalFormatted(key, format)
                .replaceAll("&[0-9a-fk-or]", ""));
    }

}
