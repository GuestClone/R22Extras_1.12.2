package rclone22.modsrc22.r22extras.api.event;

import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;


public class CancelDeathEntity
{

    @Cancelable
    public static class EntityDeathEvent extends Event
    {
        private final Entity entity;
        private boolean canceled;

        public EntityDeathEvent(Entity entity) {
            this.entity = entity;
            this.canceled = false;
        }

        public Entity getEntity() {
            return entity;
        }

        public void setCanceled(boolean canceled) {
            this.canceled = canceled;
        }

        public boolean isCanceled() {
            return canceled;
        }
    }

    public static void checkAndCancelDeath(Entity entity) {

        CancelDeathEntity.EntityDeathEvent event = new CancelDeathEntity.EntityDeathEvent(entity);

        // Post the event to the event bus
        MinecraftForge.EVENT_BUS.post(event);

        // If the event is canceled, remove all negative effects
        if (event.isCanceled()) {
            entity.isEntityAlive();
            entity.isDead = false;
        }
    }



}
