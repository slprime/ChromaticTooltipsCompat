package com.slprime.chromatictooltipscompat.mixins.late.enderio;

import java.util.List;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.enderio.core.client.handlers.SpecialTooltipHandler;
import com.slprime.chromatictooltips.api.TooltipLines;

@Mixin(SpecialTooltipHandler.class)
public class SpecialTooltipHandlerMixin {

    /**
     * @author SLPrime
     * @reason Prevent Ender IO from adding durability tooltips
     */
    @Overwrite(remap = false)
    public void addDurabilityTooltip(List<String> toolTip, ItemStack itemStack) {
        // NO-OP to prevent Ender IO from adding durability tooltips
    }

    /**
     * @author SLPrime
     * @reason Modify Ender IO's "hold shift for details" tooltip to use Chromatic Tooltips' line
     */
    @Overwrite(remap = false)
    public static void addShowDetailsTooltip(List<String> list) {
        list.add(TooltipLines.SHIFT_MODIFIER);
    }
}
