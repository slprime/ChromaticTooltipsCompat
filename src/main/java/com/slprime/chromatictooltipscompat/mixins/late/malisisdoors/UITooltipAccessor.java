package com.slprime.chromatictooltipscompat.mixins.late.malisisdoors;

import java.util.List;

import net.malisis.core.client.gui.component.decoration.UITooltip;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = UITooltip.class, remap = false)
public interface UITooltipAccessor {

    @Accessor("lines")
    List<String> chromatictooltipscompat$getLines();
}
