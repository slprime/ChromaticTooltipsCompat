package com.slprime.chromatictooltipscompat.mixins.late.industrialcraft2;

import net.minecraft.client.Minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import codechicken.nei.recipe.GuiRecipe;
import ic2.neiIntegration.core.recipehandler.FluidCannerRecipeHandler;

/**
 * Fixes ClassCastException when non-GuiRecipe GUI is passed to FluidCannerRecipeHandler.
 * The drawLiquidTooltip method attempts to cast currentScreen to GuiRecipe (line 143),
 * but other GUIs (like GuiSatchelsInventory from Satchels mod) can be the current screen,
 * causing a crash.
 *
 * ClassCastException: makamys.satchels.gui.GuiSatchelsInventory cannot be cast to codechicken.nei.recipe.GuiRecipe
 * at
 * ic2.neiIntegration.core.recipehandler.FluidCannerRecipeHandler.drawLiquidTooltip(FluidCannerRecipeHandler.java:143)
 *
 * Solution: Guard drawLiquidTooltip at HEAD to cancel if currentScreen is not GuiRecipe
 * 
 * @author SLPrime
 */
@Mixin(value = FluidCannerRecipeHandler.class, priority = 999)
public class FluidCannerRecipeHandlerMixin {

    /**
     * Cancel drawLiquidTooltip execution if the current screen is not a GuiRecipe.
     * This prevents ClassCastException from being thrown.
     */
    @Inject(method = "drawLiquidTooltip", at = @At("HEAD"), cancellable = true, remap = false)
    private void guardDrawLiquidTooltip(CallbackInfo ci) {
        // Check if currentScreen is actually a GuiRecipe before proceeding
        if (!(Minecraft.getMinecraft().currentScreen instanceof GuiRecipe)) {
            // Cancel the method execution if it's not a GuiRecipe
            ci.cancel();
        }
    }

}
