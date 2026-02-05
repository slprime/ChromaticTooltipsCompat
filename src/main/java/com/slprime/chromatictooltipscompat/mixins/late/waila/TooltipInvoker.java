package com.slprime.chromatictooltipscompat.mixins.late.waila;

import java.awt.Point;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import mcp.mobius.waila.overlay.Tooltip;

@Mixin(value = Tooltip.class, remap = false)
public interface TooltipInvoker {

    @Accessor("w")
    int getW();

    @Accessor("h")
    int getH();

    @Accessor("x")
    int getX();

    @Accessor("y")
    int getY();

    @Accessor("ty")
    int getTy();

    @Accessor("pos")
    Point getPos();

    @Accessor("hasIcon")
    boolean hasIcon();

    @Accessor("stack")
    ItemStack getStack();

    @Accessor("x")
    void setX(int x);

    @Accessor("y")
    void setY(int y);

    @Invoker("draw")
    void invokeDraw();

    @Invoker("draw2nd")
    void invokeDraw2nd();
}
