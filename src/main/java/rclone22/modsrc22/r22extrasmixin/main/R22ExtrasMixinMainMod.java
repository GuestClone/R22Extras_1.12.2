package rclone22.modsrc22.r22extrasmixin.main;



import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
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

import rclone22.modsrc22.r22extrasmixin.main.proxy.CommonProxyR22Mixin;

import java.io.File;

@Mod(
        modid = Constantr22ExtrasMixin.MODID,
        name = Constantr22ExtrasMixin.MOD_NAME,
        version = Constantr22ExtrasMixin.MOD_VERSION,
        dependencies = "required-after:r22extras;"
)
@Mod.EventBusSubscriber(modid = Constantr22ExtrasMixin.MODID)
public class R22ExtrasMixinMainMod
{
    public R22ExtrasMixinMainMod()
    {
    }

    @Mod.Instance(value = Constantr22ExtrasMixin.MODID)
    public static R22ExtrasMixinMainMod instance;

    @SidedProxy(modId = Constantr22ExtrasMixin.MODID, clientSide = Constantr22ExtrasMixin.ClientProxy, serverSide = Constantr22ExtrasMixin.ServerProxy )
    public static CommonProxyR22Mixin proxy;

    private static Logger logger;

    public File modDir;

    public static final Logger log = LogManager.getLogger(Constantr22ExtrasMixin.MODID.toUpperCase());
    public static final int GUI = 0;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

        registerHandlers(event, this, R22ExtrasMixinMainMod.class);

        this.mixinPreInit();

        proxy.preInit(event);

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

        registerHandlers(event, this, R22ExtrasMixinMainMod.class);

        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

        registerHandlers(event, this, R22ExtrasMixinMainMod.class);
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        registerHandlers(event, this, R22ExtrasMixinMainMod.class);
        proxy.serverLoad(event);
    }

    private void registerHandlers(Object... handlers) {
        for (Object handler : handlers) {
            MinecraftForge.EVENT_BUS.register(handler);
        }
        MinecraftForge.EVENT_BUS.register(handlers);

    }

    static {

        FluidRegistry.enableUniversalBucket();
    }

    public void mixinPreInit()
    {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.r22extrasmixin.json");
    }



}
