package rclone22.modsrc22.r22extrasmixin.main.proxy;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import rclone22.modsrc22.r22extrasmixin.main.Constantr22ExtrasMixin;

public class ClientProxyR22Mixin extends CommonProxyR22Mixin implements ICommonProxyR22Mixin
{

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        OBJLoader.INSTANCE.addDomain(Constantr22ExtrasMixin.MODID);
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
