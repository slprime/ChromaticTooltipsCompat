package com.slprime.chromatictooltipscompat.mixins.late.malisisdoors;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import net.malisis.core.client.gui.GuiRenderer;
import net.malisis.core.client.gui.component.decoration.UITooltip;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.StatCollector;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.slprime.chromatictooltips.TooltipHandler;

@Mixin(value = GuiRenderer.class)
public class GuiRendererMixin {

    private static Pattern pattern = Pattern.compile("\\{(.*?)}");

    /**
     * @author SLPrime
     * @reason Redirect Malisis UITooltip rendering through Chromatic Tooltips
     */
    @Overwrite(remap = false)
    public void drawTooltip(UITooltip tooltip, Tessellator tess) {

        if (tooltip == null || !tooltip.isVisible()) {
            return;
        }

        final List<String> lines = ((UITooltipAccessor) tooltip).chromatictooltipscompat$getLines();

        if (lines != null && !lines.isEmpty()) {
            TooltipHandler.drawHoveringText(
                lines.stream()
                    .map(this::translate)
                    .collect(Collectors.toList()));
        }
    }

    /* copy from MalisisFont */
    private String translate(String str) {
        if (str.indexOf('{') == -1 || str.indexOf('{') >= str.indexOf('}')) return StatCollector.translateToLocal(str);

        StringBuffer output = new StringBuffer();
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) matcher.appendReplacement(output, StatCollector.translateToLocal(matcher.group(1)));

        matcher.appendTail(output);
        return output.toString();
    }
}
