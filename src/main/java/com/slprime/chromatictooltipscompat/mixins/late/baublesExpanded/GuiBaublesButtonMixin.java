package com.slprime.chromatictooltipscompat.mixins.late.baublesExpanded;

import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;

import baubles.client.gui.GuiBaublesButton;
import baubles.client.gui.GuiPlayerExpanded;
import baubles.common.BaublesConfig;

@Mixin(GuiBaublesButton.class)
public abstract class GuiBaublesButtonMixin extends GuiButton {

    public GuiBaublesButtonMixin(int id, int x, int y, int w, int h, String text) {
        super(id, x, y, w, h, text);
    }

    @Overwrite
    public void drawButton(Minecraft mc, int xx, int yy) {

        if (!this.visible) {
            return;
        }

        if (BaublesConfig.useOldGuiButton) {
            mc.getTextureManager()
                .bindTexture(GuiPlayerExpanded.background);
        } else {
            mc.getTextureManager()
                .bindTexture(GuiPlayerExpanded.gui_background);
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_146123_n = xx >= this.xPosition && yy >= this.yPosition
            && xx < this.xPosition + this.width
            && yy < this.yPosition + this.height;
        int hover = this.getHoverState(this.field_146123_n);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        if (hover == 1) {
            if (BaublesConfig.useOldGuiButton) {
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 200, 48, 10, 10);
            } else {
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 50, 0, 14, 14);
            }
            return;
        }

        if (BaublesConfig.useOldGuiButton) {
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 210, 48, 10, 10);
        } else {
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 50, 14, 14, 14);
        }

        TooltipHandler.drawHoveringText(Arrays.asList(this.displayString));

        this.mouseDragged(mc, xx, yy);
    }

}
