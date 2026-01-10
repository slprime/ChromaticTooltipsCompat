package com.slprime.chromatictooltipscompat.mixins.late.appliedenergistics2;

import java.util.List;

import net.minecraft.client.gui.FontRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;

import appeng.client.gui.implementations.GuiInterfaceTerminal;

@Mixin(GuiInterfaceTerminal.class)
public class GuiInterfaceTerminalMixin {

    /**
     * @author SLPrime
     * @reason Redirect AE2 tooltip rendering to ChromaticTooltips
     */
    @Overwrite(remap = false)
    public void drawHoveringText(List<String> textLines, int x, int y, FontRenderer font) {
        TooltipHandler.drawHoveringText(textLines);
    }

}
