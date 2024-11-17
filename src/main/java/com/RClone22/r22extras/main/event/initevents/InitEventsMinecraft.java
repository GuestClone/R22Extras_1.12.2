package com.RClone22.r22extras.main.event.initevents;


import com.RClone22.r22extras.api.entityattribute.AttributeHandler;
import com.RClone22.r22extras.api.event.RemoveBadEffectClass;
import com.RClone22.r22extras.api.misc.nobadpotion.AntiBadPotionMain;
import com.RClone22.r22extras.api.utils.GlobalVar;
import com.RClone22.r22extras.api.utils.NBTList;
import com.RClone22.r22extras.main.Constantr22Extras;
import com.RClone22.r22extras.main.potion.SupremeResistanceClass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = Constantr22Extras.MODID)
public class InitEventsMinecraft
{

    public static final String ITEM_INDESTRUC = NBTList.ITEM_INDESTRUC;


    @SubscribeEvent
    public static void entityTick(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.getEntity();



        if (entity instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

            RemoveBadEffectClass.checkAndRemoveBadEffects(livingEntityBase);

        }
    }

    @SubscribeEvent
    public void livingAttack(LivingAttackEvent event)
    {
        Entity entity = event.getEntity();
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            CancelEvent.cancelEventSupRes(entityLivingBase, event);
        }
        if (entity instanceof EntityItem)
        {
            EntityItem entityItem = (EntityItem) entity;
            CancelEvent.cancelEventItem(entityItem, event);
        }


    }

    @SubscribeEvent
    public void livingDamage(LivingDamageEvent event)
    {
        Entity entity = event.getEntity();
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            CancelEvent.cancelEventSupRes(entityLivingBase, event);
        }
        if (entity instanceof EntityItem)
        {
            EntityItem entityItem = (EntityItem) entity;
            CancelEvent.cancelEventItem(entityItem, event);
        }

    }

    @SubscribeEvent
    public void livingDeath(LivingDeathEvent event)
    {
        Entity entity = event.getEntity();


        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            CancelEvent.cancelEventSupRes(entityLivingBase, event);
        }
        if (entity instanceof EntityItem)
        {
            EntityItem entityItem = (EntityItem) entity;
            CancelEvent.cancelEventItem(entityItem, event);
        }
    }



    @SubscribeEvent
    public void livingFall(LivingFallEvent event)
    {
        Entity entity = event.getEntity();
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            CancelEvent.cancelEventSupRes(entityLivingBase, event);
        }
        if (entity instanceof EntityItem)
        {
            EntityItem entityItem = (EntityItem) entity;
            CancelEvent.cancelEventItem(entityItem, event);
        }

    }

    @SubscribeEvent
    public void livingKnockback(LivingKnockBackEvent event)
    {
        Entity entity = event.getEntity();
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            CancelEvent.cancelEventSupRes(entityLivingBase, event);
        }
        if (entity instanceof EntityItem)
        {
            EntityItem entityItem = (EntityItem) entity;
            CancelEvent.cancelEventItem(entityItem, event);
        }

    }

    @SubscribeEvent
    public void livingHurt(LivingHurtEvent event)
    {
        Entity entity = event.getEntity();
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            CancelEvent.cancelEventSupRes(entityLivingBase, event);
        }
        if (entity instanceof EntityItem)
        {
            EntityItem entityItem = (EntityItem) entity;
            CancelEvent.cancelEventItem(entityItem, event);
        }

    }

    // Living or whatever In-Game Events:

}
