package com.slprime.chromatictooltipscompat.mixins.late.modularui;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.gtnewhorizons.modularui.api.drawable.GuiHelper;
import com.gtnewhorizons.modularui.api.math.Alignment;
import com.gtnewhorizons.modularui.api.math.Pos2d;
import com.gtnewhorizons.modularui.api.math.Size;
import com.slprime.chromatictooltips.TooltipHandler;

@Mixin(GuiHelper.class)
public class GuiHelperMixin {

    /**
     * @author SLPrime
     * @reason Replace Modular UI tooltip rendering with Chromatic Tooltips
     */
    @Overwrite(remap = false)
    public static void drawHoveringTextFormatted(List<String> lines, List<Integer> colors, Pos2d mousePos,
        Size screenSize, int maxWidth, float scale, boolean forceShadow, Alignment alignment,
        boolean hasSpaceAfterFirstLine) {
        TooltipHandler.drawHoveringText(null, lines);
    }

}
