package com.slprime.chromatictooltipscompat.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import com.slprime.chromatictooltips.api.ITargetSanitizer;
import com.slprime.chromatictooltips.api.TooltipTarget;
import com.slprime.chromatictooltipscompat.ChromaticTooltipsCompat.ModIds;
import com.slprime.chromatictooltipscompat.Config;

import codechicken.nei.recipe.StackInfo;
import cpw.mods.fml.common.Loader;
import gregtech.api.util.GTUtility;
import gregtech.common.items.ItemFluidDisplay;

public class NEITargetSanitizer implements ITargetSanitizer {

    @Override
    public TooltipTarget sanitize(TooltipTarget target) {

        if (target.isItem()) {
            final ItemStack itemStack = StackInfo
                .normalizeRecipeQueryStack(StackInfo.loadFromNBT(StackInfo.itemStackToNBT(target.getItem())));

            if (itemStack != null && Config.gregtechEnabled
                && Loader.isModLoaded(ModIds.GT5)
                && itemStack.getItem() instanceof ItemFluidDisplay) {
                final FluidStack fluidStack = GTUtility.getFluidFromDisplayStack(itemStack);
                final NBTTagCompound aNBT = itemStack.getTagCompound();
                final long stackAmount = aNBT != null ? aNBT.getLong("mFluidDisplayAmount") : fluidStack.amount;

                if (fluidStack != null) {
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
