package com.slprime.chromatictooltipscompat.event;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import com.slprime.chromatictooltips.api.ItemStats;
import com.slprime.chromatictooltips.event.AttributeEnricherEvent;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import tconstruct.library.tools.ToolCore;

public class TConstructHandler {

    public static void registerHandler() {
        TConstructHandler instance = new TConstructHandler();
        FMLCommonHandler.instance()
            .bus()
            .register(instance);
        MinecraftForge.EVENT_BUS.register(instance);
    }

    @SubscribeEvent
    public void onAttributeEnricherEvent(AttributeEnricherEvent event) {

        if (event.context.getStack()
            .getItem() instanceof ToolCore toolCore) {
            final NBTTagCompound tags = event.context.getStack()
                .getTagCompound();

            if (tags != null && tags.hasKey("InfiTool")) {
                final int attack = (int) (tags.getCompoundTag("InfiTool")
                    .getInteger("Attack") * toolCore.getDamageModifier());
                event.stats.add(new ItemStats.AttackDamageStats(attack));
            }
        }

    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onItemTooltipEvent(ItemTooltipEvent event) {

        if (event.itemStack.getItem() instanceof ToolCore) {
            final String attackDamage = EnumChatFormatting.getTextWithoutFormattingCodes(
                StatCollector.translateToLocalFormatted("attribute.name.generic.attackDamage"));
            event.toolTip.removeIf(
                s -> EnumChatFormatting.getTextWithoutFormattingCodes(s)
                    .contains(attackDamage));
        }
    }

}
