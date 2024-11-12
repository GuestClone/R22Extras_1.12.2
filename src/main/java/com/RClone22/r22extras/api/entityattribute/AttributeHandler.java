package com.RClone22.r22extras.api.entityattribute;

import com.RClone22.r22extras.api.misc.nobadpotion.AntiBadPotionMain;
import com.RClone22.r22extras.api.potions.PotionUtilses;
import com.RClone22.r22extras.api.utils.EntityInvul;
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

    public static double potionSupRes = 1.2D;

    public static double potionCrbe = 1.1D;

    public static double inteFace = 1.0D;


    public static void handleSupResAttr(Entity entity) {
        EntityLivingBase livingEntityBase = (EntityLivingBase) entity;
        PotionEffect potionEffectB = PotionUtilses.getPotionEffectPETByRegistryName(livingEntityBase, PotionUtilses.potionSupRes);

        if (potionEffectB != null) {
            // Get or register the custom attribute
            IAttributeInstance attributeInstance = livingEntityBase.getAttributeMap().getAttributeInstance(CustomEntityAttribute.SUP_RES_ATTR);

            if (attributeInstance == null) {
                // If the attribute doesn't exist, register it and get the instance
                livingEntityBase.getAttributeMap().registerAttribute(CustomEntityAttribute.SUP_RES_ATTR);
                attributeInstance = livingEntityBase.getAttributeMap().getAttributeInstance(CustomEntityAttribute.SUP_RES_ATTR);
            }

            // Set the value of the custom attribute to indicate the presence of the potion effect
            attributeInstance.setBaseValue(potionSupRes + potionEffectB.getAmplifier());  // This could indicate that the potion effect is active
        } else {
            // If the potion effect is no longer present, reset the attribute
            IAttributeInstance attributeInstance = livingEntityBase.getAttributeMap().getAttributeInstance(CustomEntityAttribute.SUP_RES_ATTR);

            // Reset the attribute's value when the potion is not present
            if (attributeInstance != null) {
                attributeInstance.setBaseValue(0.0D);  // Reset value to indicate no effect
            }
        }

        if (entity instanceof EntityInvul.IEntityInvul) {
            EntityInvul.IEntityInvul customEntity = (EntityInvul.IEntityInvul) entity;


            if (customEntity.setEntityInvulnerable(entity)) {
                IAttributeInstance attributeInstance = livingEntityBase.getAttributeMap().getAttributeInstance(CustomEntityAttribute.SUP_RES_ATTR);

                if (attributeInstance == null) {

                    livingEntityBase.getAttributeMap().registerAttribute(CustomEntityAttribute.SUP_RES_ATTR);
                    attributeInstance = livingEntityBase.getAttributeMap().getAttributeInstance(CustomEntityAttribute.SUP_RES_ATTR);
                }


                attributeInstance.setBaseValue(inteFace);
            }
        }
    }






    @SubscribeEvent
    public static void onEntityLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

            AttributeHandler.handleSupResAttr(livingEntityBase);


            if (AttributeUtil.hasCustomAttributeByRegistryName(livingEntityBase, CustomEntityAttribute.SUP_RES_ATTR)) {
                AntiBadPotionMain.abpMainStatic(livingEntityBase);
                PotionUtilses.addPotionEffectByRegistryName(livingEntityBase, "minecraft:luck", 100, 2, false, false);
            }

        }

    }




}
