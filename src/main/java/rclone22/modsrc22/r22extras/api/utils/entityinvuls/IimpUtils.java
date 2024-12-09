package rclone22.modsrc22.r22extras.api.utils.entityinvuls;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import rclone22.modsrc22.r22extras.api.entityattribute.AttributeUtil;
import rclone22.modsrc22.r22extras.api.entityattribute.CustomEntityAttribute;
import rclone22.modsrc22.r22extras.api.items.enchant.EquipmentSlotType;
import rclone22.modsrc22.r22extras.api.utils.CheckNbt;

public interface IimpUtils
{

    @Contract("null -> false")
    static boolean isEntityHasInvulnerable(Entity entity)
    {


        EntityInvul.IEntityInvulI.IEntityInvul.setEntityInvulnerableEntity(entity, EntityInvul.NullerNull);

        if (entity instanceof EntityInvul.IEntityInvulI.IEntityInvul) {
            EntityInvul.IEntityInvulI.IEntityInvul cancelable = (EntityInvul.IEntityInvulI.IEntityInvul) entity;
            if (cancelable.setEntityInvulnerable(entity)) {
                return true;
            }
        }


        if (entity instanceof Entity)
        {
            if (EntityInvul.EENTITYE)
            {
                return true;
            }

        }


        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;

            if (AttributeUtil.hasCustomAttributeByRegistryName(entityLivingBase, CustomEntityAttribute.SUP_RES_ATTR)) {
                return true;
            }

            if (EntityInvul.EENTITYLIVINGBASEE) {
                return true;
            }

            for (PotionEffect effect : entityLivingBase.getActivePotionEffects()) {
                if (effect.getPotion() instanceof EntityInvul.IEntityInvulI.IPotionInvul) {
                    EntityInvul.IEntityInvulI.IPotionInvul potion = (EntityInvul.IEntityInvulI.IPotionInvul) effect.getPotion();
                    if (potion.setPotionInvul(entityLivingBase)) {
                        return true;
                    }
                }
            }

            for (ItemStack stack : entity.getArmorInventoryList()) {
                if (stack.isEmpty()) continue;
                for (Enchantment enchantment : EnchantmentHelper.getEnchantments(stack).keySet()) {
                    if (enchantment instanceof EntityInvul.IEntityInvulI.IEnchInvul) {
                        EntityInvul.IEntityInvulI.IEnchInvul deathEnchantment = (EntityInvul.IEntityInvulI.IEnchInvul) enchantment;
                        int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
                        if (deathEnchantment.setEnchInvul(stack, enchantmentLevel)) {
                            return true;
                        }
                    }
                }
            }

        }


        return false;
    }

    static boolean isEntityItemInvul(@NotNull EntityItem entityItem)
    {
            ItemStack stack = entityItem.getItem(); // Get the ItemStack from the EntityItem

            if (IimpUtils.isItemHasInvulnerable(stack))
            {
                return true;
            }

        return EntityInvul.EENTITYITEME;
    }

    static boolean isItemHasInvulnerable(ItemStack stack)
    {

        if (!stack.isEmpty()) {

            EntityInvul.IEntityInvulI.IItemIndestruc.isItemIndestrucVoid(stack, EntityInvul.NullerNull);

            if (CheckNbt.isNbtInvunerable(stack)) {
                return true;
            }

            for (Enchantment enchantment : EnchantmentHelper.getEnchantments(stack).keySet()) {
                if (enchantment instanceof EntityInvul.IEntityInvulI.IEnchInvul) {
                    EntityInvul.IEntityInvulI.IEnchInvul deathEnchantment = (EntityInvul.IEntityInvulI.IEnchInvul) enchantment;
                    int enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(enchantment, stack);
                    if (deathEnchantment.setEnchInvul(stack, enchantmentLevel)) {
                        return true;
                    }
                }
            }

            if (stack.getItem() instanceof EntityInvul.IEntityInvulI.IItemIndestruc) {
                EntityInvul.IEntityInvulI.IItemIndestruc item = (EntityInvul.IEntityInvulI.IItemIndestruc) stack.getItem();
                if (item.isItemIndestruc(stack)) {
                    return true;
                }
            }

            return EntityInvul.IITEMI;

        }
        return false;
    }


}
