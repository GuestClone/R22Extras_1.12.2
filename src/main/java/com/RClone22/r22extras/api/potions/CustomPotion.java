package com.RClone22.r22extras.api.potions;

import com.RClone22.r22extras.main.Constant;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CustomPotion extends Potion
{


    public CustomPotion(boolean isBadEffectIn, int liquidColorIn, int x, int y) {
        super(isBadEffectIn, liquidColorIn);
        this.setIconIndex(x, y);
    }

}
