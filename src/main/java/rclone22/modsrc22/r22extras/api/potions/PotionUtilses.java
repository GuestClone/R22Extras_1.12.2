package rclone22.modsrc22.r22extras.api.potions;

import rclone22.modsrc22.r22extras.main.Constantr22Extras;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionUtilses
{

    //TODO this code here can be copied and used by other people in their mods or coding

    public static String super_res = "sup_res_eff";

    public static String potionSupRes = Constantr22Extras.MODID + ":" + super_res;

    public static boolean hasPotionEffectByRegistryName(EntityLivingBase livingEntityBase, String potionRegistryName) {
        // Check if any active potion effect matches the specified registry name
        return livingEntityBase.getActivePotionEffects().stream()
                .map(PotionEffect::getPotion)
                .map(Potion::getRegistryName)
                .filter(java.util.Objects::nonNull) // Check for null before converting to a string
                .map(Object::toString)
                .anyMatch(potionName -> potionName.equals(potionRegistryName));
    }

    // Add a potion effect to an entity by its registry name and amplifier
    public static void addPotionEffectByRegistryName(EntityLivingBase entity, String potionRegistryName, int duration, int amplifier, boolean ambient, boolean particles) {
        Potion potion = ForgeRegistries.POTIONS.getValue(new ResourceLocation(potionRegistryName));

        if (potion != null) {
            PotionEffect potionEffect = new PotionEffect(potion, duration, amplifier, ambient, particles);
            entity.addPotionEffect(potionEffect);
        }
    }

    // Remove a potion effect from an entity by its registry name
    public static void removePotionEffectByRegistryName(EntityLivingBase entity, String potionRegistryName) {
        Potion potion = ForgeRegistries.POTIONS.getValue(new ResourceLocation(potionRegistryName));

        if (potion != null) {
            entity.removePotionEffect(potion);
        }
    }

    public static PotionEffect getPotionEffectPETByRegistryName(EntityLivingBase livingEntityBase, String potionRegistryName) {
        // Check for an active potion effect that matches the specified registry name
        for (PotionEffect effect : livingEntityBase.getActivePotionEffects()) {
            Potion potion = effect.getPotion();
            if (potion.getRegistryName() != null && potion.getRegistryName().toString().equals(potionRegistryName)) {
                return effect; // Return the PotionEffect if the registry name matches
            }
        }
        return null; // Return null if no matching potion effect is found
    }




}
