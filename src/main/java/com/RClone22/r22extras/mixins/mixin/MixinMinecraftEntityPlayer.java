package com.RClone22.r22extras.mixins.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.FoodStats;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = EntityPlayer.class, remap = false)
public abstract class MixinMinecraftEntityPlayer
{

    @Shadow
    public abstract FoodStats getFoodStats();

    @Shadow
    protected FoodStats foodStats = new FoodStats();

}
