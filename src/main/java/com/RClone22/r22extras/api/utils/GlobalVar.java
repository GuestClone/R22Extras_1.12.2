package com.RClone22.r22extras.api.utils;

import com.RClone22.r22extras.api.entityattribute.CustomEntityAttribute;
import com.RClone22.r22extras.api.potions.PotionUtilses;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.Event;

public class GlobalVar
{

    public static boolean isEntityThingInvunerable = false;


    public static void cancelEventSupRes(Entity entity, Event event)
    {

        if (isEntityThingInvunerable) {
            event.setCanceled(true);
        }
    }


}
