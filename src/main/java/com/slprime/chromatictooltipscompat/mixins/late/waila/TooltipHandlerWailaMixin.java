package com.slprime.chromatictooltipscompat.mixins.late.waila;

import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import mcp.mobius.waila.handlers.nei.TooltipHandlerWaila;

@Mixin(TooltipHandlerWaila.class)
public class TooltipHandlerWailaMixin {

    /**
     * @author SLPrime
     * @reason Prevent WAILA from modifying item tooltips
     */
    @Overwrite(remap = false)
    public List<String> handleItemTooltip(GuiContainer arg0, ItemStack itemstack, int arg2, int arg3,
        List<String> currenttip) {
        return currenttip;
    }

}
