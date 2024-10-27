package com.RClone22.r22extras.main.event;

import com.RClone22.r22extras.api.items.item.IItemIndestruc;
import com.RClone22.r22extras.api.items.item.ItemNBTString;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingEvent;

public class ItemEvents
{
    public static final String ITEM_INDESTRUC = ItemNBTString.ITEM_INDESTRUC;

    public static boolean checkAndSetIndestructibleTag(ItemStack stack) {
        if (stack.isEmpty()) {
            return false; // Skip empty slots
        }

        Item item = stack.getItem();



        if (item instanceof IItemIndestruc) {
            IItemIndestruc itemIndestruc = (IItemIndestruc) item;


            if (itemIndestruc.isItemIndestruc(item, stack)) {

                NBTTagCompound tagCompound = stack.getTagCompound();
                if (tagCompound != null) { // Ensure tagCompound is not null
                    tagCompound.setBoolean(ITEM_INDESTRUC, true);
                }
                return true;
            }
        }

        return false; // No changes made
    }



}
