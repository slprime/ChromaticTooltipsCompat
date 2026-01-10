package com.slprime.chromatictooltipscompat.mixins.late.notenoughitems;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import codechicken.nei.LayoutManager;

@Mixin(LayoutManager.class)
public class LayoutManagerMixin {

    /**
     * Disable NEI's "Show IDs" feature to prevent it from interfering with Chromatic Tooltips' own item ID display.
     */
    @Redirect(
        method = "handleItemDisplayName",
        at = @At(value = "INVOKE", target = "Lcodechicken/nei/NEIClientConfig;showIDs()Z"),
        remap = false)
    private boolean chromatictooltips$disableShowIDs() {
        return false;
    }

}
