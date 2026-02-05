package com.slprime.chromatictooltipscompat.util;

import net.minecraft.client.renderer.RenderHelper;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.slprime.chromatictooltips.api.ITooltipComponent;
import com.slprime.chromatictooltips.api.TooltipContext;
import com.slprime.chromatictooltipscompat.mixins.late.waila.TooltipInvoker;

import mcp.mobius.waila.overlay.DisplayUtil;
import mcp.mobius.waila.overlay.Tooltip;

public class WAILAComponent implements ITooltipComponent {

    protected final TooltipInvoker tooltip;

    public WAILAComponent(Tooltip tooltip) {
        this.tooltip = (TooltipInvoker) tooltip;
    }

    @Override
    public int getWidth() {
        return tooltip.getW();
    }

    @Override
    public int getHeight() {
        return tooltip.getH();
    }

    @Override
    public int getSpacing() {
        return 0;
    }

    @Override
    public void draw(int x, int y, int availableWidth, TooltipContext context) {
        int originalX = tooltip.getX();
        int originalY = tooltip.getY();
        tooltip.setX(x);
        tooltip.setY(y);

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        tooltip.invokeDraw();
        GL11.glDisable(GL11.GL_BLEND);

        tooltip.invokeDraw2nd();

        if (tooltip.hasIcon() && tooltip.getStack() != null
            && tooltip.getStack()
                .getItem() != null) {
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.enableGUIStandardItemLighting();
            DisplayUtil.renderStack(tooltip.getX(), tooltip.getY() + tooltip.getH() / 2 - 8, tooltip.getStack());
            RenderHelper.disableStandardItemLighting();
        }

        GL11.glPopMatrix();

        tooltip.setX(originalX);
        tooltip.setY(originalY);
    }
}
