package com.slprime.chromatictooltipscompat.mixins.late.thaumcraft;

import java.util.Collections;

import net.minecraft.client.gui.FontRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.slprime.chromatictooltips.TooltipHandler;

import thaumcraft.client.gui.GuiResearchBrowser;

@Mixin(GuiResearchBrowser.class)
public class GuiResearchBrowserMixin {

    /**
     * @author SLPrime
     * @reason Replace Thaumcraft tooltip rendering in tabs with Chromatic Tooltips.
     */
    @Redirect(
        method = "drawScreen",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;III)I",
            ordinal = 0))
    private int drawCategoryTabTooltip(FontRenderer instance, String text, int x, int y, int color) {
        TooltipHandler.drawHoveringText(Collections.singletonList(text));
        return 0;
    }
}
