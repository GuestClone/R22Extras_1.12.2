package rclone22.modsrc22.r22extras.main.event.initevents;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import rclone22.modsrc22.r22extras.api.utils.NBTList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.r22extras.api.utils.entityinvuls.IimpUtils;
import rclone22.modsrc22.r22extras.main.Constantr22Extras;
import rclone22.modsrc22.r22extras.main.registry.InitRegistryHandler;

import java.util.List;


public class InitItemEvents implements InitRegistryHandler.IInitEvents
{
    public static final String ITEM_INDESTRUC = NBTList.ITEM_INDESTRUC;

    public static final String ITEM_OMNIHARV = NBTList.ITEM_OMNIHARV;


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void itemExpireEvent(ItemExpireEvent event)
    {

        Entity entity = event.getEntity();
        if (entity instanceof EntityItem) {
            EntityItem entityItem = (EntityItem) entity;
            ItemStack stack = entityItem.getItem(); // Get the ItemStack from the EntityItem
            Item item = stack.getItem(); // Get the Item from the ItemStack

            CancelEvent.cancelEventItem(entityItem, event);
            CancelEvent.expireItem(event);
        }
    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onItemToss(ItemTossEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof EntityItem)
        {
            EntityItem entityItem = event.getEntityItem();

            if (IimpUtils.isEntityItemInvul(entityItem))
            {
                entityItem.setEntityInvulnerable(true);
                entityItem.setFire(0);entityItem.extinguish();
                entityItem.isDead = false;
                entityItem.isEntityAlive();
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onExplosionDetonate(ExplosionEvent.Detonate event) {
        List<Entity> affectedEntities = event.getAffectedEntities();


        affectedEntities.removeIf(entity ->
                (entity instanceof EntityItem && IimpUtils.isEntityItemInvul((EntityItem) entity)) ||
                        (entity instanceof Entity && IimpUtils.isEntityHasInvulnerable(entity))

        );
    }




}
