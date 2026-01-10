package com.slprime.chromatictooltipscompat.util;

import java.util.function.Supplier;

import net.minecraft.util.EnumChatFormatting;

import com.slprime.chromatictooltips.api.ITooltipComponent;
import com.slprime.chromatictooltips.api.TooltipContext;
import com.slprime.chromatictooltips.util.TooltipFontContext;

public class AnimatedTooltipComponentCompat implements ITooltipComponent {

    protected final Supplier<String> handler;
    protected String text;
    protected int width;
    protected int height;

    public AnimatedTooltipComponentCompat(String text, Supplier<String> handler) {
        this.handler = handler;
        this.text = text;
    }

    public Supplier<String> getHandler() {
        return this.handler;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getSpacing() {
        return 0;
    }

    public ITooltipComponent[] paginate(TooltipContext context, int maxWidth, int maxHeight) {
        this.width = TooltipFontContext.getStringWidth(text);
        this.height = TooltipFontContext.getFontHeight();
        return new ITooltipComponent[] { this };
    }

    @Override
    public void draw(int x, int y, int availableWidth, TooltipContext context) {
        TooltipFontContext.drawString(this.handler.get(), x, y, 0xFFFFFFFF);
    }

    @Override
    public int hashCode() {
        return EnumChatFormatting.getTextWithoutFormattingCodes(this.text)
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof AnimatedTooltipComponentCompat other) {
            return EnumChatFormatting.getTextWithoutFormattingCodes(this.text)
                .equals(EnumChatFormatting.getTextWithoutFormattingCodes(other.text));
        }

        return false;
    }

}
