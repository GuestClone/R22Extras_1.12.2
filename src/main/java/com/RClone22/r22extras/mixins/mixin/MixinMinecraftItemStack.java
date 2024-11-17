package com.RClone22.r22extras.mixins.mixin;

import com.RClone22.r22extras.api.utils.EntityInvul;
import com.RClone22.r22extras.api.utils.NBTList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.spongepowered.asm.mixin.*;

@Mixin(value = ItemStack.class, remap = false)

public abstract class MixinMinecraftItemStack{

    @Unique
    private static final String ITEM_INDESTRUC = NBTList.ITEM_INDESTRUC;




    /**
     * @author M
     * @reason C
     */
    @Overwrite
    public boolean isItemStackDamageable() {
        // Use 'this' directly without casting
        ItemStack thisStack = (ItemStack) (Object) this; // Safely cast to ItemStack

        // Check if the ItemStack is empty
        if (thisStack.isEmpty()) {
            return false;
        }


        Item item = thisStack.getItem();


        if (item instanceof EntityInvul.IItemIndestruc) {
            EntityInvul.IItemIndestruc itemIndestruc = (EntityInvul.IItemIndestruc) item;
            if (itemIndestruc.isItemIndestruc(thisStack)) {
                return false;
            }
        }

        // Check maximum damage
        if (item.getMaxDamage(thisStack) <= 0) {
            return false;
        }

        // Check the NBT tag for "Unbreakable" and "Indestructible"
        NBTTagCompound tagCompound = thisStack.getTagCompound();
        return tagCompound == null ||
                !(tagCompound.getBoolean("Unbreakable") && tagCompound.hasKey("Unbreakable")) ||
                !(tagCompound.getBoolean(ITEM_INDESTRUC) && tagCompound.hasKey(ITEM_INDESTRUC));
    }
}
