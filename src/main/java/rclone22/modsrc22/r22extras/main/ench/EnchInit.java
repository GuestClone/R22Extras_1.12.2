package rclone22.modsrc22.r22extras.main.ench;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.Potion;
import rclone22.modsrc22.r22extras.api.items.enchant.EquipmentSlotType;

import java.util.ArrayList;
import java.util.List;

public class EnchInit
{

    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();

    public static final Enchantment INVUL_ENCH = new InvulnerabilityEnch(
            Enchantment.Rarity.RARE,
            EnumEnchantmentType.ALL,
            new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.FEET, EntityEquipmentSlot.LEGS}
    );

    static {
        ENCHANTMENTS.add(INVUL_ENCH);
    }
}

