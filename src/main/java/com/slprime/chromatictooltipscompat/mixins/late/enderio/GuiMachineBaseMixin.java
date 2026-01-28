package com.slprime.chromatictooltipscompat.mixins.late.enderio;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.enderio.core.client.gui.widget.GuiToolTip;

import crazypants.enderio.machine.gui.GuiMachineBase;

@Mixin(GuiMachineBase.class)
public class GuiMachineBaseMixin {

    /**
     * @author SLPrime
     */
    @Shadow
    private List<GuiToolTip> progressTooltips;

    /**
     * @author SLPrime
     * @reason Disable Ender IO progress tooltips to avoid conflicts with Chromatic
     *         Tooltips
     */
    @Inject(method = "updateProgressTooltips", at = @At("TAIL"), remap = false)
    protected void updateProgressTooltipsTail(int scaledProgress, float progress, CallbackInfo ci) {
        if (progressTooltips == null) {
            return;
        }

        for (GuiToolTip tt : progressTooltips) {
            tt.setVisible(false);
        }
    }
}
