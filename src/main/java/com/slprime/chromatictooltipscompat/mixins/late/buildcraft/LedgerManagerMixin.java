package com.slprime.chromatictooltipscompat.mixins.late.buildcraft;

import java.lang.reflect.Method;
import java.util.Collections;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.slprime.chromatictooltips.TooltipHandler;

/**
 * @author SLPrime
 * @reason Replace BuildCraft ledger tooltip rendering with Chromatic Tooltips.
 */
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "buildcraft.core.lib.gui.GuiBuildCraft$LedgerManager", remap = false)
public class LedgerManagerMixin {

    private static Method getAtPositionMethod;
    private static Method getTooltipMethod;

    /**
     * @author SLPrime
     * @reason Replace BuildCraft ledger tooltip rendering with Chromatic Tooltips.
     */
    @Inject(
        method = "drawLedgers(II)V",
        at = @At(
            value = "INVOKE",
            target = "Lbuildcraft/core/lib/gui/GuiBuildCraft$LedgerManager;getAtPosition(II)Lbuildcraft/core/lib/gui/GuiBuildCraft$Ledger;"),
        cancellable = true,
        remap = false)
    private void injectDrawLedgers(int mouseX, int mouseY, CallbackInfo ci) {
        try {

            if (getAtPositionMethod == null) {
                getAtPositionMethod = getClass().getDeclaredMethod("getAtPosition", int.class, int.class);
                getAtPositionMethod.setAccessible(true);
            }

            final Object ledger = getAtPositionMethod.invoke(this, mouseX, mouseY);

            if (ledger != null) {

                if (getTooltipMethod == null) {
                    getTooltipMethod = ledger.getClass()
                        .getMethod("getTooltip");
                    getTooltipMethod.setAccessible(true);
                }

                final String tooltip = (String) getTooltipMethod.invoke(ledger);

                TooltipHandler.drawHoveringText(Collections.singletonList(tooltip));
            }

        } catch (Exception e) {}

        ci.cancel();
    }

}
