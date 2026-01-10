package com.slprime.chromatictooltipscompat.mixins.late.gregtech;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.util.EnumChatFormatting;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.gtnewhorizon.gtnhlib.util.AnimatedTooltipHandler;
import com.slprime.chromatictooltips.TooltipHandler;
import com.slprime.chromatictooltips.api.ITooltipComponent;
import com.slprime.chromatictooltipscompat.util.AnimatedTooltipComponentCompat;

@Mixin(AnimatedTooltipHandler.class)
public class AnimatedTooltipHandlerMixin {

    protected static class LRUCache<K, V> extends LinkedHashMap<K, V> {

        private final int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }

    }

    private static LRUCache<String, ITooltipComponent> cache = new LRUCache<>(100);

    @Inject(method = "chain", at = @At("HEAD"), cancellable = true, remap = false, require = 0)
    private static void chain$before(Supplier<String>[] parts, CallbackInfoReturnable<Supplier<String>> cir) {
        for (int i = 0; i < parts.length; i++) {
            parts[i] = restoreAnimatedTextSupplier(parts[i]);
        }
    }

    @Inject(method = "chain", at = @At("RETURN"), cancellable = true, remap = false, require = 0)
    private static void chain$after(Supplier<String>[] parts, CallbackInfoReturnable<Supplier<String>> cir) {
        final Supplier<String> original = cir.getReturnValue();
        final String key = EnumChatFormatting.getTextWithoutFormattingCodes(original.get());

        cir.setReturnValue(() -> {
            final ITooltipComponent component = cache
                .computeIfAbsent(key, k -> new AnimatedTooltipComponentCompat(key, original));
            return TooltipHandler.getComponentId(component);
        });
    }

    @Inject(method = "animatedText", at = @At("RETURN"), cancellable = true, remap = false)
    private static void animatedTextReturn(String text, int posstep, int delay, String[] formattingArray,
        CallbackInfoReturnable<Supplier<String>> cir) {

        if (text != null && !text.isEmpty() && formattingArray != null && formattingArray.length != 0) {
            final Supplier<String> result = cir.getReturnValue();
            final String key = text + ":" + posstep + ":" + delay;

            cir.setReturnValue(() -> {
                final ITooltipComponent component = cache
                    .computeIfAbsent(key, k -> new AnimatedTooltipComponentCompat(text, result));
                return TooltipHandler.getComponentId(component);
            });
        }

    }

    private static Supplier<String> restoreAnimatedTextSupplier(Supplier<String> original) {
        final String key = original.get();
        final ITooltipComponent component = TooltipHandler.getTooltipComponent(key);

        if (component != null && component instanceof AnimatedTooltipComponentCompat anim) {
            return anim.getHandler();
        }

        return original;
    }

}
