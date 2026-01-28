package com.slprime.chromatictooltipscompat.mixins.late.gregtech;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gregtech.common.items.ItemFluidDisplay;

@Mixin(ItemFluidDisplay.class)
public class ItemFluidDisplayMixin {

    @Inject(method = "addAdditionalToolTips", at = @At("TAIL"), remap = false)
    protected void addAdditionalToolTips(List<String> aList, ItemStack aStack, EntityPlayer aPlayer, CallbackInfo ci) {
        final Fluid tFluid = FluidRegistry.getFluid(aStack.getItemDamage());

        if (tFluid != null) {
            final String line = StatCollector
                .translateToLocalFormatted("GT5U.tooltip.fluid.registry", tFluid.getName());
            aList.removeIf(s -> s.equals(line));
        }

    }

}
