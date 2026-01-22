package com.slprime.chromatictooltipscompat.util;

import com.slprime.chromatictooltips.api.ITooltipRequestResolver;
import com.slprime.chromatictooltips.api.TooltipRequest;
import com.slprime.chromatictooltipscompat.ChromaticTooltipsCompat.ModIds;
import com.slprime.chromatictooltipscompat.Config;

import codechicken.nei.recipe.StackInfo;
import cpw.mods.fml.common.Loader;
import gregtech.api.util.GTUtility;
import gregtech.common.items.ItemFluidDisplay;

public class NEITooltipRequestResolver implements ITooltipRequestResolver {

    @Override
    public boolean resolve(TooltipRequest request) {

        if (request.itemStack != null) {
            request.itemStack = StackInfo.loadFromNBT(StackInfo.itemStackToNBT(request.itemStack));
            request.itemStack = StackInfo.normalizeRecipeQueryStack(request.itemStack);

            if (request.itemStack != null && request.fluidStack == null
                && Config.gregtechEnabled
                && Loader.isModLoaded(ModIds.GT5)
                && request.itemStack.getItem() instanceof ItemFluidDisplay) {
                request.fluidStack = GTUtility.getFluidFromDisplayStack(request.itemStack);
                if (request.fluidStack != null) {
                    request.itemStack = null;
                    return true;
                }
            }
        }

        return false;
    }

}
