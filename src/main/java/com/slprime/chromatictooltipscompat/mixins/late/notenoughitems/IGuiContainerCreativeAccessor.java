package com.slprime.chromatictooltipscompat.mixins.late.notenoughitems;

import net.minecraft.client.gui.inventory.GuiContainerCreative;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiContainerCreative.class)
public interface IGuiContainerCreativeAccessor {

    @Accessor
    int getSelectedTabIndex();
}
