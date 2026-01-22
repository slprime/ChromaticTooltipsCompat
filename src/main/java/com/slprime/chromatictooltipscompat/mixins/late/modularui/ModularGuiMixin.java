package com.slprime.chromatictooltipscompat.mixins.late.modularui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.gtnewhorizons.modularui.common.internal.wrapper.ModularGui;
import com.slprime.chromatictooltips.TooltipHandler;

@Mixin(ModularGui.class)
public class ModularGuiMixin {

    /**
     * @author SLPrime
     * @reason Replace Modular UI tooltip rendering with Chromatic Tooltips
     */
    @Overwrite(remap = false)
    protected void renderToolTip(ItemStack stack, int x, int y, List<String> extraLines,
        Function<List<String>, List<String>> overwriteItemStackTooltip) {
        List<String> lines = new ArrayList<>(extraLines);

        if (overwriteItemStackTooltip != null) {
            lines = overwriteItemStackTooltip.apply(lines);
        }

        TooltipHandler.drawHoveringText(stack, lines);
    }

}
