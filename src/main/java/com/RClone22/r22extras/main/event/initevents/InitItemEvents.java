package com.RClone22.r22extras.main.event.initevents;

import com.RClone22.r22extras.api.event.EventBus;
import com.RClone22.r22extras.api.items.item.IItemIndestruc;
import com.RClone22.r22extras.api.items.item.ItemNBTString;
import com.RClone22.r22extras.main.ConstantExt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@EventBus.InitBus()
public class InitItemEvents
{
    public static final String ITEM_INDESTRUC = ItemNBTString.ITEM_INDESTRUC;

    @SubscribeEvent
    public void itemExpireEvent(ItemExpireEvent event)
    {
        Entity entity = event.getEntity();
        if (entity instanceof EntityItem) {
            EntityItem entityItem = (EntityItem) entity;
            ItemStack stack = entityItem.getItem(); // Get the ItemStack from the EntityItem
            Item item = stack.getItem(); // Get the Item from the ItemStack

            // Check if the item implements IItemIndestruc
            if (item instanceof IItemIndestruc) {
                IItemIndestruc itemIndestruc = (IItemIndestruc) item; // Cast the item to the interface


                if (itemIndestruc.isItemIndestruc(item, stack, entity)) {

                    event.setCanceled(true);
                    event.getEntity().setEntityInvulnerable(true);
                }

            }

        }
    }

    @SubscribeEvent
    public void onItemToss(ItemTossEvent event) {
        Entity entity = event.getEntity();
        ItemStack tossedItem = event.getEntityItem().getItem();
        Item item = tossedItem.getItem();

        boolean shouldMakeInvulnerable = false;

        // Check if the ItemStack has the indestructible tag
        if (tossedItem.hasTagCompound()) {
            NBTTagCompound tagCompound = tossedItem.getTagCompound();
            if (tagCompound != null && tagCompound.getBoolean(ITEM_INDESTRUC)) {
                shouldMakeInvulnerable = true;
            }
        }

        // Check if the item implements IItemIndestruc and is indestructible
        if (item instanceof IItemIndestruc) {
            IItemIndestruc itemIndestruc = (IItemIndestruc) item;
            if (itemIndestruc.isItemIndestruc(item, tossedItem, entity)) {
                shouldMakeInvulnerable = true;
            }
        }

        // Set the item and entity invulnerable if either condition was met
        if (shouldMakeInvulnerable) {
            event.getEntityItem().setEntityInvulnerable(true);
            event.getEntity().setEntityInvulnerable(true);
        }
    }

}
