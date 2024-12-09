package rclone22.modsrc22.r22extras.api.event;

import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

public class CancelBurningEvent
{

    @Cancelable
    public static class CancelBurningEventpAc extends EntityEvent
    {
        private final Entity entity;

        public CancelBurningEventpAc(Entity entity) {
            super(entity);
            this.entity = entity;
        }

        /**
         * Gets the entity that is about to burn.
         *
         * @return The entity that is about to burn.
         */
        public Entity getEntity() {
            return entity;
        }

    }

    public static void checkAndCancelBurnEvent(Entity entity) {

        CancelBurningEvent.CancelBurningEventpAc event = new CancelBurningEvent.CancelBurningEventpAc(entity);

        // Post the event to the event bus
        MinecraftForge.EVENT_BUS.post(event);

        // If the event is canceled, remove all negative effects
        if (event.isCanceled()) {
            entity.extinguish();
        }
    }
}
