package rclone22.modsrc22.r22extras.api.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.NotNull;

public interface CheckNbt
{



    static boolean isNbtInvunerable(@NotNull ItemStack stack) {
        NBTTagCompound tagCompound = stack.getTagCompound();
        if (tagCompound == null) {
            return false;
        }

        // Define an array of custom NBT keys to check
        String[] nbtKeys = {NBTList.ITEM_INDESTRUC, "CustomNBTKey1", "CustomNBTKey2", NBTList.ITEM_ModifInvul, NBTList.Unbreakable}; // Add more keys as needed

        // Iterate over the NBT keys and check if any are present with a value of true
        for (String key : nbtKeys) {
            if (tagCompound.hasKey(key) && tagCompound.getBoolean(key)) {
                return true;
            }
        }

        return false;
    }

}
