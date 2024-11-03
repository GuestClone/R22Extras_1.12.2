package com.RClone22.r22extras.api.event.removebadeff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class RemoveBadEffectEvent extends EntityEvent
{
    private final EntityLivingBase entity;

    public RemoveBadEffectEvent(EntityLivingBase entity) {
        super(entity);
        this.entity = entity;
    }

    public EntityLivingBase getEntity() {
        return entity;
    }


    // TODO, Added this event so instead of calling the fricking AntiBadPotionMain.abpMainStatic
    // TODO, So if event is cancelled it will remove any bad effects
    // TODO, just be sure it cancelled with methods or arguments to prevent bugs
    /* TODO Example of event:
        @SubscribeEvent
        public void onBadEffectEvent(RemoveBadEffectEvent event) {
        EntityLivingBase entity = event.getEntity();
        // Example condition to cancel the event: if entity health is below 10
        SupremeResistanceClass.cancelEventSupRes(entity, event);  } */


}
