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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
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

            boolean inventoryChanged = false; // Track if the inventory was changed

            // Check all items in the player's inventory
            if (livingEntityBase instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) livingEntityBase;

                // Check main inventory slots
                for (ItemStack stack : player.inventory.mainInventory) {
                    inventoryChanged |= checkAndSetIndestructibleTag(stack);
                }

                // Check armor slots
                for (ItemStack stack : player.inventory.armorInventory) {
                    inventoryChanged |= checkAndSetIndestructibleTag(stack);
                }

                // Check off-hand slot
                for (ItemStack stack : player.inventory.offHandInventory) {
                    inventoryChanged |= checkAndSetIndestructibleTag(stack);
                }

                // If any modifications were made, mark the inventory as dirty
                if (inventoryChanged) {
                    player.inventory.markDirty();
                }
            }
        }
    }


    private static boolean checkAndSetIndestructibleTag(ItemStack stack) {
        if (stack.isEmpty()) {
            return false; // Skip empty slots
        }

        Item item = stack.getItem();


        if (item instanceof IItemIndestruc) {
            IItemIndestruc itemIndestruc = (IItemIndestruc) item;


            if (itemIndestruc.isItemIndestruc(item, stack)) {

                NBTTagCompound tagCompound = stack.getTagCompound();
                if (tagCompound != null) { // Ensure tagCompound is not null
                    tagCompound.setBoolean("IndestructibleTag", true);
                }
                return true;
            }
        }

        return false; // No changes made
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
    public void livingFall(LivingKnockBackEvent event)
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

                // Call the isItemIndestruc method
                if (itemIndestruc.isItemIndestruc(item, stack)) {
                    // If this returns true, cancel the event
                    event.setCanceled(true);
                }
                // If it returns false, do nothing, allowing the event to continue
            }
            // If the item does not implement IItemIndestruc, it will also proceed normally
        }



    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);

    }

}
