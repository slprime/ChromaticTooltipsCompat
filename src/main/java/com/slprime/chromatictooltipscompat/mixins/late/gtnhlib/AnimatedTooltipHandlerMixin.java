package com.slprime.chromatictooltipscompat.mixins.late.gtnhlib;

import java.util.function.Supplier;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.gtnewhorizon.gtnhlib.util.AnimatedTooltipHandler;
import com.slprime.chromatictooltips.TooltipHandler;
import com.slprime.chromatictooltips.component.DyncamicTextComponent;

@Mixin(AnimatedTooltipHandler.class)
public class AnimatedTooltipHandlerMixin {

    /**
     * @author SLPrime
     * @reason Redirect tooltip text retrieval to Chromatic Tooltips so it can be wrapped in a dynamic text component.
     * @see https://github.com/ABKQPO/GT-Not-Leisure/blob/7a7e339eaf29b1793cf3970fb8dbd7fcf6f2bc2b/src/main/java/com/science/gtnl/mixins/early/Stick/MixinAnimatedTooltipHandler.java
     */
    @Redirect(
        method = "renderTooltip",
        at = @At(value = "INVOKE", target = "Ljava/util/function/Supplier;get()Ljava/lang/Object;"),
        remap = false)
    private static Object renderTooltip(Supplier<String> tooltip, ItemTooltipEvent event) {

        if (tooltip.get() != null) {
            event.toolTip.add(TooltipHandler.getComponentId(new DyncamicTextComponent(tooltip)));
        }

        return null;
    }

}
