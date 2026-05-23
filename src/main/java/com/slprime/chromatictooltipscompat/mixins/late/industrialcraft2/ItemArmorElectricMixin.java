package com.slprime.chromatictooltipscompat.mixins.late.industrialcraft2;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import ic2.core.item.armor.ItemArmorElectric;

@Mixin(ItemArmorElectric.class)
public abstract class ItemArmorElectricMixin {

    @Shadow(remap = false)
    private final ThreadLocal<Boolean> allowDamaging = new ThreadLocal<>();

    /**
     * Prevents the armor from taking damage when the tooltip is being generated, which would cause the tooltip to show
     * incorrect durability values.
     */
    @Inject(method = "setDamage", at = @At("HEAD"), cancellable = true, remap = false)
    public void setDamage(ItemStack stack, int damage, CallbackInfo ci) {
        Boolean allow = this.allowDamaging.get();
        if (allow == null || !allow) {
            ci.cancel();
        }
    }

}
