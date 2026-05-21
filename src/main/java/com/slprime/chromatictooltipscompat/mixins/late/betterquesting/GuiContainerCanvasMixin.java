package com.slprime.chromatictooltipscompat.mixins.late.betterquesting;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

    @Inject(method = "renderToolTip", at = @At("HEAD"), cancellable = true)
    protected void renderToolTip(ItemStack stack, int x, int y, CallbackInfo ci) {
        TooltipHandler.drawHoveringText(
            TooltipHandler.builder()
                .target(stack)
                .context("betterquesting")
                .build());
        ci.cancel();
    }

}
