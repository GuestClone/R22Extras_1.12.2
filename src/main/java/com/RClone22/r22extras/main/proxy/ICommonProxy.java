package com.RClone22.r22extras.main.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface ICommonProxy
{


    default void preInit(FMLPreInitializationEvent event)
    {

    }


    default void init(FMLInitializationEvent event)
    {

    }


    default void postInit(FMLPostInitializationEvent event)
    {

    }

}
