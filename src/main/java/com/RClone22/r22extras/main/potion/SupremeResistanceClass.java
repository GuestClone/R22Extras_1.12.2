package com.RClone22.r22extras.main.potion;

import com.RClone22.r22extras.api.misc.nobadpotion.AntiBadPotionMain;
import com.RClone22.r22extras.api.potions.CustomPotion;
import com.RClone22.r22extras.main.ConstantExt;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = ConstantExt.MODID)
public class SupremeResistanceClass
{

    private static final ResourceLocation SUP_RES_LOC = new ResourceLocation(ConstantExt.MODID+"/textures/potions/potions.png");

    public static String super_res = "sup_res";

    private int statusIconIndex = -1;

    public static final Potion SUP_RES = new SupremeResistanceClass.PotionSupremeResistance(false, 0xFF0000, 1, 1)
            .setRegistryName(ConstantExt.MODID, super_res)
            .setPotionName("potion."+super_res);

    public static void registerPotions(RegistryEvent.Register<Potion> event) {
        IForgeRegistry<Potion> registry = event.getRegistry();

        registry.register(SUP_RES);
    }

    public static void entityTickMethod(Entity entity)
    {
        SupremeResistanceClass.runEffectEntityTick(entity);
        SupremeResistanceClass.giveIfResistance(entity);

    }

    public static void giveIfResistance(Entity entity)
    {
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase livingEntityBase = (EntityLivingBase) entity;
            PotionEffect effectResistance = (livingEntityBase.getActivePotionEffect(MobEffects.RESISTANCE));
            if (effectResistance != null && (livingEntityBase.isPotionActive(MobEffects.RESISTANCE) && effectResistance.getAmplifier() >= 255)) {
                // If the entity has level 255 Resistance, give them a new effect
                livingEntityBase.addPotionEffect(new PotionEffect(SupremeResistanceClass.SUP_RES, 40, 3, false, false));
            }
        }
    }

    public static void runEffectEntityTick(Entity entity)
    {
       if (entity instanceof EntityLivingBase)
      {
         EntityLivingBase livingEntityBase = (EntityLivingBase) entity;

         if (livingEntityBase.isPotionActive(SupremeResistanceClass.SUP_RES)) {
             livingEntityBase.heal(200.0F);
             livingEntityBase.extinguish();
             livingEntityBase.setAir(300);
             livingEntityBase.setEntityInvulnerable(true);

             AntiBadPotionMain.abpMainStatic(livingEntityBase);

             if (!livingEntityBase.world.isRemote && livingEntityBase instanceof EntityPlayer)
             {
                 ((EntityPlayer)livingEntityBase).getFoodStats().addStats(20, 20.0F);
             }
         }
      }
    }

    public static void cancelEventSupRes(EntityLivingBase livingEntityBase, Event event)
    {

        if (livingEntityBase.isPotionActive(SupremeResistanceClass.SUP_RES)) {
            event.setCanceled(true);
        }

    }


    @Mod.EventBusSubscriber(modid = ConstantExt.MODID)
    public static class PotionSupremeResistance extends CustomPotion
    {
        public PotionSupremeResistance(boolean isBadEffectIn, int liquidColorIn, int x, int y) {
            super(isBadEffectIn, liquidColorIn, x, y);
            this.setIconIndex(x, y);
        }

        @Override
        public void performEffect(EntityLivingBase livingEntityBase, int amplifier)
        {
            SupremeResistanceClass.entityTickMethod(livingEntityBase);
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

    }

}
