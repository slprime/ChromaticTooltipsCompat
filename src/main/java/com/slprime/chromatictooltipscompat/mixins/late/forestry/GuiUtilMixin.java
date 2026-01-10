package com.slprime.chromatictooltipscompat.mixins.late.forestry;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;

import forestry.core.gui.GuiForestry;
import forestry.core.gui.GuiUtil;
import forestry.core.gui.tooltips.ToolTip;
import forestry.core.gui.tooltips.ToolTipLine;

@Mixin(GuiUtil.class)
public class GuiUtilMixin {

    /**
     * @author SLPrime
     * @reason Replace Forestry tooltip rendering with Chromatic Tooltips
     */
    @Overwrite(remap = false)
    public static void drawToolTips(GuiForestry<?, ?> gui, ToolTip toolTips, int mouseX, int mouseY) {

        if (toolTips != null && !toolTips.isEmpty()) {
            final List<String> list = new ArrayList<>();

            for (ToolTipLine tip : toolTips) {
                list.add(tip.toString() + (tip.getSpacing() > 0 ? "\u00A7h" : ""));
            }

            TooltipHandler.drawHoveringText(list);
        }
    }

}
