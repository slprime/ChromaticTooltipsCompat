package com.slprime.chromatictooltipscompat.mixins.late.wawla;

import net.darkhax.wawla.addons.generic.AddonGenericTooltips;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.slprime.chromatictooltips.api.TooltipLines;

@Mixin(value = AddonGenericTooltips.class, remap = false)
public class AddonGenericTooltipsMixin {

    /**
     * @author SLPrime
     * @reason Remove armor protection tooltip added by WAWLA.
     */
    @Inject(method = "onTooltip", at = @At("TAIL"))
    private void removeArmorProtection(ItemTooltipEvent event, CallbackInfo ci) {
        final String shiftEnch = StatCollector.translateToLocal("tooltip.wawla.shiftEnch");
        final String armorprot = StatCollector.translateToLocal("tooltip.wawla.armorprot");
        event.toolTip.removeIf(s -> s.startsWith(armorprot) || s.equals(shiftEnch));
        event.toolTip.removeIf(s -> s.startsWith(armorprot) || s.equals(shiftEnch));

        if (event.itemStack.getItem() instanceof ItemEnchantedBook) {
            event.toolTip.add(TooltipLines.SHIFT_MODIFIER);
        }
    }

}
