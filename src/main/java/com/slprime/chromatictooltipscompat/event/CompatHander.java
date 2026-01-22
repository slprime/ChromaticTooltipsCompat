package com.slprime.chromatictooltipscompat.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import org.lwjgl.input.Keyboard;

import com.slprime.chromatictooltips.api.TooltipLines;
import com.slprime.chromatictooltips.util.TooltipUtils;
import com.slprime.chromatictooltipscompat.Config;

import codechicken.nei.util.ItemStackKey;
import cpw.mods.fml.common.FMLCommonHandler;
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
    private static final Map<ItemStackKey, Set<TooltipLineModifier>> MODIFIERS = new HashMap<>();

    public static void registerHandler() {
        CompatHander instance = new CompatHander();
        FMLCommonHandler.instance()
            .bus()
            .register(instance);
        MinecraftForge.EVENT_BUS.register(instance);
    }

    public static void reload() {
        TOOLTIPS.clear();

        if (Config.gregtechEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("mbdesc.signAndFinalize.todisplay"),
                    TooltipLines.SHIFT_MODIFIER));
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("gt.behaviour.paintspray.infinite.tooltip.more_info"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.appliedEnergistics2Enabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("gui.appliedenergistics2.HoldShift"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.tinkersConstructEnabled) {
            TOOLTIPS.add(new TooltipLineModifier(translateToLocal("tooltip.shiftprompt"), TooltipLines.SHIFT_MODIFIER));
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocalFormatted("tictooltips.hold.key.for.materials", "Ctrl"),
                    TooltipLines.CTRL_MODIFIER));
        }

        if (Config.ae2fcEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("ae2fc.tooltip.ctrl_for_more"), TooltipLines.CTRL_MODIFIER));
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("ae2fc.tooltip.shift_for_more"), TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.ae2wctEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("gui.tooltips.ae2wct.PressShift"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.forestryEnabled) {
            TOOLTIPS.add(new TooltipLineModifier(translateToLocal("for.gui.tooltip.tmi"), TooltipLines.CTRL_MODIFIER));
            TOOLTIPS.add(new TooltipLineModifier(translateToLocal("for.gui.tooltip.tml"), TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.backpackEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("tooltip.more_information"), TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.binnieCoreEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("botany.item.tooltip.holdMore"), TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.logisticsPipesEnabled) {
            TOOLTIPS.add(new TooltipLineModifier(translateToLocal("misc.holdshift"), TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.logisticsPipesEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("thaumicenergistics.tooltip.itemstack.details"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.supersolarpanelEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("supsolpans.all.PressShift"), TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.gendustryEnabled) {
            TOOLTIPS
                .add(new TooltipLineModifier(translateToLocal("gendustry.label.shift"), TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.emtEnabled) {
            TOOLTIPS.add(new TooltipLineModifier(translateToLocal("emt.PressShift"), TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.AWWayofTimeEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("tooltip.ritualdiviner.moreinfo"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.storageDrawersEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("storageDrawers.drawers.sealed.descriptionShift"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.modularUIEnabled) {
            TOOLTIPS
                .add(new TooltipLineModifier(translateToLocal("modularui.tooltip.shift"), TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.modularUI2Enabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("modularui2.tooltip.shift"), TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.sfmEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("gui.sfm.TooltipExtraInfo"), TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.enderCoreEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("endercore.tooltip.showDetails"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.bloodArsenalEnabled) {
            TOOLTIPS.add(new TooltipLineModifier(translateToLocal("tooltip.shiftinfo"), TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.avaritiaEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(translateToLocal("tooltip.matter_cluster.desc2"), TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.baublesEnabled) {
            TOOLTIPS.add(new TooltipLineModifier(translateToLocal("tooltip.shiftprompt"), TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.holoinventoryEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("hologlasses.tooltip.hold_shift"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.akashictomeEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("akashictome.misc.shiftForInfo"),
                    TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.botaniaEnabled) {
            final String shiftinfo = EnumChatFormatting.getTextWithoutFormattingCodes(
                StatCollector.translateToLocal("botaniamisc.shiftinfo")
                    .replaceAll("&", "\u00a7"));

            TOOLTIPS.add(new TooltipLineModifier(shiftinfo, TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.spiceoflifeEnabled) {
            final String moreinfo = translateToLocalFormatted("spiceoflife.tooltip.hold.key.for.details", "Shift");

            TOOLTIPS.add(new TooltipLineModifier(moreinfo, TooltipLines.SHIFT_MODIFIER));
        }

        if (Config.adventurebackpackEnabled) {
            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("adventurebackpack:tooltips.hold.shift"),
                    TooltipLines.SHIFT_MODIFIER));

            TOOLTIPS.add(
                new TooltipLineModifier(
                    translateToLocal("adventurebackpack:tooltips.hold.ctrl"),
                    TooltipLines.CTRL_MODIFIER));

        }

        if (Config.draconicEvolutionEnabled) {
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

        if (Config.galacticraftcoreEnabled) {
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

        if (Config.computronicsEnabled) {
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

    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onItemTooltipEvent(ItemTooltipEvent event) {
        final ItemStackKey key = new ItemStackKey(event.itemStack);
        Set<TooltipLineModifier> modifiers = MODIFIERS.get(key);

        if (modifiers != null) {
            for (TooltipLineModifier rule : modifiers) {
                event.toolTip.removeIf(
                    s -> EnumChatFormatting.getTextWithoutFormattingCodes(s)
                        .contains(rule.line));
                event.toolTip.add(rule.modifier);
            }
        } else if (TooltipUtils.getMetaHash() == 0) {
            MODIFIERS.put(key, modifiers = new HashSet<>());

            for (TooltipLineModifier rule : TOOLTIPS) {
                if (event.toolTip.removeIf(
                    s -> EnumChatFormatting.getTextWithoutFormattingCodes(s)
                        .contains(rule.line))) {
                    modifiers.add(rule);
                }
            }

            for (TooltipLineModifier rule : modifiers) {
                event.toolTip.add(rule.modifier);
            }
        } else {
            for (TooltipLineModifier rule : TOOLTIPS) {
                if (event.toolTip.removeIf(
                    s -> EnumChatFormatting.getTextWithoutFormattingCodes(s)
                        .contains(rule.line))) {
                    event.toolTip.add(rule.modifier);
                }
            }
        }

    }

    private static String translateToLocal(String key) {
        return EnumChatFormatting.getTextWithoutFormattingCodes(StatCollector.translateToLocal(key));
    }

    private static String translateToLocalFormatted(String key, Object... format) {
        return EnumChatFormatting.getTextWithoutFormattingCodes(StatCollector.translateToLocalFormatted(key, format));
    }

}
