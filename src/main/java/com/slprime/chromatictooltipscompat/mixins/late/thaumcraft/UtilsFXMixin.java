package com.slprime.chromatictooltipscompat.mixins.late.thaumcraft;

import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;

import thaumcraft.client.lib.UtilsFX;

@Mixin(value = UtilsFX.class, remap = false)
public class UtilsFXMixin {

    /**
     * @author SLPrime
     * @reason Replace Thaumcraft tooltip rendering with Chromatic Tooltips.
     */
    @Overwrite(remap = false)
    public static void drawCustomTooltip(GuiScreen gui, RenderItem itemRenderer, FontRenderer fr, List<String> var4,
        int par2, int par3, int subTipColor) {
        TooltipHandler.drawHoveringText(var4);
    }

}
