package com.slprime.chromatictooltipscompat.mixins.late.waila;

import java.awt.Dimension;
import java.util.Arrays;

import net.minecraft.client.gui.ScaledResolution;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.slprime.chromatictooltips.TooltipHandler;
import com.slprime.chromatictooltips.api.TooltipContext;
import com.slprime.chromatictooltips.api.TooltipRequest;
import com.slprime.chromatictooltips.api.TooltipTarget;
import com.slprime.chromatictooltips.util.TooltipUtils;
import com.slprime.chromatictooltipscompat.util.WAILAComponent;

import mcp.mobius.waila.overlay.OverlayConfig;
import mcp.mobius.waila.overlay.OverlayRenderer;
import mcp.mobius.waila.overlay.RayTracing;
import mcp.mobius.waila.overlay.Tooltip;

@Mixin(OverlayRenderer.class)
public class OverlayRendererMixin {

    private static TooltipRequest request = new TooltipRequest("waila", TooltipTarget.EMPTY, null, null);
    private static TooltipContext previousContext;
    private static Tooltip previousTooltip;

    /**
     * @author SLPrime
     * @reason Redirect WAILA tooltip rendering through Chromatic Tooltips
     */
    @Overwrite(remap = false)
    private static void doRenderOverlay(Tooltip tooltip) {

        if (tooltip != previousTooltip) {
            previousTooltip = tooltip;

            if (previousContext == null) {
                previousContext = new TooltipContext(request, TooltipHandler.getRendererFor(request));
            } else {
                previousContext = new TooltipContext(request, previousContext);
            }

            previousContext.addSection("body", Arrays.asList(new WAILAComponent(tooltip)));
            previousContext.setScaleFactor(
                TooltipUtils.getScaledResolution()
                    .getScaleFactor());
        }

        if (previousContext != null) {
            final TooltipInvoker tooltipInvoker = (TooltipInvoker) tooltip;
            final ScaledResolution screenSize = TooltipUtils.getScaledResolution();
            final Dimension tooltipSize = previousContext.getTooltipSize();
            final int x = ((int) (screenSize.getScaledWidth() / OverlayConfig.scale) - tooltipSize.width - 1)
                * tooltipInvoker.getPos().x
                / 10000;
            final int y = ((int) (screenSize.getScaledHeight() / OverlayConfig.scale) - tooltipSize.height - 1)
                * tooltipInvoker.getPos().y
                / 10000;

            GL11.glScalef(OverlayConfig.scale, OverlayConfig.scale, 1.0f);
            previousContext.drawAt(x, y);
            GL11.glScalef(1.0f / OverlayConfig.scale, 1.0f / OverlayConfig.scale, 1.0f);
        }

    }

    /**
     * @author SLPrime
     * @reason Clear the previous tooltip context when the tooltip is closed or the player looks away from the target
     */
    @Inject(method = "renderOverlay", at = @At("HEAD"), remap = false)
    private static void renderOverlay(Tooltip tooltip, CallbackInfo ci) {

        if (previousContext != null && (tooltip == null || RayTracing.instance()
            .getTarget() == null)) {
            previousContext = null;
            previousTooltip = null;
        }

    }

}
