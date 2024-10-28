package com.RClone22.r22extras.main.registry;

import com.RClone22.r22extras.api.event.EventSubscribe;
import com.RClone22.r22extras.api.event.PreInitEventRegister;
import com.RClone22.r22extras.main.potion.SupremeResistanceClass;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.reflect.Method;

//@Mod.EventBusSubscriber(modid = ConstantExt.MODID)
@EventSubscribe.PreInitES()
public class PreInitRegistryHandler
{
    //TODO dont use the constructor in registering event buses
    // Register Events
    @SubscribeEvent
    public static void onRegisterPotions(RegistryEvent.Register<Potion> event) {
        IForgeRegistry<Potion> registry = event.getRegistry();


        SupremeResistanceClass.registerPotions(event);

        PreInitEventRegister.register(event);
    }

}
