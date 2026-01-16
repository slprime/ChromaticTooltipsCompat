package com.slprime.chromatictooltipscompat.mixins.late.betterquesting;

import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;
import com.slprime.chromatictooltipscompat.ClientProxy;
import com.slprime.chromatictooltipscompat.ClientUtil;

import betterquesting.api.utils.RenderUtils;

@Mixin(RenderUtils.class)
public class RenderUtilsMixin {

    /**
     * @author SLPrime
     * @reason Replace Better Questing tooltip rendering with Chromatic Tooltips
     */
    @Overwrite(remap = false)
    public static void drawHoveringText(ItemStack stack, List<String> textLines, int mouseX, int mouseY,
        int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font) {
        ItemStack effectiveStack = stack;

        if (effectiveStack == null && ClientProxy.chromatic$currentStack != null) {
            effectiveStack = ClientProxy.chromatic$currentStack;
            ClientProxy.chromatic$currentStack = null;
        }

        TooltipHandler.drawHoveringText(
            TooltipHandler.builder()
                .stack(ClientUtil.prepareItemStack(effectiveStack))
                .lines(textLines)
                .context("betterquesting")
                .build());
    }

}
