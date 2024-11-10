package com.RClone22.r22extras.mixins.mixin;

import com.RClone22.r22extras.api.entityattribute.CustomEntityAttribute;
import com.RClone22.r22extras.api.misc.nobadpotion.AntiBadPotionMain;
import com.RClone22.r22extras.api.potions.PotionUtilses;
import com.google.common.base.Objects;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;

@Mixin(value = EntityLivingBase.class, remap = false)
public abstract class MixinMinecraftEntityLivingBase extends Entity
{




    public MixinMinecraftEntityLivingBase(World worldIn) {
        super(worldIn);
    }


    @Shadow
    public int hurtTime;

    @Shadow
    public float prevSwingProgress;

    @Shadow
    public float swingProgress;

    @Shadow
    public float prevCameraPitch;

    @Shadow
    public float cameraPitch;

    @Shadow
    public float renderYawOffset;

    @Shadow
    public float prevRenderYawOffset;

    @Shadow
    public float rotationYawHead;

    @Shadow
    public float prevRotationYawHead;

    @Shadow
    protected EntityPlayer attackingPlayer;

    @Shadow
    protected int recentlyHit;


    @Shadow
    protected float movedDistance;

    @Shadow
    protected float prevMovedDistance;

    @Shadow
    private EntityLivingBase lastAttackedEntity;

    @Shadow
    private EntityLivingBase revengeTarget;

    @Shadow
    private int revengeTimer;

    @Shadow
    public abstract void setRevengeTarget(@Nullable EntityLivingBase livingBase);

    @Shadow
    private BlockPos prevBlockpos;

    @Shadow
    protected abstract void updatePotionEffects();

    @Shadow
    public abstract boolean isPotionActive(Potion potionIn);

    @Shadow
    protected abstract void frostWalk(BlockPos pos);

    @Shadow
    protected abstract int decreaseAirSupply(int air);

    @Shadow
    protected abstract void onDeathUpdate();

    @Shadow
    public abstract boolean isEntityAlive();

    @Unique
    EntityLivingBase r22Extras_1_12_2$entityLivingBase = (EntityLivingBase) (Object) this;

    /**
     * @author j
     * @reason j
     */
    @Overwrite
    public boolean canBreatheUnderwater()
    {
        if ((Object) this instanceof EntityLivingBase) {
            IAttributeInstance attributeInstance = r22Extras_1_12_2$entityLivingBase.getAttributeMap().getAttributeInstance(CustomEntityAttribute.SUP_RES_ATTR);


            if (attributeInstance != null && attributeInstance.getBaseValue() > 0.0D) {
                this.setAir(300);
                return true;
            }

        }

        return false;
    }

    /**
     * @author j
     * @reason j
     */
    @Overwrite
    @Override
    public void onEntityUpdate()
    {

        this.prevSwingProgress = this.swingProgress;
        super.onEntityUpdate();
        this.world.profiler.startSection("livingEntityBaseTick");
        boolean flag = r22Extras_1_12_2$entityLivingBase instanceof EntityPlayer;
        IAttributeInstance attributeInstance = r22Extras_1_12_2$entityLivingBase.getAttributeMap().getAttributeInstance(CustomEntityAttribute.SUP_RES_ATTR);



        if (this.isEntityAlive())
        {
            //
            if (attributeInstance != null && attributeInstance.getBaseValue() > 0.0D) {
                AntiBadPotionMain.abpMainStatic(r22Extras_1_12_2$entityLivingBase);
                this.extinguish();
                this.setAir(300);
            }
            //
            if (this.isEntityInsideOpaqueBlock())
            {
                this.attackEntityFrom(DamageSource.IN_WALL, 1.0F);
            }
            else if (flag && !this.world.getWorldBorder().contains(this.getEntityBoundingBox()))
            {
                double d0 = this.world.getWorldBorder().getClosestDistance(this) + this.world.getWorldBorder().getDamageBuffer();

                if (d0 < 0.0D)
                {
                    double d1 = this.world.getWorldBorder().getDamageAmount();

                    if (d1 > 0.0D)
                    {
                        this.attackEntityFrom(DamageSource.IN_WALL, (float)Math.max(1, MathHelper.floor(-d0 * d1)));
                    }
                }
            }
        }

        if (this.isImmuneToFire() || this.world.isRemote)
        {
            this.extinguish();
        }


        boolean flag1 = flag && ((EntityPlayer)r22Extras_1_12_2$entityLivingBase).capabilities.disableDamage;

        if (this.isEntityAlive())
        {
            if (!this.isInsideOfMaterial(Material.WATER))
            {
                this.setAir(300);
            }
            else
            {
                if (!this.canBreatheUnderwater() && !this.isPotionActive(MobEffects.WATER_BREATHING) && !flag1)
                {
                    this.setAir(this.decreaseAirSupply(this.getAir()));

                    if (this.getAir() == -20)
                    {
                        this.setAir(0);

                        for (int i = 0; i < 8; ++i)
                        {
                            float f2 = this.rand.nextFloat() - this.rand.nextFloat();
                            float f = this.rand.nextFloat() - this.rand.nextFloat();
                            float f1 = this.rand.nextFloat() - this.rand.nextFloat();
                            this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + (double)f2, this.posY + (double)f, this.posZ + (double)f1, this.motionX, this.motionY, this.motionZ);
                        }

                        this.attackEntityFrom(DamageSource.DROWN, 2.0F);
                    }
                }

                if (!this.world.isRemote && this.isRiding() && this.getRidingEntity() != null && this.getRidingEntity().shouldDismountInWater(this))
                {
                    this.dismountRidingEntity();
                }
            }

            if (!this.world.isRemote)
            {
                BlockPos blockpos = new BlockPos(this);

                if (!Objects.equal(this.prevBlockpos, blockpos))
                {
                    this.prevBlockpos = blockpos;
                    this.frostWalk(blockpos);
                }
            }
        }

        if (this.isEntityAlive() && this.isWet())
        {
            this.extinguish();
        }

        this.prevCameraPitch = this.cameraPitch;

        if (this.hurtTime > 0)
        {
            --this.hurtTime;
        }

        if (this.hurtResistantTime > 0 && !(r22Extras_1_12_2$entityLivingBase instanceof EntityPlayerMP))
        {
            --this.hurtResistantTime;
        }

        if (r22Extras_1_12_2$entityLivingBase.getHealth() <= 0.0F)
        {
            this.onDeathUpdate();
        }

        if (this.recentlyHit > 0)
        {
            --this.recentlyHit;
        }
        else
        {
            this.attackingPlayer = null;
        }

        if (this.lastAttackedEntity != null && !this.lastAttackedEntity.isEntityAlive())
        {
            this.lastAttackedEntity = null;
        }

        if (this.revengeTarget != null)
        {
            if (!this.revengeTarget.isEntityAlive())
            {
                this.setRevengeTarget((EntityLivingBase)null);
            }
            else if (this.ticksExisted - this.revengeTimer > 100)
            {
                r22Extras_1_12_2$entityLivingBase.setRevengeTarget((EntityLivingBase)null);
            }
        }

        this.updatePotionEffects();
        this.prevMovedDistance = this.movedDistance;
        this.prevRenderYawOffset = this.renderYawOffset;
        this.prevRotationYawHead = this.rotationYawHead;
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
        this.world.profiler.endSection();
    }



}
