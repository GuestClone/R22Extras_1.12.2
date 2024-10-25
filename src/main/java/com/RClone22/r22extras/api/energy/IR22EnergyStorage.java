package com.RClone22.r22extras.api.energy;

import net.minecraftforge.energy.IEnergyStorage;

public interface IR22EnergyStorage extends IEnergyStorage
{

    int receiveEnergy(int maxReceive, boolean simulate);


    int extractEnergy(int maxExtract, boolean simulate);


    int getEnergyStored();


    int getMaxEnergyStored();


    boolean canExtract();


    boolean canReceive();

}
