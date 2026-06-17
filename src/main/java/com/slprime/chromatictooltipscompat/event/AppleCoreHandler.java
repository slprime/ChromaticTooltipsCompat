package com.slprime.chromatictooltipscompat.event;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.slprime.chromatictooltips.event.FoodStatsEvent;
import com.slprime.chromatictooltips.event.RenderTooltipEvent;
import com.slprime.chromatictooltips.util.TooltipUtils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import squeek.applecore.api.AppleCoreAPI;
import squeek.applecore.api.food.FoodValues;
import squeek.applecore.client.TooltipOverlayHandler;

public class AppleCoreHandler {

    public static void registerHandler() {
        TooltipUtils.registerEventListener(new AppleCoreHandler());
    }

    @SubscribeEvent
    public void onTooltipRender(RenderTooltipEvent event) {
        TooltipOverlayHandler.toolTipX = event.x;
        TooltipOverlayHandler.toolTipY = event.y;
        TooltipOverlayHandler.toolTipW = event.width;
        TooltipOverlayHandler.toolTipH = event.height - 1;
    }

    @SubscribeEvent
    public void onFoodStatsEvent(FoodStatsEvent event) {
        final ItemStack stack = event.target.getItem();

        if (!AppleCoreAPI.accessor.isFood(stack)) {
            return;
        }

        final EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        final FoodValues defaultFoodValues = FoodValues.get(stack);
        final FoodValues modifiedFoodValues = player != null ? FoodValues.get(stack, player) : null;

        if (defaultFoodValues == null || modifiedFoodValues == null) {
            return;
        }

        if (defaultFoodValues.equals(modifiedFoodValues) && defaultFoodValues.hunger == 0
            && defaultFoodValues.saturationModifier == 0) {
            return;
        }

        event.hunger = Math.max(defaultFoodValues.hunger, modifiedFoodValues.hunger);
        event.saturationModifier = Math
            .max(defaultFoodValues.saturationModifier, modifiedFoodValues.saturationModifier);
    }
}
