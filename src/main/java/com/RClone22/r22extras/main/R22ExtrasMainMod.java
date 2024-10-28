package com.RClone22.r22extras.main;


import com.RClone22.r22extras.api.event.EventRegister;
import com.RClone22.r22extras.main.proxy.ICommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

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



    public static final EventRegister EVENT_REGISTER = new EventRegister(MinecraftForge.EVENT_BUS);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(event);

        MinecraftForge.EVENT_BUS.register(EVENT_REGISTER);




    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(event);

        MinecraftForge.EVENT_BUS.register(EVENT_REGISTER);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(event);

        MinecraftForge.EVENT_BUS.register(EVENT_REGISTER);

    }





}
