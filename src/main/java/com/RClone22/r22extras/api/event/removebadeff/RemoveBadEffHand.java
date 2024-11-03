package com.RClone22.r22extras.api.event.removebadeff;

import com.RClone22.r22extras.api.misc.nobadpotion.AntiBadPotionMain;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;

public class RemoveBadEffHand
{

    public static void checkAndRemoveBadEffects(EntityLivingBase entity) {
        RemoveBadEffectEvent event = new RemoveBadEffectEvent(entity);

        // Post the event to the event bus
        MinecraftForge.EVENT_BUS.post(event);

        // If the event is canceled, remove all negative effects
        if (event.isCanceled()) {
            AntiBadPotionMain.abpMainStatic(entity);
        }
    }


}
