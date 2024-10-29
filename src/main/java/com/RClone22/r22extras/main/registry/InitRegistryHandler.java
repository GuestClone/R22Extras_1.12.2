package com.RClone22.r22extras.main.registry;


import com.RClone22.r22extras.api.event.InitEventRegister;
import com.RClone22.r22extras.main.event.initevents.InitEventsMinecraft;
import com.RClone22.r22extras.main.event.initevents.InitItemEvents;
import com.RClone22.r22extras.main.proxy.ICommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class InitRegistryHandler implements ICommonProxy
{
    public InitRegistryHandler() {
        // Initialize event handlers
        // Register event handlers
        registerHandlers(

                initEventsMinecraft,
                initItemEvents,
                initEventRegister
        );

    }

    public static final InitEventsMinecraft initEventsMinecraft = new InitEventsMinecraft();
    public static final InitItemEvents initItemEvents = new InitItemEvents();

    public static final InitEventRegister initEventRegister = new InitEventRegister(MinecraftForge.EVENT_BUS, MinecraftForge.EVENT_BUS);


    private void registerHandlers(Object... handlers) {
        for (Object handler : handlers) {
            MinecraftForge.EVENT_BUS.register(handler);
        }
        // Additionally, you can use the event object if you need to register it
        MinecraftForge.EVENT_BUS.register(handlers);
    }

}
