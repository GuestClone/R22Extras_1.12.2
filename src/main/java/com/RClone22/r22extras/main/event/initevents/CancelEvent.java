package com.RClone22.r22extras.main.event.initevents;

import com.RClone22.r22extras.api.entityattribute.AttributeUtil;
import com.RClone22.r22extras.api.entityattribute.CustomEntityAttribute;
import com.RClone22.r22extras.api.potions.PotionUtilses;
import com.RClone22.r22extras.api.utils.CheckNbt;
import com.RClone22.r22extras.api.utils.EntityInvul;
import com.RClone22.r22extras.api.utils.NBTList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.Event;

public class CancelEvent
{
    public static final String ITEM_INDESTRUC = NBTList.ITEM_INDESTRUC;

    public static boolean isItemInvunerable;

    public static void cancelEventSupRes(Entity entity, Event event)
    {

        EntityLivingBase entityLivingBase = (EntityLivingBase) entity;

        /*
        boolean hasSupremeResistance = PotionUtilses.hasPotionEffectByRegistryName(entityLivingBase, PotionUtilses.potionSupRes);
        PotionEffect hasSupremeResistancePET = PotionUtilses.getPotionEffectPETByRegistryName(entityLivingBase, PotionUtilses.potionSupRes);
        if (hasSupremeResistancePET != null) {
            event.setCanceled(true);
        }
        */

        if (AttributeUtil.hasCustomAttributeByRegistryName(entityLivingBase, CustomEntityAttribute.SUP_RES_ATTR)) {
            // Apply the Luck effect if it's not already active
            event.setCanceled(true);
        }
    }

    public static void cancelEventItem(Entity entity, Event event)
    {
        EntityItem entityItem = (EntityItem) entity;
        ItemStack stack = entityItem.getItem(); // Get the ItemStack from the EntityItem
        Item item = stack.getItem(); // Get the Item from the ItemStack



        if (stack.hasTagCompound()) {
            NBTTagCompound tagCompound = stack.getTagCompound();
            if (tagCompound != null && tagCompound.getBoolean(ITEM_INDESTRUC)) {
                event.setCanceled(true);
            }
        }


        if (item instanceof EntityInvul.IItemIndestruc) {
            EntityInvul.IItemIndestruc itemIndestruc = (EntityInvul.IItemIndestruc) item;
            if (itemIndestruc.isItemIndestruc(stack)) {
                event.setCanceled(true);
            }
        }

        if (entityItem instanceof EntityInvul.IItemIndestruc) {
            EntityInvul.IItemIndestruc itemIndestruc = (EntityInvul.IItemIndestruc) entityItem;
            if (itemIndestruc.isItemIndestruc(stack)) {
                event.setCanceled(true);
            }
        }

    }

}
