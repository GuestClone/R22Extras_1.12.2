package com.RClone22.r22extras.mixins.mixin;

import com.RClone22.r22extras.api.utils.EntityInvul;
import com.RClone22.r22extras.api.utils.NBTList;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;

@Mixin(value = EntityItem.class, remap = false)
@Implements(@Interface(iface = EntityInvul.IItemIndestruc.class, prefix = "isItemIndestruc$"))
public abstract class MixinMinecraftEntityItem extends Entity implements EntityInvul.IItemIndestruc
{

    public MixinMinecraftEntityItem(World worldIn) {
        super(worldIn);
    }



    @Shadow
    private int age;

    @Shadow
    private int pickupDelay;

    @Shadow
    public abstract ItemStack getItem();

    @Shadow
    private void searchForOtherItemsNearby() {}

    @Shadow
    public int lifespan = 6000;

    @Shadow
    private int health;

    @Unique
    private static final String ITEM_INDESTRUC = NBTList.ITEM_INDESTRUC;


    @Override
    public boolean isItemIndestruc(Item item, ItemStack stack)
    {
        if (stack.hasTagCompound()) {
            NBTTagCompound tagCompound = stack.getTagCompound();
            if (tagCompound != null && (tagCompound.getBoolean(ITEM_INDESTRUC)))
            {
                EntityInvul.isIndestrucitble = true;
                return true;
            }
        }

        EntityInvul.isIndestrucitble = false;
        return false;
    }

    /**
     * @author J
     * @reason J
     */
    @Overwrite
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        EntityItem entityItem = (EntityItem) (Object) this;
        ItemStack itemStack = entityItem.getItem();
        NBTTagCompound tagCompound = itemStack.getTagCompound();

        if (this.world.isRemote || this.isDead) return false; //Forge: Fixes MC-53850
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (!this.getItem().isEmpty() && this.getItem().getItem() == Items.NETHER_STAR && source.isExplosion())
        {
            return false;
        }

        else if (EntityInvul.isIndestrucitble || (EntityInvul.isIndestrucitble && (source.isExplosion() || source.isExplosion() ||  source.isDamageAbsolute() || source.isMagicDamage()))) {
            this.r22Extras_1_12_2$setInvulUtil(entityItem);
            return false;
        }

        else {
            this.markVelocityChanged();
            this.health = (int)((float)this.health - amount);

            if (this.health <= 0)
            {
                this.setDead();
            }

            return false;
        }
    }

    /**
     * @author J
     * @reason J
     */
    @Overwrite
    @Override
    public void onUpdate()
    {
        EntityItem entityItem = (EntityItem) (Object) this;


        ItemStack itemStack = entityItem.getItem();
        Item item = itemStack.getItem();
        NBTTagCompound tagCompound = itemStack.getTagCompound();

        if (entityItem instanceof EntityInvul.IItemIndestruc) {
            EntityInvul.IItemIndestruc itemIndestruc = (EntityInvul.IItemIndestruc) item;
            if (itemIndestruc.isItemIndestruc(item, itemStack)) {
                this.r22Extras_1_12_2$setInvulUtil(entityItem);
            }
        }

        if (entityItem instanceof EntityInvul.IEntityInvul) {
            EntityInvul.IEntityInvul iEntityInvul = (EntityInvul.IEntityInvul) item;
            if (iEntityInvul.setEntityInvulnerable(entityItem)) {
                this.r22Extras_1_12_2$setInvulUtil(entityItem);
            }
        }

        if (EntityInvul.isIndestrucitble)
        {
            this.r22Extras_1_12_2$setInvulUtil(entityItem);
        }

        if (getItem().getItem().onEntityItemUpdate(entityItem)) return;
        if (this.getItem().isEmpty())
        {
            this.setDead();
        }
        else
        {
            super.onUpdate();

            if (this.pickupDelay > 0 && this.pickupDelay != 32767)
            {
                --this.pickupDelay;
            }

            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            double d0 = this.motionX;
            double d1 = this.motionY;
            double d2 = this.motionZ;

            if (!this.hasNoGravity())
            {
                this.motionY -= 0.03999999910593033D;
            }

            if (this.world.isRemote)
            {
                this.noClip = false;
            }
            else
            {
                this.noClip = this.pushOutOfBlocks(this.posX, (this.getEntityBoundingBox().minY + this.getEntityBoundingBox().maxY) / 2.0D, this.posZ);
            }

            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            boolean flag = (int)this.prevPosX != (int)this.posX || (int)this.prevPosY != (int)this.posY || (int)this.prevPosZ != (int)this.posZ;

            if (flag || this.ticksExisted % 25 == 0)
            {
                if (this.world.getBlockState(new BlockPos(this)).getMaterial() == Material.LAVA)
                {
                    this.motionY = 0.20000000298023224D;
                    this.motionX = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
                    this.motionZ = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
                    this.playSound(SoundEvents.ENTITY_GENERIC_BURN, 0.4F, 2.0F + this.rand.nextFloat() * 0.4F);
                }

                if (!this.world.isRemote)
                {
                    this.searchForOtherItemsNearby();
                }
            }

            float f = 0.98F;

            if (this.onGround)
            {
                BlockPos underPos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ));
                net.minecraft.block.state.IBlockState underState = this.world.getBlockState(underPos);
                f = underState.getBlock().getSlipperiness(underState, this.world, underPos, this) * 0.98F;
            }

            this.motionX *= (double)f;
            this.motionY *= 0.9800000190734863D;
            this.motionZ *= (double)f;

            if (this.onGround)
            {
                this.motionY *= -0.5D;
            }

            if (this.age != -32768)
            {
                ++this.age;
            }

            this.handleWaterMovement();

            if (!this.world.isRemote)
            {
                double d3 = this.motionX - d0;
                double d4 = this.motionY - d1;
                double d5 = this.motionZ - d2;
                double d6 = d3 * d3 + d4 * d4 + d5 * d5;

                if (d6 > 0.01D)
                {
                    this.isAirBorne = true;
                }
            }



            if (!this.world.isRemote && this.age >= lifespan)
            {
                int hook = net.minecraftforge.event.ForgeEventFactory.onItemExpire(entityItem, itemStack);
                if (hook < 0) this.setDead();
                else          this.lifespan += hook;
            }
            if (itemStack.isEmpty())
            {
                this.setDead();
            }
        }
    }

    @Unique
    private void r22Extras_1_12_2$setInvulUtil(Entity entity)
    {
        NBTTagCompound entityNbtData = new NBTTagCompound();
        entity.writeToNBT(entityNbtData); // Get current NBT data


        this.setFire(0);
        this.extinguish();
        this.isImmuneToFire = true;


        entity.readFromNBT(entityNbtData);
    }

}
