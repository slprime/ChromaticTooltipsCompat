package com.slprime.chromatictooltipscompat.mixins.late.notenoughitems;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.gtnewhorizon.gtnhlib.config.ConfigurationManager;
import com.slprime.chromatictooltips.config.EnricherConfig;
import com.slprime.chromatictooltips.config.EnricherConfig.EnricherVisibility;

import codechicken.nei.config.OptionButton;
import codechicken.nei.config.OptionCycled;

@Mixin(OptionCycled.class)
public abstract class OptionCycledMixin extends OptionButton {

    private static final String ITEM_IDS_OPTION = "inventory.itemIDs";

    // Indexed by NEI option value: Hidden, Auto, Shown
    private static final EnricherVisibility[] ITEM_IDS_VISIBILITY = { EnricherVisibility.OFF,
        EnricherVisibility.EXTENDED, EnricherVisibility.ALWAYS };

    private OptionCycledMixin(String name) {
        super(name);
    }

    /**
     * Bind NEI's "Item IDs" option to Chromatic Tooltips' item id setting so they can't disagree.
     */
    @Inject(method = "value", at = @At("HEAD"), cancellable = true, remap = false)
    private void chromatictooltips$readItemIds(CallbackInfoReturnable<Integer> cir) {
        if (ITEM_IDS_OPTION.equals(this.name)) {
            cir.setReturnValue(chromatictooltips$getItemIdsIndex());
        }
    }

    @Inject(method = "cycle", at = @At("HEAD"), cancellable = true, remap = false)
    private void chromatictooltips$cycleItemIds(CallbackInfoReturnable<Boolean> cir) {
        if (ITEM_IDS_OPTION.equals(this.name)) {
            final int next = (chromatictooltips$getItemIdsIndex() + 1) % ITEM_IDS_VISIBILITY.length;
            EnricherConfig.itemId = ITEM_IDS_VISIBILITY[next];
            ConfigurationManager.save(EnricherConfig.class);
            cir.setReturnValue(true);
        }
    }

    private static int chromatictooltips$getItemIdsIndex() {
        for (int i = 0; i < ITEM_IDS_VISIBILITY.length; i++) {
            if (ITEM_IDS_VISIBILITY[i] == EnricherConfig.itemId) return i;
        }

        return 0;
    }

}
