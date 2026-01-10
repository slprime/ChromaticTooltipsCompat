package com.slprime.chromatictooltipscompat.mixins.late.enderio;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.enderio.core.client.handlers.EnchantTooltipHandler;

@Mixin(EnchantTooltipHandler.class)
public class EnchantTooltipHandlerMixin {

    /**
     * @author SLPrime
     * @reason Disable Ender IO's enchantment tooltip handler to prevent conflicts
     */
    @Overwrite(remap = false)
    public void handleTooltip(ItemTooltipEvent event) {
        // NO-OP
    }

}
