package rclone22.modsrc22.r22extras.api.event;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.IForgeRegistry;

public interface IForgeRegI
{


    static void registerBlock(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();
    }

    static void registerItem(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();
    }


    static void registerPotion(RegistryEvent.Register<Potion> event)
    {
        IForgeRegistry<Potion> registry = event.getRegistry();
    }


    static void registerBiome(RegistryEvent.Register<Biome> event)
    {
        IForgeRegistry<Biome> registry = event.getRegistry();
    }


    static void registerSoundEvent(RegistryEvent.Register<SoundEvent> event)
    {
        IForgeRegistry<SoundEvent> registry = event.getRegistry();
    }


    static void registerPotionType(RegistryEvent.Register<PotionType> event)
    {
        IForgeRegistry<PotionType> registry = event.getRegistry();
    }


    static void registerEnchantment(RegistryEvent.Register<Enchantment> event)
    {
        IForgeRegistry<Enchantment> registry = event.getRegistry();
    }


    static void registerVillagerProfession(RegistryEvent.Register<VillagerRegistry.VillagerProfession> event)
    {
        IForgeRegistry<VillagerRegistry.VillagerProfession> registry = event.getRegistry();
    }


    static void registerEntityEntry(RegistryEvent.Register<EntityEntry> event)
    {
        IForgeRegistry<EntityEntry> registry = event.getRegistry();
    }


    static void registerIRecipe(RegistryEvent.Register<IRecipe> event)
    {
        IForgeRegistry<IRecipe> registry = event.getRegistry();
    }


    static void registerDataSerializerEntry(RegistryEvent.Register<DataSerializerEntry> event)
    {
        IForgeRegistry<DataSerializerEntry> registry = event.getRegistry();
    }


    default void preInit(FMLPreInitializationEvent event)
    {
        registerHandlersPreInit(event, this, IForgeRegI.class);
    }

    default void registerHandlersPreInit(Object... handlers) {
        for (Object handler : handlers) {
            MinecraftForge.EVENT_BUS.register(handler);
        }

        MinecraftForge.EVENT_BUS.register(handlers);

    }

}
