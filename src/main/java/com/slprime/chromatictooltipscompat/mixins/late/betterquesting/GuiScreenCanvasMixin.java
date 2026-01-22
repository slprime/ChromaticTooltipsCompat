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

import betterquesting.api2.client.gui.GuiScreenCanvas;

@Mixin(GuiScreenCanvas.class)
public class GuiScreenCanvasMixin {

    /**
     * @author SLPrime
     * @reason Suppress default BQ tooltip rendering when Chromatic Tooltips is active
     */
    @Overwrite(remap = false)
    private List<String> getItemToolTip(ItemStack stack) {
        return Arrays.asList("");
    }

    /**
     * @author SLPrime
     * @reason Replace Better Questing tooltip rendering with Chromatic Tooltips
     */
    @Inject(method = "renderToolTip", at = @At("HEAD"), cancellable = true)
    protected void renderToolTip(ItemStack stack, int x, int y, CallbackInfo ci) {
        TooltipHandler.drawHoveringText(
            TooltipHandler.builder()
                .stack(stack)
                .context("betterquesting")
                .build());
        ci.cancel();
    }

}
