package com.slprime.chromatictooltipscompat.mixins.late.galacticraft;

import java.util.List;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.slprime.chromatictooltips.TooltipHandler;

import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementInfoRegion;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;

@Mixin(GuiElementInfoRegion.class)
public abstract class GuiElementInfoRegionMixin extends Gui {

    @Shadow(remap = false)
    protected int width;

    @Shadow(remap = false)
    protected int height;

    @Shadow(remap = false)
    public int xPosition;

    @Shadow(remap = false)
    public int yPosition;

    @Shadow(remap = false)
    public boolean enabled;

    @Shadow(remap = false)
    public boolean drawRegion;

    @Shadow(remap = false)
    public boolean withinRegion;

    @Shadow(remap = false)
    public List<String> tooltipStrings;

    @Shadow(remap = false)
    protected abstract int getHoverState(boolean hovered);

    /**
     * @author SLPrime
     * @reason Replace Galacticraft tooltip rendering with Chromatic Tooltips
     */
    @Overwrite(remap = false)
    public void drawRegion(int mouseX, int mouseY) {
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        this.withinRegion = mouseX >= this.xPosition && mouseY >= this.yPosition
            && mouseX < this.xPosition + this.width
            && mouseY < this.yPosition + this.height;

        if (this.drawRegion) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            final int hoverState = this.getHoverState(this.withinRegion);
            Gui.drawRect(
                this.xPosition,
                this.yPosition,
                this.xPosition + this.width,
                this.yPosition + this.height,
                ColorUtil.to32BitColor(100 * hoverState, 255, 0, 0));
        }

        if (this.withinRegion && this.tooltipStrings != null && !this.tooltipStrings.isEmpty()) {
            TooltipHandler.drawHoveringText(this.tooltipStrings);
        }

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        RenderHelper.enableStandardItemLighting();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    }
}
