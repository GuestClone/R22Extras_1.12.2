package com.RClone22.r22extras.api.entityattribute;


import com.RClone22.r22extras.api.misc.nobadpotion.AntiBadPotionMain;
import com.RClone22.r22extras.main.potion.SupremeResistanceClass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
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




    public static void cancelEventSupResAttr(EntityEvent event)
    {
        if (event.getEntity() instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) event.getEntity();


            if (entity.getEntityAttribute(CustomEntityAttribute.SUP_RES_ATTR) != null &&
                    entity.getEntityAttribute(CustomEntityAttribute.SUP_RES_ATTR).getAttributeValue() > 0) {
                    event.setCanceled(true); // Cancel the event if allowed
            }
        }
    }


    public static void runEffectEntityTick(Entity entity)
    {
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

            if (livingEntityBase.getEntityAttribute(CustomEntityAttribute.SUP_RES_ATTR) != null &&
                    livingEntityBase.getEntityAttribute(CustomEntityAttribute.SUP_RES_ATTR).getAttributeValue() > 0) {
                livingEntityBase.heal(200.0F);
                livingEntityBase.extinguish();
                livingEntityBase.setAir(300);
                livingEntityBase.setEntityInvulnerable(true);

                AntiBadPotionMain.abpMainStatic(livingEntityBase);

                if (!livingEntityBase.world.isRemote && livingEntityBase instanceof EntityPlayer)
                {
                    ((EntityPlayer)livingEntityBase).getFoodStats().addStats(20, 20.0F);
                }
            }
        }
    }

    private static void addCustomAttributePri(EntityLivingBase entity) {
        // Check if the attribute is already added to prevent duplicates
        if (entity.getEntityAttribute(CustomEntityAttribute.SUP_RES_ATTR) != null) {
            entity.getAttributeMap().registerAttribute(CustomEntityAttribute.SUP_RES_ATTR);
            entity.getEntityAttribute(CustomEntityAttribute.SUP_RES_ATTR).setBaseValue(1.0); // Set a base value
        }
    }

    public static void addCustomAttribute(EntityLivingBase entity, EntityEvent Event) {
        if (entity.isPotionActive(MobEffects.RESISTANCE)) {

            addCustomAttributePri(entity);
        }
    }

}
