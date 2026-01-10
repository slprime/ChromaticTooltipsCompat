package com.slprime.chromatictooltipscompat.mixins.late.betterquesting;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;

import betterquesting.api2.client.gui.GuiContainerCanvas;

@Mixin(GuiContainerCanvas.class)
public class GuiContainerCanvasMixin {

    /**
     * @author SLPrime
     * @reason Suppress Better Questing item tooltips
     */
    @Overwrite(remap = false)
    private List<String> getItemToolTip(ItemStack stack) {
        return Arrays.asList("");
    }

    /**
     * @author SLPrime
     * @reason Replace Better Questing tooltip rendering with Chromatic Tooltips
     */
    @Overwrite(remap = false)
    protected void renderToolTip(ItemStack stack, int x, int y) {
        TooltipHandler.drawHoveringText(
            TooltipHandler.builder()
                .stack(stack)
                .context("betterquesting")
                .build());
    }

}
