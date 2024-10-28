package com.RClone22.r22extras.main;


import com.RClone22.r22extras.api.event.EventRegister;
import com.RClone22.r22extras.main.proxy.ICommonProxy;

import com.RClone22.r22extras.main.registry.PreInitRegistryHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

@Mod
        (
        modid = ConstantExt.MODID,
        name = ConstantExt.MOD_NAME,
        version = ConstantExt.MOD_VERSION
)
public class R22ExtrasMainMod
{
    @Mod.Instance(ConstantExt.MODID)
    public static R22ExtrasMainMod instance;

    @SidedProxy(modId = ConstantExt.MODID, clientSide = ConstantExt.ClientProxy, serverSide = ConstantExt.ServerProxy )
    public static ICommonProxy proxy;

    private static Logger logger;

    public static final PreInitRegistryHandler PRE_INIT_REGISTRY_HANDLER =  new PreInitRegistryHandler();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(event);

        MinecraftForge.EVENT_BUS.register(PRE_INIT_REGISTRY_HANDLER);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(event);

        MinecraftForge.EVENT_BUS.register(EventRegister.INIT_EVENT_EVENT_REGISTER);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(event);
    }





}
