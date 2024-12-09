package rclone22.modsrc22.r22extras.main.event.initevents;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.entity.living.LivingEvent;
import rclone22.modsrc22.r22extras.api.utils.NBTList;

import java.util.Set;

public class ItemNbtEvent
{

    public static void removeFlaseNbt(LivingEvent.LivingUpdateEvent event, Entity entity) {

        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();

            player.inventory.markDirty();

            checkInventory(player.inventory.mainInventory);
            checkInventory(player.inventory.armorInventory);
            checkInventory(player.inventory.offHandInventory);

        }
    }

    private static void checkInventory(NonNullList<ItemStack> inventory) {
        for (ItemStack stack : inventory) {
            if (!stack.isEmpty()) {
                NBTTagCompound nbt = stack.getTagCompound();
                if (nbt != null) {
                    // Remove any boolean tags with a value of false
                    removeFalseBooleanTags(nbt, stack);
                }
            }
        }
    }

    private static void removeFalseBooleanTags(NBTTagCompound nbt, ItemStack stack) {

        // Iterate over all boolean tags in the NBT data
        Set<String> keys = nbt.getKeySet();
        for (String key : keys) {
            if (nbt.getTagId(key) == 1) { // Tag ID 1 corresponds to a boolean
                boolean tagValue = nbt.getBoolean(key);
                if (!tagValue) {
                    nbt.removeTag(key);
                    stack.setTagCompound(nbt);
                }
            }
        }
    }


}

