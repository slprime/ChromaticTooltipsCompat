package com.slprime.chromatictooltipscompat.mixins.late.neicustomdiagram;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.github.dcysteine.neicustomdiagram.api.diagram.tooltip.Tooltip;
import com.github.dcysteine.neicustomdiagram.api.diagram.tooltip.TooltipElement;
import com.github.dcysteine.neicustomdiagram.api.diagram.tooltip.TooltipLine;
import com.github.dcysteine.neicustomdiagram.api.draw.Draw;
import com.github.dcysteine.neicustomdiagram.api.draw.Point;
import com.slprime.chromatictooltips.TooltipHandler;
import com.slprime.chromatictooltips.api.ITooltipComponent;
import com.slprime.chromatictooltips.api.TooltipBuilder;
import com.slprime.chromatictooltips.api.TooltipContext;

@Mixin(Draw.class)
public class DrawMixin {

    private static class TooltipComponentCompat implements ITooltipComponent {

        protected final TooltipLine lineHandler;

        public TooltipComponentCompat(TooltipLine lineHandler) {
            this.lineHandler = lineHandler;
        }

        public int getWidth() {
            return this.lineHandler.width();
        }

        public int getHeight() {
            return Tooltip.LINE_SPACING + this.lineHandler.height();
        }

        public int getSpacing() {
            return 0;
        }

        public void draw(int x, int y, int availableWidth, TooltipContext context) {
            GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_COLOR_BUFFER_BIT | GL11.GL_LIGHTING_BIT);
            this.lineHandler.draw(x, y);
            GL11.glPopAttrib();
        }

        @Override
        public int hashCode() {
            return this.lineHandler.hashCode();
        }

        @Override
        public boolean equals(Object obj) {

            if (obj instanceof TooltipComponentCompat other) {
                return this.lineHandler.equals(other.lineHandler);
            }

            return false;
        }

    }

    /**
     * @author SLPrime
     * @reason Disable NEI Custom Diagram's tooltip rendering
     */
    @Overwrite(remap = false)
    public static void drawTooltip(Tooltip tooltip, Point mousePos) {
        if (tooltip.lines()
            .isEmpty()) {
            return;
        }

        TooltipBuilder builder = TooltipHandler.builder();

        for (TooltipLine line : tooltip.lines()) {
            builder.line(restoreComponentIfNeeded(line));
        }

        TooltipHandler.drawHoveringText(builder.build());
    }

    private static ITooltipComponent restoreComponentIfNeeded(TooltipLine line) {

        for (TooltipElement element : line.elements()) {
            if (element.type() == TooltipElement.ElementType.TEXT) {
                final ITooltipComponent component = TooltipHandler.getTooltipComponent(element.text());
                if (component != null) {
                    return component;
                }
            }
        }

        return new TooltipComponentCompat(line);
    }

}
