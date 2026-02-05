package com.slprime.chromatictooltipscompat.mixins.late.notenoughitems;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipRegistry;
import com.slprime.chromatictooltips.api.EnchantmentData;
import com.slprime.chromatictooltips.api.ItemStats;
import com.slprime.chromatictooltips.api.TooltipTarget;
import com.slprime.chromatictooltips.enricher.EnchantmentEnricher;
import com.slprime.chromatictooltips.enricher.FluidInfoEnricher;
import com.slprime.chromatictooltips.enricher.ItemInfoEnricher;
import com.slprime.chromatictooltips.enricher.ItemStatsEnricher;

import codechicken.nei.search.TooltipFilter;

@Mixin(TooltipFilter.class)
public class TooltipFilterMixin {

    /**
     * @author SLPrime
     * @reason Use Chromatic Tooltips to generate the tooltip text for NEI filtering.
     */
    @Overwrite(remap = false)
    private static String getTooltip(ItemStack itemstack) {
        final TooltipTarget target = TooltipRegistry.sanitizeTarget(TooltipTarget.ofItem(itemstack));
        final StringBuilder tooltip = new StringBuilder(128);

        if (target.isItem()) {
            for (Object info : ItemInfoEnricher.getItemInformation(target)) {
                if (info instanceof String str) {
                    tooltip.append(str)
                        .append("\n");
                }
            }
        } else if (target.isFluid()) {
            for (Object info : FluidInfoEnricher.getFluidInformation(target)) {
                if (info instanceof String str) {
                    tooltip.append(str)
                        .append("\n");
                }
            }
        }

        for (EnchantmentData enchantmentData : EnchantmentEnricher.getEnchantments(target)) {
            tooltip.append(enchantmentData.enchantment.getTranslatedName(enchantmentData.level))
                .append("\n");
            tooltip.append(enchantmentData.hint)
                .append("\n");
        }

        for (ItemStats itemStats : ItemStatsEnricher.getAttributeModifiers(target)) {
            tooltip.append(itemStats.getTextLine())
                .append("\n");
        }

        return tooltip.toString();
    }

}
