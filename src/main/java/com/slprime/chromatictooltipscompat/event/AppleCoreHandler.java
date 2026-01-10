package com.slprime.chromatictooltipscompat.event;

import net.minecraftforge.common.MinecraftForge;

import com.slprime.chromatictooltips.event.RenderTooltipEvent;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import squeek.applecore.client.TooltipOverlayHandler;

public class AppleCoreHandler {

    public static void registerHandler() {
        AppleCoreHandler instance = new AppleCoreHandler();
        FMLCommonHandler.instance()
            .bus()
            .register(instance);
        MinecraftForge.EVENT_BUS.register(instance);
    }

    @SubscribeEvent
    public void onTooltipRender(RenderTooltipEvent event) {
        TooltipOverlayHandler.toolTipX = event.x;
        TooltipOverlayHandler.toolTipY = event.y;
        TooltipOverlayHandler.toolTipW = event.width;
        TooltipOverlayHandler.toolTipH = event.height - 1;
    }
}
