package rclone22.modsrc22.r22extras.main.event.initevents;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import rclone22.modsrc22.r22extras.api.misc.nobadpotion.AntiBadPotionMain;
import rclone22.modsrc22.r22extras.api.utils.NBTList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;
import rclone22.modsrc22.r22extras.api.utils.PlayerInventoryHelper;
import rclone22.modsrc22.r22extras.api.utils.entityinvuls.IimpUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CancelEvent
{
    public static final String ITEM_INDESTRUC = NBTList.ITEM_INDESTRUC;

    public static boolean isItemInvunerable;

    public static void livingUpdateEvent(LivingEvent.LivingUpdateEvent event)
    {
        Entity entity = event.getEntity();

            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;

                if (IimpUtils.isEntityHasInvulnerable(player)) {
                    player.getFoodStats().addStats(20, 20.0f); // Add 1 hunger and 0.5 saturation
                    player.getFoodStats().setFoodLevel(20);
                    player.getFoodStats().setFoodSaturationLevel(20.0f);
                    player.getFoodStats().addExhaustion(0.0F);
                }
            }

            if (entity instanceof EntityLivingBase)
            {
                EntityLivingBase player = (EntityLivingBase) entity;

                if (IimpUtils.isEntityHasInvulnerable(player)) {


                    player.heal(255);
                    AntiBadPotionMain.abpMainStatic(player);
                    player.setAir(300);
                    player.isEntityAlive();
                    player.setEntityInvulnerable(true);
                    player.extinguish();
                    player.setFire(0);
                    player.isDead = false;
                }
            }



    }

    public static void cancelEventSupRes(Entity entity, Event event)
    {

        if (entity instanceof EntityItem)
        {
            EntityItem entityItem = (EntityItem) entity;
            if (IimpUtils.isEntityItemInvul(entityItem)) {
                CancelEvent.cancelEventItem(entityItem, event);
                event.setCanceled(true);

            }
        }

        if (entity instanceof Entity)
        {
            if (IimpUtils.isEntityHasInvulnerable(entity)) {
                event.setCanceled(true);
            }
        }


        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            if (IimpUtils.isEntityHasInvulnerable(entityLivingBase)) {
                event.setCanceled(true);
            }
        }
    }

    public static void cancelEventItem(Entity entity, Event event)
    {
        EntityItem entityItem = (EntityItem) entity;
        ItemStack stack = entityItem.getItem(); // Get the ItemStack from the EntityItem

        if (entity instanceof EntityItem)
        {
            EntityItem entityItemI = (EntityItem) entity;
            if (IimpUtils.isEntityItemInvul(entityItemI))
            {
                event.setCanceled(true);
            }
        }
    }

    public static void expireItem(ItemExpireEvent event)
    {
        Entity entity = event.getEntity();
        if (entity instanceof EntityItem) {
            EntityItem entityItem = event.getEntityItem();
            ItemStack stack = entityItem.getItem();
            if (IimpUtils.isEntityItemInvul(entityItem)) {
                event.setExtraLife(36000);
                entityItem.setFire(0);
                entityItem.extinguish();
                entityItem.isDead = false;
                entityItem.isEntityAlive();
            }
        }
    }


    public static void eventItemDurability(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof EntityLivingBase) {
            EntityLivingBase player = (EntityLivingBase) entity;

            List<ItemStack> allInventory = PlayerInventoryHelper.getAllPlayerInventory(player);

            for (ItemStack stack : allInventory) {
                if (!stack.isEmpty()) {
                    if (IimpUtils.isItemHasInvulnerable(stack)) {
                        stack.setItemDamage(0);
                        stack.damageItem(0, player);
                    }
                }
            }

        }

    }









}
