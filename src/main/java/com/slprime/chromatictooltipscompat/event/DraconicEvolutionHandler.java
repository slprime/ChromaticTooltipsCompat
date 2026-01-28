package com.slprime.chromatictooltipscompat.event;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import com.brandon3055.draconicevolution.common.items.tools.DraconicDistructionStaff;
import com.brandon3055.draconicevolution.common.items.tools.baseclasses.ToolHandler;
import com.brandon3055.draconicevolution.common.items.weapons.DraconicSword;
import com.brandon3055.draconicevolution.common.items.weapons.WyvernSword;
import com.slprime.chromatictooltips.api.ItemStats;
import com.slprime.chromatictooltips.api.ItemStats.StatsOperator;
import com.slprime.chromatictooltips.event.AttributeEnricherEvent;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DraconicEvolutionHandler {

    public static void registerHandler() {
        DraconicEvolutionHandler instance = new DraconicEvolutionHandler();
        FMLCommonHandler.instance()
            .bus()
            .register(instance);
        MinecraftForge.EVENT_BUS.register(instance);
    }

    @SubscribeEvent
    public void onAttributeEnricherEvent(AttributeEnricherEvent event) {
        final ItemStack stack = event.context.getItem();

        if (stack != null && (stack.getItem() instanceof DraconicSword || stack.getItem() instanceof WyvernSword
            || stack.getItem() instanceof DraconicDistructionStaff)) {
            event.stats.add(new ItemStats.AttackDamageStats(ToolHandler.getBaseAttackDamage(stack)));
            event.stats.add(
                new ItemStats(
                    StatCollector.translateToLocal("info.de.bonusHealthDamage.txt"),
                    20.0,
                    StatsOperator.MULTIPLY_TOTAL,
                    null));
        }

    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onItemTooltipEvent(ItemTooltipEvent event) {
        final ItemStack stack = event.itemStack;

        if (stack != null && (stack.getItem() instanceof DraconicSword || stack.getItem() instanceof WyvernSword
            || stack.getItem() instanceof DraconicDistructionStaff)) {
            final String attackDamage = EnumChatFormatting
                .getTextWithoutFormattingCodes(StatCollector.translateToLocal("info.de.attackDamage.txt"));
            final String bonusHealthDamage = EnumChatFormatting
                .getTextWithoutFormattingCodes(StatCollector.translateToLocal("info.de.bonusHealthDamage.txt"));

            event.toolTip.removeIf(
                s -> EnumChatFormatting.getTextWithoutFormattingCodes(s)
                    .contains(attackDamage)
                    || EnumChatFormatting.getTextWithoutFormattingCodes(s)
                        .contains(bonusHealthDamage));
        }
    }

}
