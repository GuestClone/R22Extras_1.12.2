package com.RClone22.r22extras.main;


import com.RClone22.r22extras.main.proxy.CommonProxy;
import com.RClone22.r22extras.main.registry.InitRegistryHandler;
import com.RClone22.r22extras.main.registry.PreInitRegistryHandler;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;

@Mod
        (
        modid = Constantr22Extras.MODID,
        name = Constantr22Extras.MOD_NAME,
        version = Constantr22Extras.MOD_VERSION

)
@Mod.EventBusSubscriber(modid = Constantr22Extras.MODID)
public class R22ExtrasMainMod
{
    @Mod.Instance(value = Constantr22Extras.MODID)
    public static R22ExtrasMainMod instance;

    @SidedProxy(modId = Constantr22Extras.MODID, clientSide = Constantr22Extras.ClientProxy, serverSide = Constantr22Extras.ServerProxy )
    public static CommonProxy proxy;

    private static Logger logger;


    public static final PreInitRegistryHandler PRE_INIT_REGISTRY_HANDLER =  new PreInitRegistryHandler();

    public static final InitRegistryHandler INIT_REGISTRY_HANDLER = new InitRegistryHandler();



    public File modDir;

    public static final Logger log = LogManager.getLogger(Constantr22Extras.MODID.toUpperCase());
    public static final int GUI = 0;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {



        registerHandlers(event, this, PRE_INIT_REGISTRY_HANDLER);
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

        registerHandlers(event, this, INIT_REGISTRY_HANDLER);

        MixinBootstrap.init();
        Mixins.addConfiguration("mixins."+ Constantr22Extras.MODID+".json");
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

        registerHandlers(event, this);
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        registerHandlers(event, this);
        proxy.serverLoad(event);
    }

    private void registerHandlers(Object... handlers) {
        for (Object handler : handlers) {
            MinecraftForge.EVENT_BUS.register(handler);
        }
        // Additionally, you can use the event object if you need to register it
        MinecraftForge.EVENT_BUS.register(handlers);

    }



}
