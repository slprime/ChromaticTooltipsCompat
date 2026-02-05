package com.slprime.chromatictooltipscompat.mixins.late.appliedenergistics2;

import java.util.List;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import appeng.client.gui.implementations.GuiMEMonitorable;

@Mixin(GuiMEMonitorable.class)
public class GuiMEMonitorableMixin {

    /**
     * @author SLPrime
     * @reason Disable AE2's built-in tooltip handling to prevent conflicts with WAILA/CT
     */
    @Inject(method = "handleItemTooltip", at = @At("HEAD"), remap = false, cancellable = true)
    private void handleItemTooltip(ItemStack stack, int mouseX, int mouseY, List<String> currentToolTip,
        CallbackInfoReturnable<List<String>> ci) {
        ci.setReturnValue(currentToolTip);
        ci.cancel();
    }
}
