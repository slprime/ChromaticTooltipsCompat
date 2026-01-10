package com.slprime.chromatictooltipscompat.mixins.late.enderio;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.enderio.core.client.handlers.OreDictTooltipHandler;

@Mixin(OreDictTooltipHandler.class)
public class OreDictTooltipHandlerMixin {

    /**
     * @author SLPrime
     * @reason Prevent Ender IO from modifying item tooltips
     */
    @Overwrite(remap = false)
    public void onItemTooltip(ItemTooltipEvent event) {
        // NO-OP to prevent Ender IO from modifying item tooltips
    }

}
