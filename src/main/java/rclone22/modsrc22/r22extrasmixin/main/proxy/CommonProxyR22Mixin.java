package rclone22.modsrc22.r22extrasmixin.main.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;


public class CommonProxyR22Mixin implements ICommonProxyR22Mixin
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

    public EntityPlayer getPlayerClient() {
        return null;
    }

    public void init() { }
}
