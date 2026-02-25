package com.slprime.chromatictooltipscompat.mixins.late.logisticspipes;

import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;

import logisticspipes.utils.gui.GuiGraphics;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {

    /**
     * @author SLPrime
     * @reason Redirect Logistics Pipes' tooltip rendering to our own handler
     */
    @Overwrite(remap = false)
    public static void displayItemToolTip(Object[] tooltip, float pzLevel, int guiLeft, int guiTop, boolean forceAdd) {

        if (tooltip == null) {
            return;
        }

        final ItemStack itemStack = (ItemStack) tooltip[2];
        final List<?> textLines = tooltip.length > 4 ? (List<?>) tooltip[4] : Collections.emptyList();

        TooltipHandler.drawHoveringText(itemStack, textLines);
    }

}
