package com.RClone22.r22extras.main.registry;


import com.RClone22.r22extras.api.event.IForgeRegI;
import com.RClone22.r22extras.main.ConstantExt;
import com.RClone22.r22extras.main.potion.SupremeResistanceClass;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = ConstantExt.MODID)
public class PreInitRegistryHandler implements IForgeRegI
{
    //TODO dont use the constructor in registering event buses
    // Register Events

    @SubscribeEvent
    public static void registerPotion(RegistryEvent.Register<Potion> event) {
        IForgeRegistry<Potion> registry = event.getRegistry();
        SupremeResistanceClass.registerPotions(event);
    }


}
