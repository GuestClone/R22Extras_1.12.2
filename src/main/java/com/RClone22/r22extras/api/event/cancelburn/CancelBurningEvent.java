package com.RClone22.r22extras.api.event.cancelburn;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class CancelBurningEvent extends EntityEvent
{
    private final Entity entity;

    public CancelBurningEvent(Entity entity) {
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
