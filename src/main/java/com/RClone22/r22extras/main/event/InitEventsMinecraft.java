package com.RClone22.r22extras.main.event;

import com.RClone22.r22extras.api.items.item.IItemIndestruc;
import com.RClone22.r22extras.api.items.item.ItemNBTString;
import com.RClone22.r22extras.api.misc.nobadpotion.AntiBadPotionMain;
import com.RClone22.r22extras.main.Constant;
import com.RClone22.r22extras.main.potion.SupremeResistanceClass;
import com.RClone22.r22extras.main.proxy.ICommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Constant.MODID)
public class InitEventsMinecraft implements ICommonProxy
{

    public static final String ITEM_INDESTRUC = ItemNBTString.ITEM_INDESTRUC;
    // Living or whatever In-Game Events:
    @SubscribeEvent
    public static void entityTick(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) entity;
            SupremeResistanceClass.SUP_RES.performEffect(livingEntityBase, 0);




        }
    }



    @SubscribeEvent
    public void livingAttack(LivingAttackEvent event)
    {
        Entity entity = event.getEntity();
        EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

        SupremeResistanceClass.cancelEventSupRes(livingEntityBase, event);

    }

    @SubscribeEvent
    public void livingDamage(LivingDamageEvent event)
    {
        Entity entity = event.getEntity();
        EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

        SupremeResistanceClass.cancelEventSupRes(livingEntityBase, event);

    }

    @SubscribeEvent
    public void livingDeath(LivingDeathEvent event)
    {
        Entity entity = event.getEntity();
        EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

        SupremeResistanceClass.cancelEventSupRes(livingEntityBase, event);

    }



    @SubscribeEvent
    public void livingFall(LivingFallEvent event)
    {
        Entity entity = event.getEntity();
        EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

        SupremeResistanceClass.cancelEventSupRes(livingEntityBase, event);

    }

    @SubscribeEvent
    public void livingKnockback(LivingKnockBackEvent event)
    {
        Entity entity = event.getEntity();
        EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

        SupremeResistanceClass.cancelEventSupRes(livingEntityBase, event);


    }

    @SubscribeEvent
    public void livingHurt(LivingHurtEvent event)
    {
        Entity entity = event.getEntity();
        EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

        SupremeResistanceClass.cancelEventSupRes(livingEntityBase, event);

    }

    @SubscribeEvent
    public void itemExpireEvent(ItemExpireEvent event)
    {
        Entity entity = event.getEntity();
        if (entity instanceof EntityItem) {
            EntityItem entityItem = (EntityItem) entity;
            ItemStack stack = entityItem.getItem(); // Get the ItemStack from the EntityItem
            Item item = stack.getItem(); // Get the Item from the ItemStack

            // Check if the item implements IItemIndestruc
            if (item instanceof IItemIndestruc) {
                IItemIndestruc itemIndestruc = (IItemIndestruc) item; // Cast the item to the interface


                if (itemIndestruc.isItemIndestruc(item, stack)) {

                    event.setCanceled(true);
                    event.getEntity().setEntityInvulnerable(true);
                }

            }

        }
    }

    @SubscribeEvent
    public void onItemToss(ItemTossEvent event) {
        ItemStack tossedItem = event.getEntityItem().getItem();
        Item item = tossedItem.getItem();

        boolean shouldMakeInvulnerable = false;

        // Check if the ItemStack has the indestructible tag
        if (tossedItem.hasTagCompound()) {
            NBTTagCompound tagCompound = tossedItem.getTagCompound();
            if (tagCompound != null && tagCompound.getBoolean(ITEM_INDESTRUC)) {
                shouldMakeInvulnerable = true;
            }
        }

        // Check if the item implements IItemIndestruc and is indestructible
        if (item instanceof IItemIndestruc) {
            IItemIndestruc itemIndestruc = (IItemIndestruc) item;
            if (itemIndestruc.isItemIndestruc(item, tossedItem)) {
                shouldMakeInvulnerable = true;
            }
        }

        // Set the item and entity invulnerable if either condition was met
        if (shouldMakeInvulnerable) {
            event.getEntityItem().setEntityInvulnerable(true);
            event.getEntity().setEntityInvulnerable(true);
        }
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);

    }

}
