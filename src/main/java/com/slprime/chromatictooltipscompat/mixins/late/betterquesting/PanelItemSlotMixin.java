package com.slprime.chromatictooltipscompat.mixins.late.betterquesting;

import java.util.Arrays;
import java.util.List;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.slprime.chromatictooltipscompat.ClientProxy;

import betterquesting.api.utils.BigItemStack;
import betterquesting.api2.client.gui.controls.PanelButton;
import betterquesting.api2.client.gui.misc.IGuiRect;
import betterquesting.api2.client.gui.panels.content.PanelItemSlot;

@Mixin(PanelItemSlot.class)
public abstract class PanelItemSlotMixin {

    @Shadow(remap = false)
    @Final
    private boolean oreDict;

    @Shadow(remap = false)
    @Final
    private List<BigItemStack> oreVariants;

    @Shadow(remap = false)
    public abstract BigItemStack getStoredValue();

    /**
     * @author SLPrime
     * @reason Suppress Better Questing item tooltips
     */
    @Overwrite(remap = false)
    public List<String> getTooltip(int mx, int my) {
        final IGuiRect rect = ((PanelButton) (Object) this).getTransform();
        BigItemStack ttStack = getStoredValue();
        if (ttStack == null || !rect.contains(mx, my)) return null;

        if (oreDict && oreVariants.size() > 0) {
            ttStack = oreVariants.get((int) (System.currentTimeMillis() / 1000D) % oreVariants.size());
        }

        ClientProxy.chromatic$currentStack = ttStack != null ? ttStack.getBaseStack() : null;

        return Arrays.asList("");
    }

}
