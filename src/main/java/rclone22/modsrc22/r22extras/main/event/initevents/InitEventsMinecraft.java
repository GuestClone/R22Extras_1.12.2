package rclone22.modsrc22.r22extras.main.event.initevents;


import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import rclone22.modsrc22.r22extras.api.entityattribute.AttributeHandler;
import rclone22.modsrc22.r22extras.api.event.CancelDeathEntity;
import rclone22.modsrc22.r22extras.api.event.RemoveBadEffectClass;
import rclone22.modsrc22.r22extras.api.utils.NBTList;
import rclone22.modsrc22.r22extras.api.utils.PlayerUUIDList;
import rclone22.modsrc22.r22extras.api.utils.entityinvuls.IimpUtils;
import rclone22.modsrc22.r22extras.main.Constantr22Extras;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rclone22.modsrc22.r22extras.main.registry.InitRegistryHandler;


public class InitEventsMinecraft
{

    public static final String ITEM_INDESTRUC = NBTList.ITEM_INDESTRUC;

    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void entityTick(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.getEntity();

        CancelEvent.livingUpdateEvent(event);

        AttributeHandler.onEntityLivingUpdate(event);

        if (entity instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

            RemoveBadEffectClass.checkAndRemoveBadEffects(livingEntityBase);
        }

        if (entity instanceof EntityPlayer)
        {
            EntityPlayer entityPlayer = (EntityPlayer) entity;

            ItemNbtEvent.removeFlaseNbt(event, entityPlayer);

            CancelEvent.eventItemDurability(event);
        }

        CancelDeathEntity.checkAndCancelDeath(entity);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void livingAttack(LivingAttackEvent event)
    {
        Entity entity = event.getEntity();

        CancelEvent.cancelEventSupRes(entity, event);

    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void livingDamage(LivingDamageEvent event)
    {
        Entity entity = event.getEntity();

        CancelEvent.cancelEventSupRes(entity, event);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void livingDeath(LivingDeathEvent event)
    {
        Entity entity = event.getEntity();


        CancelEvent.cancelEventSupRes(entity, event);


    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void livingFall(LivingFallEvent event)
    {
        Entity entity = event.getEntity();

        CancelEvent.cancelEventSupRes(entity, event);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void livingKnockback(LivingKnockBackEvent event)
    {
        Entity entity = event.getEntity();

        CancelEvent.cancelEventSupRes(entity, event);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void livingHurt(LivingHurtEvent event)
    {
        Entity entity = event.getEntity();

        CancelEvent.cancelEventSupRes(entity, event);
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        World world = event.getWorld();
    }


}
