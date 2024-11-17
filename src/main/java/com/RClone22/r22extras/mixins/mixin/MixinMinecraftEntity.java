package com.RClone22.r22extras.mixins.mixin;

import com.RClone22.r22extras.api.entityattribute.CustomEntityAttribute;
import com.RClone22.r22extras.api.utils.EntityInvul;
import com.RClone22.r22extras.api.utils.GlobalVar;
import com.RClone22.r22extras.api.utils.NBTList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.*;

import javax.annotation.Nullable;
import java.util.Objects;


@Mixin(value = Entity.class, remap = false)
public abstract class MixinMinecraftEntity
{

    @Shadow
    private boolean invulnerable;

    @Shadow
    private int fire;

    @Shadow
    protected boolean isImmuneToFire;

    @Shadow
    public abstract void setFire(int seconds);

    @Shadow
    public abstract boolean isEntityInvulnerable(DamageSource source);

    @Shadow
    protected abstract void markVelocityChanged();

    @Shadow
    protected int rideCooldown;

    @Shadow
    public World world;

    @Shadow
    public double prevPosX;

    @Shadow
    public double prevPosY;

    @Shadow
    public double prevPosZ;

    @Shadow
    public double posX;

    @Shadow
    public double posY;

    @Shadow
    public double posZ;

    @Shadow
    public float rotationYaw;

    @Shadow
    public float rotationPitch;

    @Shadow
    public float prevRotationYaw;

    @Shadow
    public float prevRotationPitch;

    @Shadow
    protected boolean inPortal;

    @Shadow
    protected int portalCounter;

    @Shadow
    protected boolean firstUpdate;

    @Shadow
    private Entity ridingEntity;

    @Shadow
    public abstract boolean isInLava();

    @Shadow
    public abstract AxisAlignedBB getEntityBoundingBox();

    @Shadow
    private AxisAlignedBB boundingBox;

    @Shadow
    protected abstract void setFlag(int flag, boolean set);


    @Shadow
    protected abstract void outOfWorld();

    @Shadow
    public abstract void dismountRidingEntity();


    @Shadow
    public float fallDistance;

    @Shadow
    public abstract boolean handleWaterMovement();

    @Shadow
    protected abstract void decrementTimeUntilPortal();

    @Shadow
    public abstract void spawnRunningParticles();

    @Shadow
    public int timeUntilPortal;

    @Shadow
    public float prevDistanceWalkedModified;

    @Shadow
    public float distanceWalkedModified;

    @Shadow
    public abstract int getMaxInPortalTime();

    @Shadow
    @Nullable
    public abstract Entity changeDimension(int dimensionIn);

    @Shadow
    public abstract int getPortalCooldown();

    @Shadow
    public abstract boolean isRiding();

    @Shadow
    @Nullable
    public abstract Entity getRidingEntity();


    @Shadow public abstract boolean isEntityAlive();

    @Unique
    Entity r22Extras_1_12_2$this = (Entity) (Object) this;

    @Unique
    private static final String ITEM_INDESTRUC = NBTList.ITEM_INDESTRUC;

    @Unique
    private static final String ENTITY_IS_INVUNERABLE_T = NBTList.ENTITY_IS_INVUNERABLE_T;

    @Unique
    private boolean r22Extras_1_12_2$setMakeInvulnerableEntity = GlobalVar.isEntityThingInvunerable;


    @Shadow
    public abstract void setEntityInvulnerable(boolean isInvulnerable);


    @Shadow public boolean isDead;

    /**
     * @author J
     * @reason J
     */
    @Overwrite
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        }
        else if (this.r22Extras_1_12_2$setMakeInvulnerableEntity)
        {
            return false;
        }
        else
        {
            this.markVelocityChanged();
            return false;
        }
    }

    /**
     * @author J
     * @reason J
     */
    @Overwrite
    public void extinguish()
    {
        this.fire = 0;
        this.setFire(0);
    }

    /**
     * @author J
     * @reason J
     */
    @Overwrite
    public boolean isImmuneToExplosions()
    {

        Entity entity = (Entity) (Object) this;;;;;


        if (this.r22Extras_1_12_2$setMakeInvulnerableEntity)
        {
            this.r22Extras_1_12_2$NullEmptyVoid();
            return true;
        }

        if (this.invulnerable)
        {
            this.r22Extras_1_12_2$NullEmptyVoid();
            return true;
        }

        return false;
    }

    /**
     * @author J
     * @reason J
     */
    @Overwrite
    public final boolean isImmuneToFire()
    {
        Entity entity = (Entity) (Object) this;

        /*
        if (this.r22Extras_1_12_2$setMakeInvulnerableEntity)
        {
            this.r22Extras_1_12_2$NullEmptyVoid();
            return this.isImmuneToFire = true;
        }
        */
        if (this.invulnerable)
        {
            this.r22Extras_1_12_2$NullEmptyVoid();
            return this.isImmuneToFire = true;
        }

        return this.isImmuneToFire;
    }

    /**
     * @author J
     * @reason J
     */
    @Overwrite
    protected void setOnFireFromLava()
    {
        Entity entity = (Entity) (Object) this;
        if (!this.isImmuneToFire)
        {
            this.attackEntityFrom(DamageSource.LAVA, 4.0F);
            this.setFire(15);
        }

        if (this.isImmuneToFire)
        {
            this.setFire(0);
        }

        if (this.invulnerable)
        {
            this.setFire(0);
        }

        /*
        if (this.r22Extras_1_12_2$setMakeInvulnerableEntity)
        {
            this.setFire(0);
        }
        */
    }



    
    /**
     * @author J
     * @reason J
     */
    @Overwrite
    public void onEntityUpdate()
    {

        Entity entity = (Entity) (Object) this;
        this.world.profiler.startSection("entityBaseTick");

        if (this.isRiding() && Objects.requireNonNull(this.getRidingEntity()).isDead)
        {
            this.dismountRidingEntity();
        }

        if (this.rideCooldown > 0)
        {
            --this.rideCooldown;
        }

        this.prevDistanceWalkedModified = this.distanceWalkedModified;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.prevRotationPitch = this.rotationPitch;
        this.prevRotationYaw = this.rotationYaw;

        if (!this.world.isRemote && this.world instanceof WorldServer)
        {
            this.world.profiler.startSection("portal");

            if (this.inPortal)
            {
                MinecraftServer minecraftserver = this.world.getMinecraftServer();

                if (minecraftserver != null)
                {
                    if (minecraftserver.getAllowNether())
                    {
                        if (!this.isRiding())
                        {
                            int i = this.getMaxInPortalTime();

                            if (this.portalCounter++ >= i)
                            {
                                this.portalCounter = i;
                                this.timeUntilPortal = this.getPortalCooldown();
                                int j;

                                if (this.world.provider.getDimensionType().getId() == -1)
                                {
                                    j = 0;
                                }
                                else
                                {
                                    j = -1;
                                }

                                this.changeDimension(j);
                            }
                        }

                        this.inPortal = false;
                    }
                }
                
            }
            else
            {
                if (this.portalCounter > 0)
                {
                    this.portalCounter -= 4;
                }

                if (this.portalCounter < 0)
                {
                    this.portalCounter = 0;
                }
            }

            this.decrementTimeUntilPortal();
            this.world.profiler.endSection();
        }

        this.spawnRunningParticles();
        this.handleWaterMovement();

        if (this.world.isRemote)
        {
            this.extinguish();
        }
        else if (this.fire > 0)
        {
            if (this.isImmuneToFire)
            {
                this.fire -= 4;

                if (this.fire < 0)
                {
                    this.extinguish();
                }
            }
            else
            {
                if (this.fire % 20 == 0)
                {
                    this.attackEntityFrom(DamageSource.ON_FIRE, 1.0F);
                }

                --this.fire;
            }
        }

        if (this.isInLava())
        {
            this.setOnFireFromLava();
            this.fallDistance *= 0.5F;
        }

        if (this.posY < -64.0D)
        {
            this.outOfWorld();
        }

        if (!this.world.isRemote)
        {
            this.setFlag(0, this.fire > 0);
        }

        this.firstUpdate = false;
        this.world.profiler.endSection();

        this.r22Extras_1_12_2$entityInvul(entity);
    }


    @Unique
    public void r22Extras_1_12_2$entityInvul(Entity entity)
    {
        NBTTagCompound entityNbtData = new NBTTagCompound();
        entity.writeToNBT(entityNbtData); // Get current NBT data


        if ((Object) this instanceof EntityItem)
        {
            EntityItem entityItem = (EntityItem) (Object) this;

            ItemStack itemStack = entityItem.getItem();;

            if (itemStack.hasTagCompound()) {

                NBTTagCompound tagCompound = itemStack.getTagCompound();
                if (tagCompound != null && tagCompound.getBoolean(ITEM_INDESTRUC)) {
                    this.r22Extras_1_12_2$setInvulUtil(entityItem);
                    this.r22Extras_1_12_2$setMakeInvulnerableEntity = true;
                } else {
                    this.r22Extras_1_12_2$NullEmptyVoid();
                    this.r22Extras_1_12_2$setMakeInvulnerableEntity = false;

                }
            }
        }

        if (entity instanceof EntityInvul.IEntityInvul) {
            EntityInvul.IEntityInvul customEntity = (EntityInvul.IEntityInvul) entity;


            if (customEntity.setEntityInvulnerable(entity)) {
                this.r22Extras_1_12_2$setInvulUtil(entity);
                this.r22Extras_1_12_2$setMakeInvulnerableEntity = true;
            }
            else {
                this.r22Extras_1_12_2$NullEmptyVoid();
                this.r22Extras_1_12_2$setMakeInvulnerableEntity = false;
            }
        }

        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            IAttributeInstance attributeInstance = entityLivingBase.getAttributeMap().getAttributeInstance(CustomEntityAttribute.SUP_RES_ATTR);

            if (attributeInstance != null && attributeInstance.getBaseValue() > 0.0D) {

                this.r22Extras_1_12_2$setInvulUtil(entity);
                this.r22Extras_1_12_2$setMakeInvulnerableEntity = true;

            }

            else {
                this.r22Extras_1_12_2$NullEmptyVoid();
                this.r22Extras_1_12_2$setMakeInvulnerableEntity = false;
            }
        }

        if (this.r22Extras_1_12_2$setMakeInvulnerableEntity)
        {
            r22Extras_1_12_2$setInvulUtil(entity);
        }

        entity.readFromNBT(entityNbtData);


    }



    @Unique
    private void r22Extras_1_12_2$setInvulUtil(Entity entity)
    {
        NBTTagCompound entityNbtData = new NBTTagCompound();
        entity.writeToNBT(entityNbtData); // Get current NBT data


            this.setFire(0);
            this.fire = 0;
            this.extinguish();
            this.invulnerable = true;
            this.isImmuneToFire = true;
            this.setEntityInvulnerable(true);
            this.isDead = false;



        entity.readFromNBT(entityNbtData);
    }

    @Unique
    private void r22Extras_1_12_2$NullEmptyVoid()
    {}

}
