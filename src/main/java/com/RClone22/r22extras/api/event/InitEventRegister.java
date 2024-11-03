package com.RClone22.r22extras.api.event;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InitEventRegister
{


    public InitEventRegister(Object... objA)
    {
        for (Object handler : objA) {
            MinecraftForge.EVENT_BUS.register(handler);
        }
        // Additionally, you can use the event object if you need to register it
        MinecraftForge.EVENT_BUS.register(objA);

        registerHandlers(objA, this);

    }

    public static void registerHandlers(Object... handlers) {
        for (Object handler : handlers) {
            MinecraftForge.EVENT_BUS.register(handler);
        }
        // Additionally, you can use the event object if you need to register it
        MinecraftForge.EVENT_BUS.register(handlers);
    }

}

