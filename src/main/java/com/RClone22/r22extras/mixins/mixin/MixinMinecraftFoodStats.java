package com.RClone22.r22extras.mixins.mixin;

import com.RClone22.r22extras.api.entityattribute.CustomEntityAttribute;
import com.RClone22.r22extras.api.potions.PotionUtilses;

import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.world.EnumDifficulty;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(value = FoodStats.class, remap = false)
public abstract class MixinMinecraftFoodStats
{

    @Shadow
    private int foodLevel = 20;

    @Shadow
    private float foodSaturationLevel = 5.0F;

    @Shadow
    private float foodExhaustionLevel;

    @Shadow
    private int foodTimer;

    @Shadow
    private int prevFoodLevel = 20;


    @Shadow
    public abstract void addExhaustion(float exhaustion);


    /**
     * @author j
     * @reason j
     */
    @Overwrite
    public void onUpdate(EntityPlayer player)
    {
        EnumDifficulty enumdifficulty = player.world.getDifficulty();
        this.prevFoodLevel = this.foodLevel;

        IAttributeInstance attributeInstance = player.getAttributeMap().getAttributeInstance(CustomEntityAttribute.SUP_RES_ATTR);

        if (attributeInstance != null && attributeInstance.getBaseValue() > 0.0D) {
            this.foodSaturationLevel = 20.0F;
            this.foodLevel = 20;
            player.heal(1.0F);
        }



        if (this.foodExhaustionLevel > 4.0F)
        {
            this.foodExhaustionLevel -= 4.0F;

            if (this.foodSaturationLevel > 0.0F)
            {
                this.foodSaturationLevel = Math.max(this.foodSaturationLevel - 1.0F, 0.0F);
            }
            else if (enumdifficulty != EnumDifficulty.PEACEFUL)
            {
                this.foodLevel = Math.max(this.foodLevel - 1, 0);
            }
        }

        boolean flag = player.world.getGameRules().getBoolean("naturalRegeneration");

        if (flag && this.foodSaturationLevel > 0.0F && player.shouldHeal() && this.foodLevel >= 20)
        {
            ++this.foodTimer;

            if (this.foodTimer >= 10)
            {
                float f = Math.min(this.foodSaturationLevel, 6.0F);
                player.heal(f / 6.0F);
                this.addExhaustion(f);
                this.foodTimer = 0;
            }
        }
        else if (flag && this.foodLevel >= 18 && player.shouldHeal())
        {
            ++this.foodTimer;

            if (this.foodTimer >= 80)
            {
                player.heal(1.0F);
                this.addExhaustion(6.0F);
                this.foodTimer = 0;
            }
        }
        else if (this.foodLevel <= 0)
        {
            ++this.foodTimer;

            if (this.foodTimer >= 80)
            {
                if (player.getHealth() > 10.0F || enumdifficulty == EnumDifficulty.HARD || player.getHealth() > 1.0F && enumdifficulty == EnumDifficulty.NORMAL)
                {
                    player.attackEntityFrom(DamageSource.STARVE, 1.0F);
                }

                this.foodTimer = 0;
            }
        }
        else
        {
            this.foodTimer = 0;
        }
    }

}
