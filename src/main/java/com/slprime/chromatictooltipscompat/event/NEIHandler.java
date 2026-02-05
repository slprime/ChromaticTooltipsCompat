package com.slprime.chromatictooltipscompat.event;

import java.awt.Point;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.common.MinecraftForge;

import com.slprime.chromatictooltips.TooltipRegistry;
import com.slprime.chromatictooltips.api.EnricherPlace;
import com.slprime.chromatictooltips.api.ITooltipComponent;
import com.slprime.chromatictooltips.api.ITooltipEnricher;
import com.slprime.chromatictooltips.api.TooltipContext;
import com.slprime.chromatictooltips.api.TooltipLines;
import com.slprime.chromatictooltips.api.TooltipModifier;
import com.slprime.chromatictooltips.api.TooltipTarget;
import com.slprime.chromatictooltips.event.ContextInfoEnricherEvent;
import com.slprime.chromatictooltips.event.FluidInfoEnricherEvent;
import com.slprime.chromatictooltips.event.HotkeyEnricherEvent;
import com.slprime.chromatictooltips.event.TextLinesConverterEvent;
import com.slprime.chromatictooltips.event.TitleEnricherEvent;
import com.slprime.chromatictooltips.util.TooltipUtils;
import com.slprime.chromatictooltipscompat.ChromaticTooltipsCompat.ModIds;
import com.slprime.chromatictooltipscompat.Config;

import codechicken.lib.gui.GuiDraw;
import codechicken.lib.gui.GuiDraw.ITooltipLineHandler;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.guihook.IContainerTooltipHandler;
import codechicken.nei.util.ItemUntranslator;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gregtech.api.util.GTUtility;

public class NEIHandler {

    protected static class SecondTitleEnricher implements ITooltipEnricher {

        public String sectionId() {
            return "untranslator";
        }

        public EnricherPlace place() {
            return EnricherPlace.HEADER;
        }

        public EnumSet<TooltipModifier> mode() {
            return EnumSet.of(TooltipModifier.NONE);
        }

        public TooltipLines build(TooltipContext context) {
            ItemStack itemStack = context.getItem();

            if (itemStack == null && context.getTarget()
                .isFluid() && Config.gregtechEnabled && Loader.isModLoaded(ModIds.GT5)) {
                itemStack = GTUtility.getFluidDisplayStack(context.getFluid(), true);
            }

            if (itemStack != null) {
                final String subtitle = ItemUntranslator.getInstance()
                    .getItemStackDisplayName(itemStack);

                if (subtitle != null && !subtitle.isEmpty()) {
                    return new TooltipLines(EnumChatFormatting.DARK_GRAY + subtitle);
                }
            }

            return null;
        }
    }

    protected static class TooltipComponentCompat implements ITooltipComponent {

        protected final ITooltipLineHandler lineHandler;

        public TooltipComponentCompat(ITooltipLineHandler lineHandler) {
            this.lineHandler = lineHandler;
        }

        public int getWidth() {
            return this.lineHandler.getSize().width;
        }

        public int getHeight() {
            return this.lineHandler.getSize().height;
        }

        public int getSpacing() {
            return 0;
        }

        public void draw(int x, int y, int availableWidth, TooltipContext context) {
            this.lineHandler.draw(x, y);
        }

        @Override
        public int hashCode() {
            return this.lineHandler.hashCode();
        }

        @Override
        public boolean equals(Object obj) {

            if (obj instanceof TooltipComponentCompat other) {
                return this.lineHandler.equals(other.lineHandler);
            }

            return false;
        }

    }

    protected static List<ITooltipLineHandler> tipLineHandlers = new ArrayList<>();

    public static void registerHandler() {
        NEIHandler instance = new NEIHandler();
        FMLCommonHandler.instance()
            .bus()
            .register(instance);
        MinecraftForge.EVENT_BUS.register(instance);
        TooltipRegistry.addEnricherAfter("title", new SecondTitleEnricher());

        try {
            final Field field = GuiDraw.class.getDeclaredField("tipLineHandlers");
            field.setAccessible(true);
            NEIHandler.tipLineHandlers = (List<ITooltipLineHandler>) field.get(null);
        } catch (Exception ex) {}

    }

    protected List<IContainerTooltipHandler> getInstanceTooltipHandlers() {
        final GuiContainerManager manager = GuiContainerManager.getManager();
        return Collections.unmodifiableList(manager != null ? manager.instanceTooltipHandlers : new ArrayList<>());
    }

    protected ItemStack getItemStackFromContext(TooltipTarget target) {
        ItemStack stack = target.getItem();

        if (stack == null && target.isFluid() && Config.gregtechEnabled && Loader.isModLoaded(ModIds.GT5)) {
            stack = GTUtility.getFluidDisplayStack(target.getFluid(), true);
        }

        return stack;
    }

    @SubscribeEvent
    public void onTitleEnricherEvent(TitleEnricherEvent event) {
        final ItemStack stack = getItemStackFromContext(event.target);

        if (stack == null) {
            return;
        }

        final GuiContainer gui = NEIClientUtils.getGuiContainer();
        List<String> namelist = new ArrayList<>();
        namelist.add(event.displayName);

        for (IContainerTooltipHandler handler : getInstanceTooltipHandlers()) {
            namelist = handler.handleItemDisplayName(gui, stack, namelist);
        }

        if (!namelist.isEmpty()) {
            event.displayName = namelist.get(0);
        }

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onContextInfoEnricherEvent(ContextInfoEnricherEvent event) {
        final GuiContainer gui = NEIClientUtils.getGuiContainer();
        final Point mouse = TooltipUtils.getMousePosition();
        final ItemStack stack = getItemStackFromContext(event.target);
        List<String> tooltip = new ArrayList<>();
        tooltip.add("Temporary Name"); // temporary name added for information gathering

        for (IContainerTooltipHandler handler : getInstanceTooltipHandlers()) {
            tooltip = handler.handleItemTooltip(gui, stack, mouse.x, mouse.y, tooltip);
        }

        if (!tooltip.isEmpty()) {
            tooltip.remove(0); // remove temporary name
        }

        event.tooltip.addAll(tooltip);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onFluidInfoEnricherEvent(FluidInfoEnricherEvent event) {
        if (Config.gregtechEnabled && Loader.isModLoaded(ModIds.GT5)) {
            final ItemStack stack = GTUtility.getFluidDisplayStack(
                event.target.getFluid()
                    .getFluid());

            if (stack == null) {
                return;
            }

            List<String> tooltip = new ArrayList<>();
            tooltip.add("Temporary Name"); // temporary name added for information gathering

            stack.getItem()
                .addInformation(stack, TooltipUtils.mc().thePlayer, tooltip, false);

            if (!tooltip.isEmpty()) {
                tooltip.remove(0); // remove temporary name
            }

            event.tooltip.addAll(tooltip);
        }
    }

    @SubscribeEvent
    public void onHotkeyEnricherEvent(HotkeyEnricherEvent event) {
        final GuiContainer gui = NEIClientUtils.getGuiContainer();
        final Point mouse = TooltipUtils.getMousePosition();

        for (IContainerTooltipHandler handler : getInstanceTooltipHandlers()) {
            event.hotkeys = handler.handleHotkeys(gui, mouse.x, mouse.y, event.hotkeys);
        }

    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onTextLinesConverterEvent(TextLinesConverterEvent event) {

        if (NEIHandler.tipLineHandlers.isEmpty()) {
            return;
        }

        for (int i = 0; i < event.list.size(); i++) {
            if (event.list.get(i) instanceof String str && !str.isEmpty()) {
                final ITooltipLineHandler lineHandler = GuiDraw
                    .getTipLine(EnumChatFormatting.getTextWithoutFormattingCodes(str));
                if (lineHandler != null) {
                    event.list.set(i, new TooltipComponentCompat(lineHandler));
                }
            }
        }

    }

    @SubscribeEvent
    public void onScreenPostDraw(DrawScreenEvent.Post event) {
        NEIHandler.tipLineHandlers.clear();
    }

}
