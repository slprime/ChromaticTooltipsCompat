package com.slprime.chromatictooltipscompat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(
    modid = ChromaticTooltipsCompat.MODID,
    version = Tags.VERSION,
    name = "ChromaticTooltipsCompat",
    acceptedMinecraftVersions = "[1.7.10]")
public class ChromaticTooltipsCompat {

    public static final String MODID = "chromatictooltipscompat";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(
        clientSide = "com.slprime.chromatictooltipscompat.ClientProxy",
        serverSide = "com.slprime.chromatictooltipscompat.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    public static class ModIds {

        public static final String NEI = "NotEnoughItems";
        public static final String GT5 = "gregtech";
        public static final String ENDERCORE = "endercore";
        public static final String APPLECORE = "AppleCore";
        public static final String WAWLA = "wawla";
        public static final String TCONSTRUCT = "TConstruct";
        public static final String DRACONIC_EVOLUTION = "DraconicEvolution";

    }
}
