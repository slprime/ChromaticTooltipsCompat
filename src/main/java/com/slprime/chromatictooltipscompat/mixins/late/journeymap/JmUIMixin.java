package com.slprime.chromatictooltipscompat.mixins.late.journeymap;

import java.util.List;

import net.minecraft.client.gui.FontRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;

import journeymap.client.ui.component.JmUI;

@Mixin(JmUI.class)
public abstract class JmUIMixin {

    /**
     * @author SLPrime
     * @reason Replace JourneyMap tooltip rendering with Chromatic Tooltips
     */
    @Overwrite(remap = false)
    protected void drawHoveringText(List tooltip, int mouseX, int mouseY, FontRenderer fontRenderer) {
        if (tooltip != null && !tooltip.isEmpty()) {
            TooltipHandler.drawHoveringText(tooltip);
        }
    }
}
