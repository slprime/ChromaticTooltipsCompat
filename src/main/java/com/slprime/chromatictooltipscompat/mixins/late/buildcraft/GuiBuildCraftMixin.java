package com.slprime.chromatictooltipscompat.mixins.late.buildcraft;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;

import buildcraft.core.lib.gui.GuiBuildCraft;
import buildcraft.core.lib.gui.tooltips.ToolTip;
import buildcraft.core.lib.gui.tooltips.ToolTipLine;

@Mixin(GuiBuildCraft.class)
public class GuiBuildCraftMixin {

    /**
     * @author SLPrime
     * @reason Replace BuildCraft tooltip rendering with Chromatic Tooltips
     */
    @Overwrite(remap = false)
    private void drawToolTips(ToolTip toolTips, int mouseX, int mouseY) {
        List<String> lines = new ArrayList<>();

        for (ToolTipLine tip : toolTips) {
            String line = tip.text;
            if (tip.color == -1) {
                line = "\u00a77" + line;
            } else {
                line = "\u00a7" + Integer.toHexString(tip.color) + line;
            }
            lines.add(line);
        }

        TooltipHandler.drawHoveringText(lines);
    }

}
