package com.slprime.chromatictooltipscompat.mixins.late.ae2fc;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.glodblock.github.common.block.BlockCertusQuartzTank;

@Mixin(BlockCertusQuartzTank.class)
public class BlockCertusQuartzTankMixin {

    /**
     * @author SLPrime
     * @reason Disable default tooltip info
     */
    @Overwrite(remap = false)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        // NO-OP to disable default tooltip info
    }

}
