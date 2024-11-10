package com.RClone22.r22extras.api.entityattribute;

import com.RClone22.r22extras.api.potions.PotionUtilses;
import com.RClone22.r22extras.main.Constantr22Extras;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Constantr22Extras.MODID)
public class AttributeHandler
{

    public static void handlePotionEffect(EntityLivingBase entity) {
        if (PotionUtilses.hasPotionEffectByRegistryName(entity, PotionUtilses.potionSupRes)) {
            // Get or register the custom attribute
            IAttributeInstance attributeInstance = entity.getAttributeMap().getAttributeInstance(CustomEntityAttribute.SUP_RES_ATTR);

            if (attributeInstance == null) {
                // If the attribute doesn't exist, register it and get the instance
                entity.getAttributeMap().registerAttribute(CustomEntityAttribute.SUP_RES_ATTR);
                attributeInstance = entity.getAttributeMap().getAttributeInstance(CustomEntityAttribute.SUP_RES_ATTR);
            }

            // Set the value of the custom attribute to indicate the presence of the potion effect
            attributeInstance.setBaseValue(1.0D);  // This could indicate that the potion effect is active
        } else {
            // If the potion effect is no longer present, reset the attribute
            IAttributeInstance attributeInstance = entity.getAttributeMap().getAttributeInstance(CustomEntityAttribute.SUP_RES_ATTR);

            // Reset the attribute's value when the potion is not present
            if (attributeInstance != null) {
                attributeInstance.setBaseValue(0.0D);  // Reset value to indicate no effect
            }
        }


    }

    @SubscribeEvent
    public static void onEntityLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

            AttributeHandler.handlePotionEffect(livingEntityBase);



            if (AttributeUtil.hasCustomAttributeByRegistryName(livingEntityBase, CustomEntityAttribute.SUP_RES_ATTR)) {
                // Apply the Luck effect if it's not already active
                PotionUtilses.addPotionEffectByRegistryName(livingEntityBase, "minecraft:luck", 20, 2, false, false);
            }

        }

    }




}
