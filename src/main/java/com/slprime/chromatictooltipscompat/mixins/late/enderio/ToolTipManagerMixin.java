package com.slprime.chromatictooltipscompat.mixins.late.enderio;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.enderio.core.client.gui.ToolTipManager;
import com.enderio.core.client.gui.ToolTipManager.ToolTipRenderer;
import com.enderio.core.client.gui.widget.GuiToolTip;
import com.slprime.chromatictooltips.TooltipHandler;

@Mixin(ToolTipManager.class)
public class ToolTipManagerMixin {

    /**
     * @author SLPrime
     * @reason Replace Ender IO tooltip rendering with Chromatic Tooltips
     */
    @Overwrite(remap = false)
    protected void drawTooltip(GuiToolTip toolTip, int mouseX, int mouseY, ToolTipRenderer renderer) {
        final List<String> list = toolTip.getToolTipText();
        if (list != null) {
            TooltipHandler.drawHoveringText(list);
        }
    }

}
