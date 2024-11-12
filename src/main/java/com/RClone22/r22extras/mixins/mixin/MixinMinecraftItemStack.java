package com.RClone22.r22extras.mixins.mixin;

import com.RClone22.r22extras.api.utils.EntityInvul;
import com.RClone22.r22extras.api.utils.NBTList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.spongepowered.asm.mixin.*;

@Mixin(value = ItemStack.class, remap = false)
@Implements(@Interface(iface = EntityInvul.IItemIndestruc.class, prefix = "isItemIndestruc$"))
public abstract class MixinMinecraftItemStack implements EntityInvul.IItemIndestruc{

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
    public boolean isItemStackDamageable() {
        // Use 'this' directly without casting
        ItemStack thisStack = (ItemStack) (Object) this; // Safely cast to ItemStack

        // Check if the ItemStack is empty
        if (thisStack.isEmpty()) {
            return false;
        }


        Item item = thisStack.getItem();

        if (EntityInvul.isIndestrucitble)
        {
            return false;
        }

        if (item instanceof EntityInvul.IItemIndestruc) {
            EntityInvul.IItemIndestruc itemIndestruc = (EntityInvul.IItemIndestruc) item;
            if (itemIndestruc.isItemIndestruc(item, thisStack)) {
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
                !(tagCompound.getBoolean("Unbreakable") && tagCompound.hasKey("Unbreakable")) /*||
                !(tagCompound.getBoolean(ITEM_INDESTRUC) && tagCompound.hasKey(ITEM_INDESTRUC))*/;
    }
}
