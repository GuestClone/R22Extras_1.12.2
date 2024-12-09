package rclone22.modsrc22.r22extras.api.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public interface PlayerInventoryHelper
{


    static List<ItemStack> getAllPlayerInventory(EntityLivingBase player) {
        List<ItemStack> allPlayerInventory = new ArrayList<>();

        if (player instanceof EntityPlayer)
        {
            // For players
            EntityPlayer player1 = (EntityPlayer) player;
            allPlayerInventory.addAll(player1.inventory.mainInventory);
            allPlayerInventory.addAll(player1.inventory.offHandInventory);
            allPlayerInventory.addAll(player1.inventory.armorInventory);
        }

        // For Entity Livings
        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR) {
                allPlayerInventory.add(player.getItemStackFromSlot(slot));
            }
            if (slot.getSlotType() == EntityEquipmentSlot.Type.HAND) {
                allPlayerInventory.add(player.getItemStackFromSlot(slot));
            }
        }

        return allPlayerInventory;
    }




}
