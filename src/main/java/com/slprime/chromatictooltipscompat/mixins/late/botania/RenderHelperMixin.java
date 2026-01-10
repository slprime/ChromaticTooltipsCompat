package com.slprime.chromatictooltipscompat.mixins.late.botania;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;

import vazkii.botania.client.core.helper.RenderHelper;

@Mixin(RenderHelper.class)
public class RenderHelperMixin {

    /**
     * @author SLPrime
     * @reason Redirect tooltip rendering to Chromatic Tooltips
     */
    @Overwrite(remap = false)
    public static void renderTooltip(int x, int y, List<String> tooltipData, int color, int color2) {
        TooltipHandler.drawHoveringText(tooltipData);
    }

}
