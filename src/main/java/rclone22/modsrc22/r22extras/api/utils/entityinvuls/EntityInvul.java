package rclone22.modsrc22.r22extras.api.utils.entityinvuls;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Contract;
import rclone22.modsrc22.r22extras.main.Constantr22Extras;

import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = Constantr22Extras.MODID)
public class EntityInvul
{


    public static boolean NullerNull;

    /**
     *
     *  Idk what I did this comment for
     *
     **/
    public static boolean EENTITYE;

    public static boolean EENTITYLIVINGBASEE;

    public static boolean EENTITYITEME;

    public static boolean IITEMI;


    public static class IEntityInvulI
    {
        public interface IEnchInvul
        {
            boolean setEnchInvul(ItemStack stack, int enchantmentLevel);
        }

        public interface IEntityInvul
        {


            boolean setEntityInvulnerable(Entity entity);


            @Contract("null, _ -> false")
            static void setEntityInvulnerableEntity(Entity entity, boolean isSetInvuln)
            {
                if (isSetInvuln && entity instanceof EntityLivingBase)
                {
                    EntityInvul.EENTITYLIVINGBASEE = isSetInvuln;
                }

                if (isSetInvuln && entity instanceof EntityItem)
                {
                    EntityInvul.EENTITYITEME = isSetInvuln;
                }

                if (isSetInvuln && entity instanceof Entity)
                {
                    EntityInvul.EENTITYE = isSetInvuln;

                }

            }

        }

        public interface IItemIndestruc
        {
            default boolean isItemIndestruc(ItemStack stack)
            {

                return false;

            }

            static void isItemIndestrucVoid(ItemStack stack, boolean isItemIndestruc)
            {

                if (isItemIndestruc && !stack.isEmpty())
                {
                    EntityInvul.IITEMI = isItemIndestruc;

                }
            }

        }

        public interface IPotionInvul
        {
            /**
             *
             * If you are using this in potions:
             *  Only use this boolean if your potion is "not" bad effect
             *  else you will end up with an error since this method
             *  is connected to removing bad effects
             *  but if you implement this into a bad effect
             *  set it to "false".
             *
             *  btw use this method for the potion to become invulnerable
             *
             */
            boolean setPotionInvul(Entity entity);
        }

    }



}



 

