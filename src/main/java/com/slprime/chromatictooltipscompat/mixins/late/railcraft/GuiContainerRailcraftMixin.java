package com.slprime.chromatictooltipscompat.mixins.late.railcraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;

import mods.railcraft.client.gui.GuiContainerRailcraft;
import mods.railcraft.common.gui.tooltips.ToolTip;

@Mixin(GuiContainerRailcraft.class)
public class GuiContainerRailcraftMixin {

    /**
     * @author SLPrime
     * @reason Replace Railcraft tooltip rendering with Chromatic Tooltips
     */
    @Overwrite(remap = false)
    private void drawToolTips(ToolTip toolTips, int mouseX, int mouseY) {
        TooltipHandler.drawHoveringText(toolTips.convertToStrings());
    }

}
