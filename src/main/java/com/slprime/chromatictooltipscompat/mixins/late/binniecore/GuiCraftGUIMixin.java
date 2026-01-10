package com.slprime.chromatictooltipscompat.mixins.late.binniecore;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;

import binnie.core.craftgui.geometry.IPoint;
import binnie.core.craftgui.minecraft.GuiCraftGUI;
import binnie.core.craftgui.minecraft.MinecraftTooltip;

@Mixin(GuiCraftGUI.class)
public class GuiCraftGUIMixin {

    /**
     * @author SLPrime
     * @reason Redirect tooltip rendering to Chromatic Tooltips
     */
    @Overwrite(remap = false)
    public void renderTooltip(IPoint mousePosition, MinecraftTooltip tooltip) {
        TooltipHandler.drawHoveringText(tooltip.getList());
    }
}
