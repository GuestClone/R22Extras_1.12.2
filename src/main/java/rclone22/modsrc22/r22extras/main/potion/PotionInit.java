package rclone22.modsrc22.r22extras.main.potion;

import rclone22.modsrc22.r22extras.api.potions.PotionUtilses;
import rclone22.modsrc22.r22extras.main.Constantr22Extras;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Constantr22Extras.MODID)
public class PotionInit
{

    public static String super_res = PotionUtilses.super_res;


    public static final List<Potion> POTIONS = new ArrayList<Potion>();

    public static final Potion SUP_RES = new SupremeResistanceClass.PotionSupremeResistance(false, 0xFF0000, 1, 1)
            .setRegistryName(Constantr22Extras.MODID, super_res)
            .setPotionName("potion."+super_res);

    static {
        POTIONS.add(SUP_RES);
    }



}
