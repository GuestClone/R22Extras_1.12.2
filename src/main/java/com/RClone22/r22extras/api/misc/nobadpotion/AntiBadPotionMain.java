package com.RClone22.r22extras.api.misc.nobadpotion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

import java.util.*;
import java.util.stream.Collectors;

public class AntiBadPotionMain
{
    public static void abpmList(Entity entity)
    {
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;

            // Set of registry names for bad effects to be removed
            Set<String> badEffectsMC = BadEffectsList.mcBadEffects();
            Set<String> badEffectsMod = BadEffectsList.modBadEffects();

            // Collect the potions to be removed to avoid concurrent modification issues
            List<Potion> potionsToRemove = entityLivingBase.getActivePotionEffects().stream()
                    .map(PotionEffect::getPotion)
                    .filter(potion -> badEffectsMC.contains(Objects.requireNonNull(potion.getRegistryName()).toString()) ||
                            badEffectsMod.contains(potion.getRegistryName().toString()))
                    .collect(Collectors.toList());

            // Remove the collected potions
            potionsToRemove.forEach(entityLivingBase::removePotionEffect);

        }

    }

    public void abpmMixin(Entity entity)
    {

    }

    public static void abpmIter(Entity entity) {
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;

            // Create a list to collect the potions to be removed to avoid concurrent modification
            List<Potion> potionsToRemove = entityLivingBase.getActivePotionEffects().stream()
                    .map(PotionEffect::getPotion)
                    .filter(Potion::isBadEffect)
                    .collect(Collectors.toList());

            // Remove the collected potions
            potionsToRemove.forEach(entityLivingBase::removePotionEffect);

        }
    }



    public static void abpMainStatic(Entity entity)
    {

        AntiBadPotionMain antiBadPotionMain = new AntiBadPotionMain();

        antiBadPotionMain.abpmMixin(entity);
             abpmList(entity);
             abpmIter(entity);


    }



}
