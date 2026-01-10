package com.slprime.chromatictooltipscompat;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static boolean adventurebackpackEnabled = true;
    public static boolean ae2fcEnabled = true;
    public static boolean ae2wctEnabled = true;
    public static boolean akashictomeEnabled = true;
    public static boolean appleCoreEnabled = true;
    public static boolean appliedEnergistics2Enabled = true;
    public static boolean avaritiaEnabled = true;
    public static boolean AWWayofTimeEnabled = true;
    public static boolean backpackEnabled = true;
    public static boolean baublesEnabled = true;
    public static boolean betterQuestingEnabled = true;
    public static boolean binnieCoreEnabled = true;
    public static boolean bloodArsenalEnabled = true;
    public static boolean botaniaEnabled = true;
    public static boolean codeChickenCoreEnabled = true;
    public static boolean computronicsEnabled = true;
    public static boolean draconicEvolutionEnabled = true;
    public static boolean emtEnabled = true;
    public static boolean enderCoreEnabled = true;
    public static boolean forestryEnabled = true;
    public static boolean galacticraftcoreEnabled = true;
    public static boolean gendustryEnabled = true;
    public static boolean gregtechEnabled = true;
    public static boolean holoinventoryEnabled = true;
    public static boolean inventoryTweaksEnabled = true;
    public static boolean logisticsPipesEnabled = true;
    public static boolean modularUIEnabled = true;
    public static boolean modularUI2Enabled = true;
    public static boolean notEnoughItemsEnabled = true;
    public static boolean neicustomdiagramEnabled = true;
    public static boolean railcraftEnabled = true;
    public static boolean sfmEnabled = true;
    public static boolean spiceoflifeEnabled = true;
    public static boolean storageDrawersEnabled = true;
    public static boolean supersolarpanelEnabled = true;
    public static boolean thaumicenergisticsEnabled = true;
    public static boolean tinkersConstructEnabled = true;
    public static boolean wailaEnabled = true;
    public static boolean wawlaEnabled = true;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        configuration.setCategoryRequiresMcRestart(Configuration.CATEGORY_GENERAL, true);

        adventurebackpackEnabled = configuration.getBoolean(
            "adventurebackpackEnabled",
            Configuration.CATEGORY_GENERAL,
            adventurebackpackEnabled,
            "Enable compatibility with Adventure Backpack");

        ae2fcEnabled = configuration.getBoolean(
            "ae2fcEnabled",
            Configuration.CATEGORY_GENERAL,
            ae2fcEnabled,
            "Enable compatibility with AE2 Fluid Crafting");

        ae2wctEnabled = configuration.getBoolean(
            "ae2wctEnabled",
            Configuration.CATEGORY_GENERAL,
            ae2wctEnabled,
            "Enable compatibility with AE2 Wireless Crafting Terminal");

        akashictomeEnabled = configuration.getBoolean(
            "akashictomeEnabled",
            Configuration.CATEGORY_GENERAL,
            akashictomeEnabled,
            "Enable compatibility with Akashic Tome");

        appleCoreEnabled = configuration.getBoolean(
            "appleCoreEnabled",
            Configuration.CATEGORY_GENERAL,
            appleCoreEnabled,
            "Enable compatibility with AppleCore");

        appliedEnergistics2Enabled = configuration.getBoolean(
            "appliedEnergistics2Enabled",
            Configuration.CATEGORY_GENERAL,
            appliedEnergistics2Enabled,
            "Enable compatibility with Applied Energistics 2");

        avaritiaEnabled = configuration.getBoolean(
            "avaritiaEnabled",
            Configuration.CATEGORY_GENERAL,
            avaritiaEnabled,
            "Enable compatibility with Avaritia");

        AWWayofTimeEnabled = configuration.getBoolean(
            "AWWayofTimeEnabled",
            Configuration.CATEGORY_GENERAL,
            AWWayofTimeEnabled,
            "Enable compatibility with Blood Magic: Alchemical Wizardry");

        backpackEnabled = configuration.getBoolean(
            "backpackEnabled",
            Configuration.CATEGORY_GENERAL,
            backpackEnabled,
            "Enable compatibility with Backpack Edited for ModdedNetwork");

        baublesEnabled = configuration.getBoolean(
            "baublesEnabled",
            Configuration.CATEGORY_GENERAL,
            baublesEnabled,
            "Enable compatibility with Baubles");

        betterQuestingEnabled = configuration.getBoolean(
            "betterQuestingEnabled",
            Configuration.CATEGORY_GENERAL,
            betterQuestingEnabled,
            "Enable compatibility with Better Questing");

        binnieCoreEnabled = configuration.getBoolean(
            "binnieCoreEnabled",
            Configuration.CATEGORY_GENERAL,
            binnieCoreEnabled,
            "Enable compatibility with Binnie Core");

        bloodArsenalEnabled = configuration.getBoolean(
            "bloodArsenalEnabled",
            Configuration.CATEGORY_GENERAL,
            bloodArsenalEnabled,
            "Enable compatibility with Blood Arsenal");

        botaniaEnabled = configuration.getBoolean(
            "botaniaEnabled",
            Configuration.CATEGORY_GENERAL,
            botaniaEnabled,
            "Enable compatibility with Botania");

        codeChickenCoreEnabled = configuration.getBoolean(
            "codeChickenCoreEnabled",
            Configuration.CATEGORY_GENERAL,
            codeChickenCoreEnabled,
            "Enable compatibility with CodeChicken Core");

        computronicsEnabled = configuration.getBoolean(
            "computronicsEnabled",
            Configuration.CATEGORY_GENERAL,
            computronicsEnabled,
            "Enable compatibility with Computronics");

        draconicEvolutionEnabled = configuration.getBoolean(
            "draconicEvolutionEnabled",
            Configuration.CATEGORY_GENERAL,
            draconicEvolutionEnabled,
            "Enable compatibility with Draconic Evolution");

        emtEnabled = configuration.getBoolean(
            "emtEnabled",
            Configuration.CATEGORY_GENERAL,
            emtEnabled,
            "Enable compatibility with Electro-Magic Tools");

        enderCoreEnabled = configuration.getBoolean(
            "enderCoreEnabled",
            Configuration.CATEGORY_GENERAL,
            enderCoreEnabled,
            "Enable compatibility with Ender IO Core");

        forestryEnabled = configuration.getBoolean(
            "forestryEnabled",
            Configuration.CATEGORY_GENERAL,
            forestryEnabled,
            "Enable compatibility with Forestry");

        galacticraftcoreEnabled = configuration.getBoolean(
            "galacticraftcoreEnabled",
            Configuration.CATEGORY_GENERAL,
            galacticraftcoreEnabled,
            "Enable compatibility with Galacticraft Core");

        gendustryEnabled = configuration.getBoolean(
            "gendustryEnabled",
            Configuration.CATEGORY_GENERAL,
            gendustryEnabled,
            "Enable compatibility with Gendustry");

        gregtechEnabled = configuration.getBoolean(
            "gregtechEnabled",
            Configuration.CATEGORY_GENERAL,
            gregtechEnabled,
            "Enable compatibility with GregTech");

        holoinventoryEnabled = configuration.getBoolean(
            "holoinventoryEnabled",
            Configuration.CATEGORY_GENERAL,
            holoinventoryEnabled,
            "Enable compatibility with HoloInventory");

        inventoryTweaksEnabled = configuration.getBoolean(
            "inventoryTweaksEnabled",
            Configuration.CATEGORY_GENERAL,
            inventoryTweaksEnabled,
            "Enable compatibility with Inventory Tweaks");

        logisticsPipesEnabled = configuration.getBoolean(
            "logisticsPipesEnabled",
            Configuration.CATEGORY_GENERAL,
            logisticsPipesEnabled,
            "Enable compatibility with Logistics Pipes");

        modularUIEnabled = configuration.getBoolean(
            "modularUIEnabled",
            Configuration.CATEGORY_GENERAL,
            modularUIEnabled,
            "Enable compatibility with Modular UI");

        modularUI2Enabled = configuration.getBoolean(
            "modularUI2Enabled",
            Configuration.CATEGORY_GENERAL,
            modularUI2Enabled,
            "Enable compatibility with Modular UI 2");

        notEnoughItemsEnabled = configuration.getBoolean(
            "notEnoughItemsEnabled",
            Configuration.CATEGORY_GENERAL,
            notEnoughItemsEnabled,
            "Enable compatibility with Not Enough Items");

        neicustomdiagramEnabled = configuration.getBoolean(
            "neicustomdiagramEnabled",
            Configuration.CATEGORY_GENERAL,
            neicustomdiagramEnabled,
            "Enable compatibility with NEI Custom Diagram");

        railcraftEnabled = configuration.getBoolean(
            "railcraftEnabled",
            Configuration.CATEGORY_GENERAL,
            railcraftEnabled,
            "Enable compatibility with Railcraft");

        sfmEnabled = configuration.getBoolean(
            "sfmEnabled",
            Configuration.CATEGORY_GENERAL,
            sfmEnabled,
            "Enable compatibility with Steve's Factory Manager");

        spiceoflifeEnabled = configuration.getBoolean(
            "spiceoflifeEnabled",
            Configuration.CATEGORY_GENERAL,
            spiceoflifeEnabled,
            "Enable compatibility with The Spice of Life - Carrot Edition");

        storageDrawersEnabled = configuration.getBoolean(
            "storageDrawersEnabled",
            Configuration.CATEGORY_GENERAL,
            storageDrawersEnabled,
            "Enable compatibility with Storage Drawers");

        supersolarpanelEnabled = configuration.getBoolean(
            "supersolarpanelEnabled",
            Configuration.CATEGORY_GENERAL,
            supersolarpanelEnabled,
            "Enable compatibility with Super Solar Panel");

        thaumicenergisticsEnabled = configuration.getBoolean(
            "thaumicenergisticsEnabled",
            Configuration.CATEGORY_GENERAL,
            thaumicenergisticsEnabled,
            "Enable compatibility with Thaumic Energistics");

        tinkersConstructEnabled = configuration.getBoolean(
            "tinkersConstructEnabled",
            Configuration.CATEGORY_GENERAL,
            tinkersConstructEnabled,
            "Enable compatibility with Tinkers' Construct");

        wailaEnabled = configuration.getBoolean(
            "wailaEnabled",
            Configuration.CATEGORY_GENERAL,
            wailaEnabled,
            "Enable compatibility with WAILA");

        wawlaEnabled = configuration.getBoolean(
            "wawlaEnabled",
            Configuration.CATEGORY_GENERAL,
            wawlaEnabled,
            "Enable compatibility with WAWLA");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
