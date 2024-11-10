package com.RClone22.r22extras.api.utils;

import com.RClone22.r22extras.api.potions.PotionUtilses;
import com.RClone22.r22extras.main.Constantr22Extras;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constantr22Extras.MODID)
public class EntityInvul
{

    private static final String ITEM_INDESTRUC = NBTList.ITEM_INDESTRUC;

    public interface IItemIndestruc
    {
        default boolean isItemIndestruc(Item item, ItemStack stack, Entity entity)
        {
            if (stack.hasTagCompound()) {
                NBTTagCompound tagCompound = stack.getTagCompound();
                return tagCompound != null && (tagCompound.getBoolean(ITEM_INDESTRUC));
            }

            return true;
        }

    }

    public interface IEntityInvul
    {

        default boolean setEntityInvulnerable()
        {


            Entity entity = (Entity) this;


            if (entity instanceof EntityLivingBase)
            {
                EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
                PotionEffect effect = PotionUtilses.getPotionEffectPETByRegistryName(entityLivingBase, PotionUtilses.potionSupRes);


                if (effect != null) {
                    setEntityInvulnerable(true, entityLivingBase);
                    return true;
                }
            }

            if (entity instanceof EntityItem)
            {
                EntityItem entityItem = (EntityItem) entity;

                ItemStack itemStack = entityItem.getItem();
                Item item = itemStack.getItem();

                if (itemStack.hasTagCompound()) {
                    NBTTagCompound tagCompound = itemStack.getTagCompound();
                    setEntityInvulnerable(true, entityItem);
                    return tagCompound != null && (tagCompound.getBoolean(ITEM_INDESTRUC));
                }

                return true;
            }

            return false;
        }

        static void setEntityInvulnerable(boolean entityInvulnerable, Entity entity)
        {
            if (entity instanceof com.RClone22.r22extras.api.utils.EntityInvul.IEntityInvul) {
                com.RClone22.r22extras.api.utils.EntityInvul.IEntityInvul invulEntity = (com.RClone22.r22extras.api.utils.EntityInvul.IEntityInvul) entity;
                boolean result = invulEntity.setEntityInvulnerable();

                if (result) {
                    entity.setEntityInvulnerable(true);
                } else  {
                    entity.setEntityInvulnerable(entityInvulnerable);
                }
                entity.setEntityInvulnerable(entityInvulnerable);
            }

        }


    }

}
