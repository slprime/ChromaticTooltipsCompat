package com.slprime.chromatictooltipscompat.mixins.late.enderio;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.enderio.core.client.handlers.ClientHandler;
import com.slprime.chromatictooltips.util.TooltipUtils;

@Mixin(ClientHandler.class)
public class ClientHandlerMixin {

    /**
     * @author SLPrime
     * @reason Use our own method to check for shift key state to ensure compatibility with other
     *         mods that may remap keys.
     */
    @Overwrite(remap = false)
    public static boolean isShiftDown() {
        return TooltipUtils.isShiftKeyDown();
    }

}
