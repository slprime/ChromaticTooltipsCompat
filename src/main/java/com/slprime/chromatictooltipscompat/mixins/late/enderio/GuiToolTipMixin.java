package com.slprime.chromatictooltipscompat.mixins.late.enderio;

import java.awt.Rectangle;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.enderio.core.client.gui.widget.GuiToolTip;

@Mixin(GuiToolTip.class)
public abstract class GuiToolTipMixin {

    @Shadow
    protected boolean visible;

    @Shadow
    protected Rectangle bounds;

    @Shadow
    private int lastMouseX = -1;

    @Shadow
    private int lastMouseY = -1;

    @Shadow
    protected abstract void updateText();

    /**
     * @author SLPrime
     * @reason Always update tooltip text to ensure Chromatic Tooltips displays correctly
     */
    @Overwrite(remap = false)
    public boolean shouldDraw() {
        if (!this.visible) {
            return false;
        }

        this.updateText();

        return bounds.contains(lastMouseX, lastMouseY);
    }
}
