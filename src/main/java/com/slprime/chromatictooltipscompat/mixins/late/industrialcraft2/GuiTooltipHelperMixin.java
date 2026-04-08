package com.slprime.chromatictooltipscompat.mixins.late.industrialcraft2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.slprime.chromatictooltips.TooltipHandler;

import ic2.core.upgrade.IUpgradableBlock;
import ic2.core.util.GuiTooltipHelper;

@Mixin(GuiTooltipHelper.class)
public class GuiTooltipHelperMixin {

    @Shadow(remap = false)
    private static List<ItemStack> getCompatibleUpgrades(IUpgradableBlock block) {
        throw new AssertionError("Mixin @Shadow");
    }

    /**
     * @author SLPrime
     * @reason Redirect IC2 tooltip rendering to ChromaticTooltips
     */
    @Overwrite(remap = false)
    public static void drawTooltip(int mouseX, int mouseY, int xOffset, int yOffset, String text, boolean drawBorder,
        int width) {

        if (text != null && !text.isEmpty()) {
            TooltipHandler.drawHoveringText(Collections.singletonList(text));
        }

    }

    /**
     * @author SLPrime
     * @reason Redirect IC2 tooltip rendering to ChromaticTooltips
     */
    @Overwrite(remap = false)
    public static void drawUpgradeslotTooltip(int x, int y, int minX, int minY, int maxX, int maxY, IUpgradableBlock te,
        int yoffset, int xoffset) {

        if (te instanceof IUpgradableBlock && x >= minX && x <= maxX && y >= minY && y <= maxY) {
            final List<String> tooltip = new ArrayList<>();
            final List<ItemStack> compatibleUpgrades = getCompatibleUpgrades(te);

            tooltip.add(StatCollector.translateToLocal("ic2.generic.text.upgrade"));

            for (ItemStack itemstack : compatibleUpgrades) {
                tooltip.add("  " + itemstack.getDisplayName());
            }

            TooltipHandler.drawHoveringText(tooltip);
        }

    }

}
