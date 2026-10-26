package com.slprime.chromatictooltipscompat.mixins.late.modularui2;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fluids.FluidStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.cleanroommc.modularui.api.MCHelper;
import com.cleanroommc.modularui.api.drawable.ITextLine;
import com.cleanroommc.modularui.drawable.text.Spacer;
import com.cleanroommc.modularui.drawable.text.TextLine;
import com.cleanroommc.modularui.drawable.text.TextRenderer;
import com.cleanroommc.modularui.screen.RichTooltip;
import com.cleanroommc.modularui.screen.viewport.GuiContext;
import com.slprime.chromatictooltips.TooltipHandler;
import com.slprime.chromatictooltips.api.ITooltipComponent;
import com.slprime.chromatictooltips.api.TooltipContext;
import com.slprime.chromatictooltips.api.TooltipLines;
import com.slprime.chromatictooltips.api.TooltipRequest;
import com.slprime.chromatictooltips.api.TooltipTarget;
import com.slprime.chromatictooltips.component.SpaceComponent;
import com.slprime.chromatictooltips.component.TextComponent;
import com.slprime.chromatictooltips.util.TooltipUtils;

@Mixin(RichTooltip.class)
public class RichTooltipMixin {

    public static class TooltipComponentCompat implements ITooltipComponent {

        protected final ITextLine line;
        protected GuiContext context;
        protected boolean shadow = false;
        protected int color = 0;

        public TooltipComponentCompat(ITextLine line, GuiContext context, int color, boolean shadow) {
            this.line = line;
            this.context = context;
            this.color = color;
            this.shadow = shadow;
        }

        @Override
        public int getWidth() {
            return this.line.getWidth();
        }

        @Override
        public int getHeight() {
            return this.line.getHeight(Minecraft.getMinecraft().fontRenderer) - 2;
        }

        @Override
        public int getSpacing() {
            return 2;
        }

        @Override
        public void draw(int x, int y, int availableWidth, TooltipContext context) {
            this.line.draw(
                this.context,
                Minecraft.getMinecraft().fontRenderer,
                x,
                y,
                this.color,
                this.shadow,
                availableWidth,
                getHeight());
        }

        @Override
        public int hashCode() {
            return this.line.hashCode();
        }

        @Override
        public boolean equals(Object obj) {

            if (obj instanceof TooltipComponentCompat other) {
                return this.line.equals(other.line);
            }

            return false;
        }

        @Override
        public String toString() {
            return "TooltipComponentCompat{line=" + this.line + "}";
        }
    }

    private FluidStack chromatictooltips$fluid;

    @Inject(method = "buildTooltip", at = @At("HEAD"), remap = false)
    private void chromatictooltips$onBuildTooltip(CallbackInfo ci) {
        this.chromatictooltips$fluid = null;
    }

    @Inject(method = "addFromFluid", at = @At("HEAD"), cancellable = true, remap = false)
    private void chromatictooltips$onAddFromFluid(FluidStack fluid, CallbackInfoReturnable<RichTooltip> cir) {
        final RichTooltip tooltip = (RichTooltip) (Object) this;
        final List<String> lines = MCHelper.getFluidTooltip(fluid);

        // the title and the fluid info are built by Chromatic Tooltips from the fluid target
        for (int i = 1; i < lines.size(); i++) {
            tooltip.add(lines.get(i))
                .newLine();
        }

        this.chromatictooltips$fluid = fluid;
        cir.setReturnValue(tooltip);
    }

    @Inject(method = "addAdditionalInfoFromFluid", at = @At("HEAD"), cancellable = true, remap = false)
    private void chromatictooltips$onAddAdditionalInfoFromFluid(FluidStack fluid,
        CallbackInfoReturnable<RichTooltip> cir) {

        if (this.chromatictooltips$fluid != null) {
            cir.setReturnValue((RichTooltip) (Object) this);
        }

    }

    @Redirect(
        method = "draw(Lcom/cleanroommc/modularui/screen/viewport/GuiContext;Lnet/minecraft/item/ItemStack;)V",
        at = @At(
            value = "INVOKE",
            target = "Lcom/cleanroommc/modularui/drawable/GuiDraw;" + "drawTooltipBackground("
                + "Lnet/minecraft/item/ItemStack;"
                + "Ljava/util/List;"
                + "IIII"
                + "Lcom/cleanroommc/modularui/screen/RichTooltip;"
                + ")V"),
        remap = false)
    private void chromatictooltips$drawTooltipBackground(ItemStack stack, List<String> lines, int x, int y, int width,
        int height, RichTooltip tooltip) {
        // do nothing
    }

    @Redirect(
        method = "draw(Lcom/cleanroommc/modularui/screen/viewport/GuiContext;Lnet/minecraft/item/ItemStack;)V",
        at = @At(
            value = "INVOKE",
            target = "Lcom/cleanroommc/modularui/drawable/text/TextRenderer;" + "drawCompiled("
                + "Lcom/cleanroommc/modularui/screen/viewport/GuiContext;"
                + "Ljava/util/List;"
                + ")V"),
        remap = false)
    private void chromatictooltips$drawCompiled(TextRenderer renderer, GuiContext context,
        List<ITextLine> compiledLines) {
        final List<Object> tooltipComponents = new ArrayList<>();

        for (ITextLine line : compiledLines) {
            if (line instanceof TextLine textLine) {
                tooltipComponents.add(
                    this.chromatictooltips$fluid != null ? textLine.toString()
                        : TooltipUtils.applyBaseColorIfAbsent(textLine.toString(), EnumChatFormatting.WHITE));
            } else if (line instanceof Spacer spacer) {
                tooltipComponents.add(new SpaceComponent(spacer.getSpace()));
            } else {
                tooltipComponents.add(new TooltipComponentCompat(line, context, renderer.getColor(), false));
            }
        }

        if (this.chromatictooltips$fluid != null) {
            // the slot lines show the amount, and some slots (e.g. GT fluid lock) use a placeholder amount
            TooltipHandler.drawHoveringText(
                new TooltipRequest(
                    null,
                    TooltipTarget.ofFluid(this.chromatictooltips$fluid, 0),
                    new TooltipLines(tooltipComponents),
                    null));
        } else if (!tooltipComponents.isEmpty()) {

            if (!(tooltipComponents.size() > 1 && (tooltipComponents.get(0) instanceof String str
                && str.startsWith(EnumChatFormatting.WHITE.toString())
                && tooltipComponents.get(1) instanceof String str2
                && !str2.startsWith(EnumChatFormatting.WHITE.toString())))) {
                tooltipComponents.add(0, new TextComponent(""));
            }

            TooltipHandler.drawHoveringText(tooltipComponents);
        }
    }

    @Inject(
        method = "draw(Lcom/cleanroommc/modularui/screen/viewport/GuiContext;Lnet/minecraft/item/ItemStack;)V",
        at = @At("HEAD"),
        cancellable = true,
        remap = false)
    private void chromatictooltips$onDraw(GuiContext context, ItemStack stack, CallbackInfo ci) {

        if (stack != null) {
            TooltipHandler.drawHoveringText(stack, null);
            ci.cancel();
        }

    }

}
