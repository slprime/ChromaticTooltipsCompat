package com.slprime.chromatictooltipscompat.mixins.late.hardcoreenderexpansion;

import java.util.Arrays;

import net.minecraft.client.gui.FontRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.slprime.chromatictooltips.TooltipHandler;

import chylex.hee.gui.helpers.GuiItemRenderHelper;

@Mixin(value = GuiItemRenderHelper.class, remap = false)
public abstract class GuiItemRenderHelperMixin {

    @Shadow(remap = false)
    private static String tooltipString;

    /**
     * @author SLPrime
     * @reason Replace Hardcore Ender Expansion tooltip rendering with Chromatic Tooltips.
     */
    @Overwrite(remap = false)
    public static void drawTooltip(GuiItemRenderHelper.ITooltipRenderer gui, FontRenderer fontRendererObj) {
        if (tooltipString != null) {
            TooltipHandler.drawHoveringText(Arrays.asList(tooltipString.split("\\n")));
            tooltipString = null;
        }
    }
}
