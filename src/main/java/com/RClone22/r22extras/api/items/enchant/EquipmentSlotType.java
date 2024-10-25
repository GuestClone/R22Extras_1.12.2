package com.RClone22.r22extras.api.items.enchant;

import net.minecraft.inventory.EntityEquipmentSlot;

public class EquipmentSlotType
{

    public static final EntityEquipmentSlot[]

            ARMOR = new EntityEquipmentSlot[]
            {
            EntityEquipmentSlot.HEAD,
            EntityEquipmentSlot.CHEST,
            EntityEquipmentSlot.LEGS,
            EntityEquipmentSlot.FEET
    },

    // Recommend for tools
    BOTH_HANDS = new EntityEquipmentSlot[]
                    {
                    EntityEquipmentSlot.MAINHAND,
                    EntityEquipmentSlot.OFFHAND
            },

            // armor and hands recommended for if using all slots like armor and hand slots.

            ARMOR_AND_HANDS	= new EntityEquipmentSlot[]
                    {
                    EntityEquipmentSlot.MAINHAND,
                    EntityEquipmentSlot.OFFHAND,
                    EntityEquipmentSlot.HEAD,
                    EntityEquipmentSlot.CHEST,
                    EntityEquipmentSlot.LEGS,
                    EntityEquipmentSlot.FEET
            };

}
