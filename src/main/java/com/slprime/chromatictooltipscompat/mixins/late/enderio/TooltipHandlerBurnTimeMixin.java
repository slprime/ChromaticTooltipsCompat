package com.slprime.chromatictooltipscompat.mixins.late.enderio;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import crazypants.enderio.gui.TooltipHandlerBurnTime;

@Mixin(TooltipHandlerBurnTime.class)
public class TooltipHandlerBurnTimeMixin {

    @Redirect(
        method = "addCommonEntries",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/tileentity/TileEntityFurnace;getItemBurnTime(Lnet/minecraft/item/ItemStack;)I"))
    private int disableBurnTimeTooltip(ItemStack stack) {
        return 0;
    }

}
