package com.slprime.chromatictooltipscompat.mixins.late.buildcraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import buildcraft.core.lib.gui.tooltips.ToolTip;

@Mixin(ToolTip.class)
public class ToolTipMixin {

    /**
     * @author SLPrime
     * @reason Always report that BuildCraft tooltips are ready to be drawn, since
     *         Chromatic Tooltips handles the rendering now.
     */
    @Overwrite(remap = false)
    public boolean isReady() {
        return true;
    }

}
