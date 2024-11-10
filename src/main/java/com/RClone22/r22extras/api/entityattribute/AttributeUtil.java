package com.RClone22.r22extras.api.entityattribute;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

public class AttributeUtil
{



    public static boolean hasCustomAttributeByRegistryName(EntityLivingBase entity, IAttribute customAttribute) {
        IAttributeInstance attributeInstance = entity.getAttributeMap().getAttributeInstance(customAttribute);
        return attributeInstance != null && attributeInstance.getBaseValue() > 0;
    }


    public static IAttributeInstance getCustomAttributeInstanceByRegistryName(EntityLivingBase entity, IAttribute customAttribute) {
        return entity.getAttributeMap().getAttributeInstance(customAttribute);
    }


    public static void applyCustomAttribute(EntityLivingBase entity, IAttribute customAttribute, double value) {
        IAttributeInstance attributeInstance = entity.getAttributeMap().getAttributeInstance(customAttribute);

        if (attributeInstance == null) {
            // Register the attribute if it doesn't exist
            entity.getAttributeMap().registerAttribute(customAttribute);
            attributeInstance = entity.getAttributeMap().getAttributeInstance(customAttribute);
        }

        // Set the attribute's base value to the specified value
        if (attributeInstance != null) {
            attributeInstance.setBaseValue(value);
        }
    }


    public static void removeCustomAttribute(EntityLivingBase entity, IAttribute customAttribute) {
        IAttributeInstance attributeInstance = entity.getAttributeMap().getAttributeInstance(customAttribute);
        if (attributeInstance != null) {
            attributeInstance.setBaseValue(0.0D);  // Reset the attribute value to 0
        }
    }

}

