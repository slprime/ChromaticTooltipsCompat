package com.slprime.chromatictooltipscompat;

import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.item.ItemStack;

import com.slprime.chromatictooltips.util.ClientUtil;
import com.slprime.chromatictooltips.util.ItemStackFilterParser;
import com.slprime.chromatictooltipscompat.ChromaticTooltipsCompat.ModIds;
import com.slprime.chromatictooltipscompat.event.AppleCoreHandler;
import com.slprime.chromatictooltipscompat.event.CompatHander;
import com.slprime.chromatictooltipscompat.event.DraconicEvolutionHandler;
import com.slprime.chromatictooltipscompat.event.EnderCoreHandler;
import com.slprime.chromatictooltipscompat.event.NEIHandler;
import com.slprime.chromatictooltipscompat.event.TConstructHandler;
import com.slprime.chromatictooltipscompat.filter.GTTierFilterParser;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

public class ClientProxy extends CommonProxy implements IResourceManagerReloadListener {

    public static ItemStack chromatic$currentStack;

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);

        if (Config.notEnoughItemsEnabled && Loader.isModLoaded(ModIds.NEI)) {
            NEIHandler.registerHandler();
        }

        if (Config.appleCoreEnabled && Loader.isModLoaded(ModIds.APPLECORE)) {
            AppleCoreHandler.registerHandler();
        }

        if (Config.gregtechEnabled && Loader.isModLoaded(ModIds.GT5)) {
            ItemStackFilterParser.registerCustomFilter("tier", new GTTierFilterParser());
        }

        if (Config.enderCoreEnabled && Loader.isModLoaded(ModIds.ENDERCORE)) {
            EnderCoreHandler.registerHandler();
        }

        if (Config.tinkersConstructEnabled && Loader.isModLoaded(ModIds.TCONSTRUCT)) {
            TConstructHandler.registerHandler();
        }

        if (Config.draconicEvolutionEnabled && Loader.isModLoaded(ModIds.DRACONIC_EVOLUTION)) {
            DraconicEvolutionHandler.registerHandler();
        }

        if (ClientUtil.mc()
            .getResourceManager() instanceof IReloadableResourceManager manager) {
            manager.registerReloadListener(this);
        } else {
            CompatHander.reload();
        }

        CompatHander.registerHandler();
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        CompatHander.reload();
    }

}
