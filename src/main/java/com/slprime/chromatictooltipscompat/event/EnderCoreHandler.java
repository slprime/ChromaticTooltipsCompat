package com.slprime.chromatictooltipscompat.event;

import java.util.Arrays;

import net.minecraftforge.common.MinecraftForge;

import com.enderio.core.api.common.enchant.IAdvancedEnchant;
import com.slprime.chromatictooltips.api.EnchantmentData;
import com.slprime.chromatictooltips.event.EnchantmentEnricherEvent;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EnderCoreHandler {

    public static void registerHandler() {
        EnderCoreHandler instance = new EnderCoreHandler();
        FMLCommonHandler.instance()
            .bus()
            .register(instance);
        MinecraftForge.EVENT_BUS.register(instance);
    }

    @SubscribeEvent
    public void onEnchantmentEvent(EnchantmentEnricherEvent event) {
        for (EnchantmentData ehchData : event.enchantments) {
            if (ehchData.hint.isEmpty() && ehchData.enchantment instanceof IAdvancedEnchant advancedEnchant) {
                ehchData.hint.addAll(Arrays.asList(advancedEnchant.getTooltipDetails(event.context.getStack())));
            }
        }
    }

}
