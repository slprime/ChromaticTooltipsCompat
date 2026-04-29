package com.slprime.chromatictooltipscompat.mixins.late.notenoughitems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.slprime.chromatictooltips.TooltipHandler;
import com.slprime.chromatictooltipscompat.ChromaticTooltipsCompat.ModIds;
import com.slprime.chromatictooltipscompat.CompatConfig;
import com.slprime.chromatictooltipscompat.util.EnderIOTooltipBridge;

import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.guihook.IContainerTooltipHandler;
import cpw.mods.fml.common.Loader;

@Mixin(GuiContainerManager.class)
public class GuiContainerManagerMixin {

    @Shadow(remap = false)
    public GuiContainer window;

    @Shadow(remap = false)
    @Final
    public List<IContainerTooltipHandler> instanceTooltipHandlers;

    /**
     * @author SLPrime
     * @reason Replace NEI tooltip rendering with Chromatic Tooltips
     * @see https://github.com/asdflj/AE2Things/blob/c17061e473f2553f0b67793d65b4d4c943fff295/src/main/java/com/asdflj/ae2thing/coremod/mixin/nei/MixinGuiContainerManager.java
     */
    @Inject(method = "renderToolTips", at = @At("HEAD"), remap = false, cancellable = true)
    private void tooltip$renderToolTips(int mousex, int mousey, CallbackInfo ci) {
        List<String> tooltip = new ArrayList<>();
        final ItemStack stack = GuiContainerManager.getStackMouseOver(this.window);
        final boolean showTooltip = GuiContainerManager.shouldShowTooltip(this.window);

        if (stack != null && this.window instanceof IGuiContainerCreativeAccessor containerCreative) {
            tooltip.addAll(this.buildCreativeTabLines(containerCreative, stack));
        }

        synchronized (instanceTooltipHandlers) {
            for (IContainerTooltipHandler handler : instanceTooltipHandlers) {
                tooltip = handler.handleTooltip(this.window, mousex, mousey, tooltip);
            }
        }

        if (CompatConfig.enderCoreEnabled && Loader.isModLoaded(ModIds.ENDERCORE)) {
            // Add Ender IO tooltips
            EnderIOTooltipBridge.handle(tooltip);
        }

        TooltipHandler.drawHoveringText(showTooltip ? stack : null, tooltip);
        ci.cancel();
    }

    /**
     * @author SLPrime
     * @reason Replace NEI tooltip rendering with Chromatic Tooltips
     */
    @Overwrite(remap = false)
    public static void drawPagedTooltip(FontRenderer font, int x, int y, List<String> list) {
        if (!list.isEmpty()) {
            TooltipHandler.drawHoveringText(list);
        }
    }

    private List<String> buildCreativeTabLines(IGuiContainerCreativeAccessor containerCreative, ItemStack stack) {

        if (containerCreative.getSelectedTabIndex() != CreativeTabs.tabAllSearch.getTabIndex()) {
            return Collections.emptyList();
        }

        CreativeTabs creativetabs = stack.getItem()
            .getCreativeTab();

        if (creativetabs == null && stack.getItem() == Items.enchanted_book) {
            final Map<Integer, Integer> map = EnchantmentHelper.getEnchantments(stack);
            if (map.size() == 1) {
                final Enchantment enchantment = Enchantment.enchantmentsList[map.keySet()
                    .iterator()
                    .next()];
                for (CreativeTabs tab : CreativeTabs.creativeTabArray) {
                    if (tab.func_111226_a(enchantment.type)) {
                        creativetabs = tab;
                        break;
                    }
                }
            }
        }

        if (creativetabs == null) {
            return Collections.emptyList();
        }

        final List<String> lines = new ArrayList<>();
        lines.add(
            "" + EnumChatFormatting.BOLD + EnumChatFormatting.BLUE + I18n.format(creativetabs.getTranslatedTabLabel()));
        return lines;
    }

}
