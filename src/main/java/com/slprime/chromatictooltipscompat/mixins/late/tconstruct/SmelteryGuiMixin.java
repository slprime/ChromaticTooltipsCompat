package com.slprime.chromatictooltipscompat.mixins.late.tconstruct;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;

import tconstruct.smeltery.gui.SmelteryGui;

@Mixin(SmelteryGui.class)
public class SmelteryGuiMixin {

    /**
     * @author SLPrime
     * @reason Use Chromatic Tooltips' tooltip rendering instead of TConstruct.
     */
    @Overwrite(remap = false)
    private void drawToolTip(List<String> tooltip, int x, int y) {
        TooltipHandler.drawHoveringText(tooltip);
    }

}
