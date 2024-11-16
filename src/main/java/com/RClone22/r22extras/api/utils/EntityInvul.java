package com.RClone22.r22extras.api.utils;

import com.RClone22.r22extras.api.entityattribute.CustomEntityAttribute;
import com.RClone22.r22extras.main.Constantr22Extras;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constantr22Extras.MODID)
public class EntityInvul
{

    private static final String ITEM_INDESTRUC = NBTList.ITEM_INDESTRUC;

    public static boolean isIndestrucitble;

    public static boolean isEntInvunerableT;

    public interface IItemIndestruc
    {
        default boolean isItemIndestruc(Item item, ItemStack stack)
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


    }

    public interface IEntityInvul
    {
        default boolean setEntityInvulnerable(Entity entity)
        {
            EntityInvul.isEntInvunerableT = false;
            return false;
        }


    }

}
