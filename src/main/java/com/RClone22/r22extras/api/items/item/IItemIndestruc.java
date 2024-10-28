package com.RClone22.r22extras.api.items.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IItemIndestruc
{
     String ITEM_INDESTRUC = ItemNBTString.ITEM_INDESTRUC;


        default boolean isItemIndestruc(Item item, ItemStack stack, Entity entity)
        {
            if (stack.hasTagCompound()) {
                NBTTagCompound tagCompound = stack.getTagCompound();
                return tagCompound != null && (tagCompound.getBoolean(ITEM_INDESTRUC));
            }

            return true;
        }



}
