package com.RClone22.r22extras.main;

import com.RClone22.r22extras.main.proxy.ICommonProxy;
import com.RClone22.r22extras.main.event.InitEventsMinecraft;

import com.RClone22.r22extras.main.registry.PreInitRegistryHandler;

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
        modid = Constant.MODID,
        name = Constant.MOD_NAME,
        version = Constant.MOD_VERSION
)
@Mod.EventBusSubscriber(modid = Constant.MODID)
public class R22ExtrasMainMod
{
    @Mod.Instance(Constant.MODID)
    public static R22ExtrasMainMod instance;

    @SidedProxy(modId = Constant.MODID, clientSide = Constant.ClientProxy, serverSide = Constant.ServerProxy )
    public static ICommonProxy proxy;


    public final InitEventsMinecraft initEventsMinecraft = new InitEventsMinecraft(); // Init
    public final PreInitRegistryHandler preInitRegistryHandler = new PreInitRegistryHandler(); // Pre Init

    private static Logger logger;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);

        MinecraftForge.EVENT_BUS.register(preInitRegistryHandler);

        MixinBootstrap.init();
        Mixins.addConfiguration("mixins."+Constant.MODID+".json");

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);

        MinecraftForge.EVENT_BUS.register(initEventsMinecraft);

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);

    }







}
