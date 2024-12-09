package rclone22.modsrc22.r22extras.api.energy;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class R22CapabilityEnergy
{

    @CapabilityInject(IR22EnergyStorage.class)
    public static Capability<IR22EnergyStorage> ENERGY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IR22EnergyStorage.class, new Capability.IStorage<IR22EnergyStorage>()
                {
                    @Override
                    public NBTBase writeNBT(Capability<IR22EnergyStorage> capability, IR22EnergyStorage instance, EnumFacing side)
                    {
                        return new NBTTagInt(instance.getEnergyStored());
                    }

                    @Override
                    public void readNBT(Capability<IR22EnergyStorage> capability, IR22EnergyStorage instance, EnumFacing side, NBTBase nbt)
                    {
                        if (!(instance instanceof R22EnergyStorage))
                            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
                        ((R22EnergyStorage)instance).energy = ((NBTTagInt)nbt).getInt();
                    }
                },
                () -> new R22EnergyStorage(1000));
    }

}
