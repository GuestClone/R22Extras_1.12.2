package com.RClone22.r22extras.api.items.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IItemIndestruc
{



        default boolean isItemIndestruc(Item item, ItemStack stack)
        {
            return false;
        }



}
