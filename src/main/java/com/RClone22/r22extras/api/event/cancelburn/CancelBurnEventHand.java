package com.RClone22.r22extras.api.event.cancelburn;

import com.RClone22.r22extras.api.event.removebadeff.RemoveBadEffectEvent;
import com.RClone22.r22extras.api.misc.nobadpotion.AntiBadPotionMain;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;

public class CancelBurnEventHand
{

    public static void checkAndCancelBurnEvent(Entity entity) {
        CancelBurningEvent event = new CancelBurningEvent(entity);

        // Post the event to the event bus
        MinecraftForge.EVENT_BUS.post(event);

        // If the event is canceled, remove all negative effects
        if (event.isCanceled()) {
            entity.extinguish();
        }
    }

}
