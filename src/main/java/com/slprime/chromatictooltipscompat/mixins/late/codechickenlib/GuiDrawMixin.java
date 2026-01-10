package com.slprime.chromatictooltipscompat.mixins.late.codechickenlib;

import java.util.List;

import net.minecraft.client.gui.FontRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;

import codechicken.lib.gui.GuiDraw;

@Mixin(GuiDraw.class)
public class GuiDrawMixin {

    /**
     * @author SLPrime
     * @reason Replace NEI tooltip rendering with Chromatic Tooltips
     */
    @Overwrite(remap = false)
    public static void drawMultilineTip(FontRenderer font, int x, int y, List<String> list, int bgStart, int bgEnd,
        int borderStart, int borderEnd) {
        TooltipHandler.drawHoveringText(list);
    }

}
