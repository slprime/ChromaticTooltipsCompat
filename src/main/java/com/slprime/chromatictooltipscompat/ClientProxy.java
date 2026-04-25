package com.slprime.chromatictooltipscompat;

import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.item.ItemStack;

import com.slprime.chromatictooltips.TooltipRegistry;
import com.slprime.chromatictooltips.util.ItemStackFilterParser;
import com.slprime.chromatictooltips.util.TooltipUtils;
import com.slprime.chromatictooltipscompat.ChromaticTooltipsCompat.ModIds;
import com.slprime.chromatictooltipscompat.event.AppleCoreHandler;
import com.slprime.chromatictooltipscompat.event.AppliedEnergisticsHandler;
import com.slprime.chromatictooltipscompat.event.CompatHandler;
import com.slprime.chromatictooltipscompat.event.DraconicEvolutionHandler;
import com.slprime.chromatictooltipscompat.event.EnderCoreHandler;
import com.slprime.chromatictooltipscompat.event.NEIHandler;
import com.slprime.chromatictooltipscompat.event.TConstructHandler;
import com.slprime.chromatictooltipscompat.filter.GTTierFilterParser;
import com.slprime.chromatictooltipscompat.util.NEITargetSanitizer;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

public class ClientProxy extends CommonProxy implements IResourceManagerReloadListener {

    public static ItemStack chromatic$currentStack;

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);

        if (CompatConfig.notEnoughItemsEnabled && Loader.isModLoaded(ModIds.NEI)) {
            NEIHandler.registerHandler();
            TooltipRegistry.addTargetSanitizer(new NEITargetSanitizer());
        }

        if (CompatConfig.appleCoreEnabled && Loader.isModLoaded(ModIds.APPLECORE)) {
            AppleCoreHandler.registerHandler();
        }

        if (CompatConfig.gregtechEnabled && Loader.isModLoaded(ModIds.GT5)) {
            ItemStackFilterParser.registerCustomFilter("tier", new GTTierFilterParser());
        }

        if (CompatConfig.enderCoreEnabled && Loader.isModLoaded(ModIds.ENDERCORE)) {
            EnderCoreHandler.registerHandler();
        }

        if (CompatConfig.tinkersConstructEnabled && Loader.isModLoaded(ModIds.TCONSTRUCT)) {
            TConstructHandler.registerHandler();
        }

        if (CompatConfig.draconicEvolutionEnabled && Loader.isModLoaded(ModIds.DRACONIC_EVOLUTION)) {
            DraconicEvolutionHandler.registerHandler();
        }

        if (CompatConfig.appliedEnergisticsEnabled && Loader.isModLoaded(ModIds.APPLIED_ENERGISTICS2)) {
            AppliedEnergisticsHandler.registerHandler();
        }

        if (TooltipUtils.mc()
            .getResourceManager() instanceof IReloadableResourceManager manager) {
            manager.registerReloadListener(this);
        } else {
            CompatHandler.reload();
        }

        CompatHandler.registerHandler();
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        CompatHandler.reload();
    }

}
