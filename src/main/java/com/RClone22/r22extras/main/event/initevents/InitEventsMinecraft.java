package com.RClone22.r22extras.main.event.initevents;

import com.RClone22.r22extras.api.event.EventSubscribe;
import com.RClone22.r22extras.api.items.item.ItemNBTString;
import com.RClone22.r22extras.main.potion.SupremeResistanceClass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@EventSubscribe.InitES()
public class InitEventsMinecraft
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




}