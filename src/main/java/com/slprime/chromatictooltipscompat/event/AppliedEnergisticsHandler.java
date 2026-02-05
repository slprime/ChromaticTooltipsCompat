package com.slprime.chromatictooltipscompat.event;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.common.MinecraftForge;

import com.slprime.chromatictooltips.event.StackSizeEnricherEvent;
import com.slprime.chromatictooltips.util.TooltipUtils;

import appeng.api.storage.data.IAEStack;
import appeng.client.gui.AEBaseGui;
import appeng.client.gui.slots.VirtualMESlot;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class AppliedEnergisticsHandler {

    public static void registerHandler() {
        AppliedEnergisticsHandler instance = new AppliedEnergisticsHandler();
        FMLCommonHandler.instance()
            .bus()
            .register(instance);
        MinecraftForge.EVENT_BUS.register(instance);
    }

    @SubscribeEvent
    public void onStackSizeEnricherEvent(StackSizeEnricherEvent event) {
        final GuiContainer gui = TooltipUtils.getGuiContainer();

        if (gui instanceof AEBaseGui basesGui) {
            final VirtualMESlot hoveredSlot = basesGui.getVirtualMESlotUnderMouse();

            if (hoveredSlot != null) {
                final IAEStack<?> aes = hoveredSlot.getAEStack();
                event.stackAmount = aes != null ? aes.getStackSize() : event.stackAmount;
            }
        }
    }

}
