package com.RClone22.r22extras.api.event;

import net.minecraftforge.common.MinecraftForge;

public class InitEventRegister
{

    public InitEventRegister(Object objA, Object objB)
    {
        InitEventRegister.registerAB(objA, this);
        InitEventRegister.registerAB(objB, this);

        InitEventRegister.registerA(objA);
        InitEventRegister.registerA(this);
    }

    public static void registerAB(Object objA, Object objB)
    {
        MinecraftForge.EVENT_BUS.register(objA);
        MinecraftForge.EVENT_BUS.register(objB);
    }

    public static void registerA(Object objA)
    {
        MinecraftForge.EVENT_BUS.register(objA);
    }

}

