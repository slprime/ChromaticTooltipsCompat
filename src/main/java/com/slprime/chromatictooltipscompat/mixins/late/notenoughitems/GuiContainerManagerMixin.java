package com.slprime.chromatictooltipscompat.mixins.late.notenoughitems;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.slprime.chromatictooltips.TooltipHandler;
import com.slprime.chromatictooltipscompat.ClientUtil;

import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.guihook.IContainerTooltipHandler;

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

        synchronized (instanceTooltipHandlers) {
            for (IContainerTooltipHandler handler : instanceTooltipHandlers) {
                tooltip = handler.handleTooltip(this.window, mousex, mousey, tooltip);
            }
        }

        TooltipHandler.drawHoveringText(showTooltip ? ClientUtil.prepareItemStack(stack) : null, tooltip);
        ci.cancel();
    }

    /**
     * @author SLPrime
     * @reason Replace NEI tooltip rendering with Chromatic Tooltips
     */
    @Overwrite(remap = false)
    public static void drawPagedTooltip(FontRenderer font, int x, int y, List<String> list) {
        if (list.isEmpty()) return;
        TooltipHandler.drawHoveringText(list);
    }

}
