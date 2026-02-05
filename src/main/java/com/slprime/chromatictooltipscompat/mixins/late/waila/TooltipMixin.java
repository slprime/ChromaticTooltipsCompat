package com.slprime.chromatictooltipscompat.mixins.late.waila;

import java.awt.Point;

import net.minecraftforge.common.config.Configuration;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import mcp.mobius.waila.api.impl.ConfigHandler;
import mcp.mobius.waila.overlay.Tooltip;
import mcp.mobius.waila.utils.Constants;

@Mixin(Tooltip.class)
public abstract class TooltipMixin {

    @Shadow(remap = false)
    Point pos;

    @Shadow(remap = false)
    boolean hasIcon = false;

    @Shadow(remap = false)
    private int w;

    @Shadow(remap = false)
    private int h;

    @Shadow(remap = false)
    private int offsetX;

    @Shadow(remap = false)
    private int x;

    @Shadow(remap = false)
    private int y;

    @Shadow(remap = false)
    private int ty;

    @Shadow(remap = false)
    private int maxStringW;

    @Shadow(remap = false)
    protected abstract int getRenderableTotalHeight();

    /**
     * @author SLPrime
     * @reason Use configured WAILA position from Chromatic Tooltips config
     */
    @Overwrite(remap = false)
    private void computePositionAndSize(boolean hasIcon) {
        this.pos = new Point(
            ConfigHandler.instance()
                .getConfig(Configuration.CATEGORY_GENERAL, Constants.CFG_WAILA_POSX, 0),
            ConfigHandler.instance()
                .getConfig(Configuration.CATEGORY_GENERAL, Constants.CFG_WAILA_POSY, 0));
        this.hasIcon = hasIcon;

        this.offsetX = (hasIcon ? 20 : 0);

        w = this.maxStringW + this.offsetX;
        h = this.getRenderableTotalHeight();
        ty = (h - this.getRenderableTotalHeight()) / 2 + 1;

        x = 0;
        y = 0;
    }

}
