package rclone22.modsrc22.r22extras.main.oredict;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;

public class OreDict
{

    public static ItemStack getItemStackFromString(String itemString, int count, int meta) {
        String[] parts = itemString.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid item string: " + itemString);
        }

        ResourceLocation resource = new ResourceLocation(parts[0], parts[1]);
        Item item = Item.REGISTRY.getObject(resource);

        if (item == null) {
            throw new IllegalArgumentException("Item not found: " + itemString);
        }

        return new ItemStack(item, count, meta);
    }

    public static void registerOreDictionary(String oreName, String itemString, int meta) {
        try {
            ItemStack stack = getItemStackFromString(itemString, 1, meta);
            if (!stack.isEmpty()) {
                OreDictionary.registerOre(oreName, stack);
                System.out.println("Registered " + stack.getDisplayName() + " as " + oreName);
            }
        } catch (Exception e) {
            System.err.println("Failed to register OreDictionary entry: " + e.getMessage());
        }
    }

    public static void registerOreDictionaryList(String[] oreNames, String itemString, int meta) {
        try {
            ItemStack stack = getItemStackFromString(itemString, 1, meta);
            if (!stack.isEmpty()) {
                for (String oreName : oreNames) {
                    OreDictionary.registerOre(oreName, stack);
                    System.out.println("Registered " + stack.getDisplayName() + " as " + oreName);
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to register OreDictionary entry: " + e.getMessage());
        }
    }

    public static void registerOreDictionaryEntries() {

        registerOreDictionary("", "minecraft:", 0);
        registerOreDictionary("", "minecraft:", OreDictionary.WILDCARD_VALUE);
        registerOreDictionary("", "minecraft:", 0);

        registerOreDictionary("oreRareEarth", "galacticraftplanets:mars", 2);

        registerOreDictionary("ingotDesh", "hbm:ingot_desh", 0);

        registerOreDictionaryList(new String[]{"WorkersAlloy", "ingotWorkersAlloy"}, "galacticraftplanets:item_basic_mars", 2);

    }

}
