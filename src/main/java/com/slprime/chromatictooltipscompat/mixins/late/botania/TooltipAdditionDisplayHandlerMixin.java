package com.slprime.chromatictooltipscompat.mixins.late.botania;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.slprime.chromatictooltipscompat.event.BotaniaManaBarPositionTracker;

import vazkii.botania.client.core.handler.TooltipAdditionDisplayHandler;

@Mixin(value = TooltipAdditionDisplayHandler.class, remap = false)
public class TooltipAdditionDisplayHandlerMixin {

    @ModifyVariable(method = "drawManaBar", at = @At("HEAD"), argsOnly = true, index = 2)
    private static int drawManaBar_mouseX(int mouseX) {
        return BotaniaManaBarPositionTracker.hasPosition ? BotaniaManaBarPositionTracker.lastTooltipX : mouseX;
    }

    @ModifyVariable(method = "drawManaBar", at = @At("HEAD"), argsOnly = true, index = 3)
    private static int drawManaBar_mouseY(int mouseY) {
        return BotaniaManaBarPositionTracker.hasPosition ? BotaniaManaBarPositionTracker.lastTooltipY : mouseY;
    }

    @ModifyVariable(method = "drawManaBar", at = @At("HEAD"), argsOnly = true, index = 4)
    private static int drawManaBar_offx(int offx) {
        return BotaniaManaBarPositionTracker.hasPosition ? 0 : offx;
    }

    @ModifyVariable(method = "drawManaBar", at = @At("HEAD"), argsOnly = true, index = 5)
    private static int drawManaBar_offy(int offy) {
        return BotaniaManaBarPositionTracker.hasPosition ? 0 : offy;
    }

    @ModifyVariable(method = "drawManaBar", at = @At("HEAD"), argsOnly = true, index = 6)
    private static int drawManaBar_width(int width) {
        return BotaniaManaBarPositionTracker.hasPosition ? BotaniaManaBarPositionTracker.lastTooltipWidth : width;
    }

    @ModifyVariable(method = "drawTieredManaBar", at = @At("HEAD"), argsOnly = true, index = 2)
    private static int drawTieredManaBar_mouseX(int mouseX) {
        return BotaniaManaBarPositionTracker.hasPosition ? BotaniaManaBarPositionTracker.lastTooltipX : mouseX;
    }

    @ModifyVariable(method = "drawTieredManaBar", at = @At("HEAD"), argsOnly = true, index = 3)
    private static int drawTieredManaBar_mouseY(int mouseY) {
        return BotaniaManaBarPositionTracker.hasPosition ? BotaniaManaBarPositionTracker.lastTooltipY : mouseY;
    }

    @ModifyVariable(method = "drawTieredManaBar", at = @At("HEAD"), argsOnly = true, index = 4)
    private static int drawTieredManaBar_offx(int offx) {
        return BotaniaManaBarPositionTracker.hasPosition ? 0 : offx;
    }

    @ModifyVariable(method = "drawTieredManaBar", at = @At("HEAD"), argsOnly = true, index = 5)
    private static int drawTieredManaBar_offy(int offy) {
        return BotaniaManaBarPositionTracker.hasPosition ? 0 : offy;
    }

    @ModifyVariable(method = "drawTieredManaBar", at = @At("HEAD"), argsOnly = true, index = 6)
    private static int drawTieredManaBar_width(int width) {
        return BotaniaManaBarPositionTracker.hasPosition ? BotaniaManaBarPositionTracker.lastTooltipWidth : width;
    }
}
