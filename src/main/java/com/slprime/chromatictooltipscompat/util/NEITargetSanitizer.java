package com.slprime.chromatictooltipscompat.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import com.slprime.chromatictooltips.api.ITargetSanitizer;
import com.slprime.chromatictooltips.api.TooltipTarget;

import codechicken.nei.recipe.StackInfo;

public class NEITargetSanitizer implements ITargetSanitizer {

    @Override
    public TooltipTarget sanitize(TooltipTarget target) {

        if (target.isItem()) {
            final ItemStack copyStack = StackInfo.loadFromNBT(StackInfo.itemStackToNBT(target.getItem()));

            if (copyStack == null) {
                return target;
            }

            final ItemStack itemStack = StackInfo.normalizeRecipeQueryStack(copyStack);

            if (itemStack != null && StackInfo.isFluidDisplayItem(itemStack)) {
                final FluidStack fluidStack = StackInfo.getFluid(itemStack);

                if (fluidStack != null) {
                    final NBTTagCompound aNBT = itemStack.getTagCompound();
                    final long stackAmount = aNBT != null
                        ? (aNBT.hasKey("mFluidDisplayAmount") ? aNBT.getLong("mFluidDisplayAmount")
                            : aNBT.getLong("neiFluidDisplayAmount"))
                        : fluidStack.amount;
                    return TooltipTarget.ofFluid(fluidStack, stackAmount);
                }
            }

            final FluidStack fluid = StackInfo.getFluid(itemStack);

            if (fluid != null) {
                return target.withContainedFluid(fluid);
            }

            return TooltipTarget.ofItem(itemStack, target.getStackAmount());
        }

        return target;
    }

}
