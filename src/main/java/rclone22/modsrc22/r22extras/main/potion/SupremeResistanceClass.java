package rclone22.modsrc22.r22extras.main.potion;


import rclone22.modsrc22.r22extras.api.potions.CustomPotion;
import rclone22.modsrc22.r22extras.api.potions.PotionUtilses;
import rclone22.modsrc22.r22extras.api.utils.entityinvuls.EntityInvul;
import rclone22.modsrc22.r22extras.main.Constantr22Extras;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = Constantr22Extras.MODID)
public class SupremeResistanceClass
{

    private static final ResourceLocation SUP_RES_LOC = new ResourceLocation(Constantr22Extras.MODID,"textures/potions/r22extras_potion_supreme_res.png");


    private int statusIconIndex = -1;


    public static void runEffectEntityTickInvul(EntityLivingBase livingEntityBase)
    {
        /*
              livingEntityBase.heal(200.0F);
              livingEntityBase.extinguish();
              livingEntityBase.setAir(300);
              livingEntityBase.setEntityInvulnerable(true);


              if (!livingEntityBase.world.isRemote && livingEntityBase instanceof EntityPlayer)
              {
                  ((EntityPlayer)livingEntityBase).getFoodStats().addStats(20, 20.0F);
              }

        if (livingEntityBase instanceof EntityPlayer)
        {
            ((EntityPlayer)livingEntityBase).getFoodStats().addStats(20, 20.0F);
        }
        */

      }

    public static void runEntityTick(EntityLivingBase livingEntityBase)
    {

        if (PotionUtilses.hasPotionEffectByRegistryName(livingEntityBase, PotionUtilses.potionSupRes)) {
            // Call your external method
            runEffectEntityTickInvul(livingEntityBase);
        }
    }



    @Mod.EventBusSubscriber(modid = Constantr22Extras.MODID)
    public static class PotionSupremeResistance extends CustomPotion implements EntityInvul.IEntityInvulI.IPotionInvul
    {
        public PotionSupremeResistance(boolean isBadEffectIn, int liquidColorIn, int x, int y) {
            super(isBadEffectIn, liquidColorIn, x, y);
            this.setIconIndex(x, y);
        }

        @Override
        public void performEffect(EntityLivingBase livingEntityBase, int amplifier)
        {
            SupremeResistanceClass.runEntityTick(livingEntityBase);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public boolean hasStatusIcon() {
            return true;
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int getStatusIconIndex() {
            // Bind the custom texture for the potion icon

            Minecraft.getMinecraft().renderEngine.bindTexture(SupremeResistanceClass.SUP_RES_LOC);
            return super.getStatusIconIndex();
        }

        @Override
        public boolean setPotionInvul(Entity entity)
        {
            if (entity instanceof EntityLivingBase)
            {
                EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
                PotionEffect potionEffectB = PotionUtilses.getPotionEffectPETByRegistryName(entityLivingBase, PotionUtilses.potionSupRes);
                return potionEffectB != null;
            }


            return false;
        }


    }

}
