package com.RClone22.r22extras.main;

import com.RClone22.r22extras.main.proxy.ICommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class Packets implements ICommonProxy
{
    public static SimpleNetworkWrapper network;

    public static EntityPlayer getPlayerFromContext(MessageContext ctx){
        EntityPlayer thePlayer = (ctx.side.isClient() ? R22ExtrasMainMod.proxy.getPlayerClient() : ctx.getServerHandler().player);
        return thePlayer;
    }

    public static NetworkRegistry.TargetPoint targetPointAroundBlockPos(int dimension, BlockPos pos, double distance){
        return new NetworkRegistry.TargetPoint(dimension, pos.getX()+0.5d, pos.getY()+0.5d, pos.getZ()+0.5d, distance);
    }

    public static NetworkRegistry.TargetPoint targetPointAroundEnt(Entity ent, double distance){
        return new NetworkRegistry.TargetPoint(ent.dimension, ent.posX, ent.posY, ent.posZ, distance);
    }

    public static NetworkRegistry.TargetPoint targetPointAroundEnt(TileEntity ent, double distance){
        return new NetworkRegistry.TargetPoint(ent.getWorld().provider.getDimension(), ent.getPos().getX(), ent.getPos().getY(), ent.getPos().getZ(), distance);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    public void init(FMLInitializationEvent event) {

        network = NetworkRegistry.INSTANCE.newSimpleChannel(Constantr22Extras.MODID);
        int packetid=0;


    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }
}
