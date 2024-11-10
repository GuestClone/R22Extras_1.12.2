package com.RClone22.r22extras.api.entityattribute;


import com.RClone22.r22extras.api.misc.nobadpotion.AntiBadPotionMain;
import com.RClone22.r22extras.main.potion.SupremeResistanceClass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.common.util.FakePlayer;

import java.util.UUID;

public class CustomEntityAttribute
{


    public static final UUID SUP_RES_ATTR_UUID = UUID.fromString("2b438da8-fcc6-4123-864a-6559fe98c3d7"); // Generate a unique UUID
    public static final IAttribute SUP_RES_ATTR =
            (new RangedAttribute(null,
                    "mod.sup_res_attr",
                    1.0D,
                    0.0D,
                    Double.MAX_VALUE)
            ).setDescription("Super Resistance Attribute").setShouldWatch(true);





}

