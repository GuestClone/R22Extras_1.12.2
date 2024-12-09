package rclone22.modsrc22.r22extras.main.registry;


import net.minecraft.enchantment.Enchantment;
import rclone22.modsrc22.r22extras.api.event.IForgeRegI;
import rclone22.modsrc22.r22extras.main.Constantr22Extras;
import rclone22.modsrc22.r22extras.main.ench.EnchInit;
import rclone22.modsrc22.r22extras.main.potion.PotionInit;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Constantr22Extras.MODID)
public class PreInitRegistryHandler implements IForgeRegI
{
    /**
     *
     * TODO dont use the constructor in registering event buses
     * Register Events
    **/



    @SubscribeEvent
    public static void onRegisterEnchantments(RegistryEvent.Register<Enchantment> event) {
        IForgeRegistry<Enchantment> register = event.getRegistry();

        PreInitRegistryHandler preInitRegistryHandler = new PreInitRegistryHandler();

        preInitRegistryHandler.registerHandlersPreInit(event);

        for (Enchantment ench : EnchInit.ENCHANTMENTS) {
            register.register(ench);
        }

    }

    @SubscribeEvent
    public static void registerPotion(RegistryEvent.Register<Potion> event) {
        IForgeRegistry<Potion> register = event.getRegistry();

        PreInitRegistryHandler preInitRegistryHandler = new PreInitRegistryHandler();

        for (Potion potion : PotionInit.POTIONS) {
            register.register(potion);
        }

        preInitRegistryHandler.registerHandlersPreInit(event);
    }


    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        registerHandlersPreInit(this, event, PreInitRegistryHandler.class);
    }


    @Override
    public void registerHandlersPreInit(Object... handlers) {
        for (Object handler : handlers) {
            MinecraftForge.EVENT_BUS.register(handler);
        }

        MinecraftForge.EVENT_BUS.register(handlers);

    }


}
