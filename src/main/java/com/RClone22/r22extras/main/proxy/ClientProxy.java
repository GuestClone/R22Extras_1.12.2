package com.RClone22.r22extras.main.proxy;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class ClientProxy extends CommonProxy implements ICommonProxy
{

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {

    }


    @Override
    public void init(FMLInitializationEvent event)
    {

    }


    @Override
    public void postInit(FMLPostInitializationEvent event)
    {

    }

    @Override
    public void serverLoad(FMLServerStartingEvent event)
    {

    }

    @Override
    public EntityPlayer getPlayerClient() {
        return Minecraft.getMinecraft().player;
    }

}
