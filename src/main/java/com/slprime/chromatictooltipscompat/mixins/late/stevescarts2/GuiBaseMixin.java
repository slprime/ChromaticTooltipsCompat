package com.slprime.chromatictooltipscompat.mixins.late.stevescarts2;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;

import vswe.stevescarts.Interfaces.GuiBase;

@Mixin(GuiBase.class)
public class GuiBaseMixin {

    /**
     * @author SLPrime
     * @reason Replace Steve's Carts 2 tooltip rendering with Chromatic Tooltips
     */
    @Overwrite(remap = false)
    public void drawMouseOver(List text, int x, int y) {
        if (text != null && !text.isEmpty()) {
            TooltipHandler.drawHoveringText(text);
        }
    }
}
