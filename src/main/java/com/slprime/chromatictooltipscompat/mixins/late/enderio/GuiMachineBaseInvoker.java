package com.slprime.chromatictooltipscompat.mixins.late.enderio;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import com.enderio.core.client.gui.widget.GuiToolTip;

import crazypants.enderio.machine.gui.GuiMachineBase;

@Mixin(value = GuiMachineBase.class, remap = false)
public interface GuiMachineBaseInvoker {

    @Accessor("progressTooltips")
    List<GuiToolTip> getProgressTooltips();

}
