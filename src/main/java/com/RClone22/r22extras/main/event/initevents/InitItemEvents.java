package com.RClone22.r22extras.main.event.initevents;


import com.RClone22.r22extras.api.utils.EntityInvul;
import com.RClone22.r22extras.api.utils.NBTList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class InitItemEvents
{
    public static final String ITEM_INDESTRUC = NBTList.ITEM_INDESTRUC;



    public static final String ITEM_OMNIHARV = NBTList.ITEM_OMNIHARV;

    public static boolean isItemInvunerable;

    @SubscribeEvent
    public void itemExpireEvent(ItemExpireEvent event)
    {


        Entity entity = event.getEntity();
        if (entity instanceof EntityItem) {
            EntityItem entityItem = (EntityItem) entity;
            ItemStack stack = entityItem.getItem(); // Get the ItemStack from the EntityItem
            Item item = stack.getItem(); // Get the Item from the ItemStack

            CancelEvent.cancelEventItem(entityItem, event);

        }
    }


    @SubscribeEvent
    public void onItemToss(ItemTossEvent event) {
        Entity entity = event.getEntity();
        ItemStack tossedItem = event.getEntityItem().getItem();
        Item item = tossedItem.getItem();

        // Check if the ItemStack has the indestructible tag
        if (tossedItem.hasTagCompound()) {
            NBTTagCompound tagCompound = tossedItem.getTagCompound();
            if (tagCompound != null && tagCompound.getBoolean(ITEM_INDESTRUC)) {
                isItemInvunerable = true;
            }
        }

        // Check if the item implements IItemIndestruc and is indestructible
        if (item instanceof EntityInvul.IItemIndestruc) {
            EntityInvul.IItemIndestruc itemIndestruc = (EntityInvul.IItemIndestruc) item;
            if (itemIndestruc.isItemIndestruc(tossedItem)) {
                isItemInvunerable = true;
            }
        }

        // Set the item and entity invulnerable if either condition was met
        if (isItemInvunerable) {
            event.getEntityItem().setEntityInvulnerable(true);
            event.getEntity().setEntityInvulnerable(true);
        }
    }

}
