package com.slprime.chromatictooltipscompat;

import net.minecraft.item.ItemStack;

import com.slprime.chromatictooltipscompat.ChromaticTooltipsCompat.ModIds;
import com.slprime.chromatictooltipscompat.event.NEIHandler;

import cpw.mods.fml.common.Loader;

public class ClientUtil {

    public static ItemStack prepareItemStack(ItemStack stack) {

        if (stack != null && Config.notEnoughItemsEnabled && Loader.isModLoaded(ModIds.NEI)) {
            return NEIHandler.prepareNEIItemStack(stack);
        }

        return stack;
    }

}
