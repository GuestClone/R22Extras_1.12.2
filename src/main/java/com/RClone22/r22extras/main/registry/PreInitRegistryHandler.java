package com.RClone22.r22extras.main.registry;

import com.RClone22.r22extras.main.Constant;
import com.RClone22.r22extras.main.potion.SupremeResistanceClass;
import com.RClone22.r22extras.main.proxy.ICommonProxy;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Constant.MODID)
public class PreInitRegistryHandler implements ICommonProxy
{
    //TODO dont use the constructor in registering event buses

    // Register Events
    @SubscribeEvent
    public static void onRegisterPotions(RegistryEvent.Register<Potion> event) {
        IForgeRegistry<Potion> registry = event.getRegistry();

        SupremeResistanceClass.registerPotions(event);


    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

}
