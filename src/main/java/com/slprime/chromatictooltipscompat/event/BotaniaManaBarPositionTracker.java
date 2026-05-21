package com.slprime.chromatictooltipscompat.event;

import com.slprime.chromatictooltips.event.RenderTooltipEvent;
import com.slprime.chromatictooltips.util.TooltipUtils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BotaniaManaBarPositionTracker {

    public static int lastTooltipX = 0;
    public static int lastTooltipY = 0;
    public static int lastTooltipWidth = 0;
    public static boolean hasPosition = false;

    public static void registerHandler() {
        TooltipUtils.registerEventListener(new BotaniaManaBarPositionTracker());
    }

    @SubscribeEvent
    public void onTooltipRender(RenderTooltipEvent event) {
        lastTooltipX = event.x + 3;
        lastTooltipY = event.y;
        lastTooltipWidth = event.width - 6;
        hasPosition = true;
    }
}
