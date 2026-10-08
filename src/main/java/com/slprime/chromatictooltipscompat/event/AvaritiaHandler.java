package com.slprime.chromatictooltipscompat.event;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import com.slprime.chromatictooltips.api.ItemStats;
import com.slprime.chromatictooltips.event.AttributeEnricherEvent;
import com.slprime.chromatictooltips.event.ItemInfoEnricherEvent;
import com.slprime.chromatictooltips.util.TooltipUtils;
import com.slprime.chromatictooltipscompat.CompatConfig;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fox.spiteful.avaritia.LudicrousText;
import fox.spiteful.avaritia.compat.draconicevolution.InfinityToolRuntimeHelpers;
import fox.spiteful.avaritia.items.tools.ItemSwordInfinity;

public class AvaritiaHandler {

    public static void registerHandler() {
        TooltipUtils.registerEventListener(new AvaritiaHandler());
    }

    @SubscribeEvent
    public void onAttributeEnricherEvent(AttributeEnricherEvent event) {
        final ItemStack stack = event.target.getItem();

        if (stack != null && stack.getItem() instanceof ItemSwordInfinity) {
            event.stats.removeIf(stats -> stats instanceof ItemStats.AttackDamageStats);

            if (!CompatConfig.avaritiaInfinityDamageTooltipEnabled) {
                event.stats.add(new ItemStats.AttackDamageStats(InfinityToolRuntimeHelpers.getSwordDamage(stack)));
            }
        }
    }

    @SubscribeEvent
    public void onItemInfoEnricherEvent(ItemInfoEnricherEvent event) {
        final ItemStack stack = event.target.getItem();

        if (stack != null && stack.getItem() instanceof ItemSwordInfinity
            && CompatConfig.avaritiaInfinityDamageTooltipEnabled) {
            event.tooltip.add(
                0,
                EnumChatFormatting.BLUE + "+"
                    + LudicrousText.makeFabulous(StatCollector.translateToLocal("tip.infinity"))
                    + " "
                    + EnumChatFormatting.BLUE
                    + StatCollector.translateToLocal("attribute.name.generic.attackDamage"));
        }
    }

}
