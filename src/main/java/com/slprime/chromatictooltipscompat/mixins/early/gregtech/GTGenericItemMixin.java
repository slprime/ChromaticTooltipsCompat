package com.slprime.chromatictooltipscompat.mixins.early.gregtech;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gregtech.api.items.GTGenericItem;

@Mixin(GTGenericItem.class)
public class GTGenericItemMixin {

    /**
     * @author SLPrime
     * @reason Remove durability info from GregTech items
     */
    @Inject(method = "addInformation", at = @At("RETURN"))
    private void afterAddInformation(ItemStack aStack, EntityPlayer aPlayer, List<String> aList, boolean aF3_H,
        CallbackInfo ci) {
        if (aStack.getMaxDamage() > 0 && !aStack.getItem()
            .getHasSubtypes()) {
            String damageInfo = (aStack.getMaxDamage() - aStack.getItemDamage()) + " / " + aStack.getMaxDamage();
            aList.removeIf(line -> damageInfo.equals(line));
        }
    }
}
