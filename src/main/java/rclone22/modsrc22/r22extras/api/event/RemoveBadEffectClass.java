package rclone22.modsrc22.r22extras.api.event;

import rclone22.modsrc22.r22extras.api.misc.nobadpotion.AntiBadPotionMain;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

public class RemoveBadEffectClass
{

    @Cancelable
    public static class RemoveBadEffectEventpAc extends EntityEvent
    {
        private final EntityLivingBase entity;

        public RemoveBadEffectEventpAc(EntityLivingBase entity) {
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

    public static void checkAndRemoveBadEffects(EntityLivingBase entity) {
        RemoveBadEffectClass.RemoveBadEffectEventpAc event = new RemoveBadEffectClass.RemoveBadEffectEventpAc(entity);
        // Post the event to the event bus
        MinecraftForge.EVENT_BUS.post(event);

        // If the event is canceled, remove all negative effects
        if (event.isCanceled()) {
            AntiBadPotionMain.abpMainStatic(entity);
        }
    }


}
