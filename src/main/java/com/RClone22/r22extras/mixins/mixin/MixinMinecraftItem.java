package com.RClone22.r22extras.mixins.mixin;

import com.RClone22.r22extras.api.utils.EntityInvul;
import com.RClone22.r22extras.api.utils.NBTList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.*;

import java.lang.reflect.Field;

@Mixin(value = Item.class, remap = false)
@Implements(@Interface(iface = EntityInvul.IItemIndestruc.class, prefix = "isItemIndestruc$"))
public abstract class MixinMinecraftItem implements EntityInvul.IItemIndestruc{


    @Unique
    private static final String ITEM_INDESTRUC = NBTList.ITEM_INDESTRUC;



    @Override
    public boolean isItemIndestruc(Item item, ItemStack stack)
    {
        if (stack.hasTagCompound()) {
            NBTTagCompound tagCompound = stack.getTagCompound();
            if (tagCompound != null && (tagCompound.getBoolean(ITEM_INDESTRUC)))
            {
                EntityInvul.isIndestrucitble = true;
                return true;
            }
        }

        EntityInvul.isIndestrucitble = false;
        return false;
    }


    /**
     * @author M
     * @reason C
     */
    @Overwrite
    public boolean isDamaged(ItemStack stack) {

        Item item = stack.getItem();

        if (stack == null) {
            return false;
        }

        if (EntityInvul.isIndestrucitble)
        {
            return false;
        }

        if (item instanceof EntityInvul.IItemIndestruc) {
            EntityInvul.IItemIndestruc itemIndestruc = (EntityInvul.IItemIndestruc) item;
            if (itemIndestruc.isItemIndestruc(item, stack)) {
                return false;
            }
        }





       /*
       // Check for NBT tags
        if (stack.hasTagCompound()) {

            NBTTagCompound tagCompound = stack.getTagCompound();
            if (tagCompound != null && tagCompound.hasKey(ITEM_INDESTRUC)) {
                if (tagCompound.getBoolean(ITEM_INDESTRUC)) {
                    return false; // Indestructible item
                }
            }
        }
        */

        // Return true if the item has taken damage
        return r22Extras_1_12_2$getItemDamage(stack, item) > 0; // Use custom method to get damage
    }

    /**
     * @author M
     * @reason C
     */
    @Overwrite
    public void setDamage(ItemStack stack, int damage) {

        Item item = stack.getItem();

        if (!r22Extras_1_12_2$isItemIndestructible(stack, item)) {
            // Set the damage using reflection
            try {
                Field itemDamageField = ItemStack.class.getDeclaredField("itemDamage");
                itemDamageField.setAccessible(true); // Make the field accessible
                itemDamageField.setInt(stack, damage); // Set the damage value

                // Ensure damage does not go below zero
                if (r22Extras_1_12_2$getItemDamage(stack, item) < 0) {
                    itemDamageField.setInt(stack, 0);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace(); // Handle the exception as necessary
            }
        }
    }

    @Unique
    private boolean r22Extras_1_12_2$isItemIndestructible(ItemStack stack, Item item) {

        /*

        if (stack != null && !stack.isEmpty()) {
            NBTTagCompound tagCompound = stack.getTagCompound();
            if (tagCompound != null && tagCompound.hasKey(ITEM_INDESTRUC)) {
                return tagCompound.getBoolean(ITEM_INDESTRUC);
            }
           }
            */

        if (EntityInvul.isIndestrucitble)
        {
            return true;
        }

        if (item instanceof EntityInvul.IItemIndestruc) {
            EntityInvul.IItemIndestruc itemIndestruc = (EntityInvul.IItemIndestruc) item;
            return itemIndestruc.isItemIndestruc(item, stack);
        }


        return false; // Not indestructible if conditions are not met
    }

    // Helper method to get item damage using reflection
    @Unique
    private int r22Extras_1_12_2$getItemDamage(ItemStack stack, Item item) {
        try {
            Field itemDamageField = ItemStack.class.getDeclaredField("itemDamage");
            itemDamageField.setAccessible(true);
            return itemDamageField.getInt(stack); // Get the current damage value
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace(); // Handle the exception as necessary
            return 0; // Default to 0 if an error occurs
        }
    }

}