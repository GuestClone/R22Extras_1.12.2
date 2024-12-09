package rclone22.modsrc22.r22extrasmixin.mixins.modded;

import com.hbm.util.ArmorRegistry;

public class HazardClassEnums
{

    public static final ArmorRegistry.HazardClass[] ALL_HAZARDS = new ArmorRegistry.HazardClass[] {
            ArmorRegistry.HazardClass.GAS_CHLORINE,
            ArmorRegistry.HazardClass.GAS_MONOXIDE,
            ArmorRegistry.HazardClass.GAS_INERT,
            ArmorRegistry.HazardClass.PARTICLE_COARSE,
            ArmorRegistry.HazardClass.PARTICLE_FINE,
            ArmorRegistry.HazardClass.BACTERIA,
            ArmorRegistry.HazardClass.NERVE_AGENT,
            ArmorRegistry.HazardClass.GAS_CORROSIVE,
            ArmorRegistry.HazardClass.SAND,
            ArmorRegistry.HazardClass.LIGHT,
            ArmorRegistry.HazardClass.RAD_GAS
    };

    public static final ArmorRegistry.HazardClass[] ALL_HAZARDS_NO_LIGHTSAND = new ArmorRegistry.HazardClass[] {
            ArmorRegistry.HazardClass.GAS_CHLORINE,
            ArmorRegistry.HazardClass.GAS_MONOXIDE,
            ArmorRegistry.HazardClass.GAS_INERT,
            ArmorRegistry.HazardClass.PARTICLE_COARSE,
            ArmorRegistry.HazardClass.PARTICLE_FINE,
            ArmorRegistry.HazardClass.BACTERIA,
            ArmorRegistry.HazardClass.NERVE_AGENT,
            ArmorRegistry.HazardClass.GAS_CORROSIVE,
            ArmorRegistry.HazardClass.RAD_GAS
    };

    public static final ArmorRegistry.HazardClass[] ALL_HAZARDS_NO_RADGAS = new ArmorRegistry.HazardClass[] {
            ArmorRegistry.HazardClass.GAS_CHLORINE,
            ArmorRegistry.HazardClass.GAS_MONOXIDE,
            ArmorRegistry.HazardClass.GAS_INERT,
            ArmorRegistry.HazardClass.PARTICLE_COARSE,
            ArmorRegistry.HazardClass.PARTICLE_FINE,
            ArmorRegistry.HazardClass.BACTERIA,
            ArmorRegistry.HazardClass.NERVE_AGENT,
            ArmorRegistry.HazardClass.GAS_CORROSIVE,
            ArmorRegistry.HazardClass.SAND,
            ArmorRegistry.HazardClass.LIGHT,
    };

    public static final ArmorRegistry.HazardClass[] ALL_HAZARD_GAS = new ArmorRegistry.HazardClass[] {
            ArmorRegistry.HazardClass.GAS_CHLORINE,
            ArmorRegistry.HazardClass.GAS_MONOXIDE,
            ArmorRegistry.HazardClass.GAS_INERT,
            ArmorRegistry.HazardClass.RAD_GAS
    };

    public static final ArmorRegistry.HazardClass[] ALL_HAZARD_NO_RADGAS = new ArmorRegistry.HazardClass[] {
            ArmorRegistry.HazardClass.GAS_CHLORINE,
            ArmorRegistry.HazardClass.GAS_MONOXIDE,
            ArmorRegistry.HazardClass.GAS_INERT,
    };

    public static final ArmorRegistry.HazardClass[] ALL_HAZARD_DUST = new ArmorRegistry.HazardClass[] {
            ArmorRegistry.HazardClass.PARTICLE_COARSE,
            ArmorRegistry.HazardClass.PARTICLE_FINE,
            ArmorRegistry.HazardClass.GAS_MONOXIDE, // Monoxide for nether coal gasses
    };

    public static final ArmorRegistry.HazardClass[] ALL_HAZARD_PARTICULATES = new ArmorRegistry.HazardClass[] {
            ArmorRegistry.HazardClass.BACTERIA,
            ArmorRegistry.HazardClass.NERVE_AGENT,
            ArmorRegistry.HazardClass.GAS_CORROSIVE,
    };

    public static final ArmorRegistry.HazardClass[] ALL_HAZARD_LIGHTSAND = new ArmorRegistry.HazardClass[] {
            ArmorRegistry.HazardClass.LIGHT,
            ArmorRegistry.HazardClass.SAND,
    };

    public static final ArmorRegistry.HazardClass[] ALL_HAZARD_LIGHTSAND_DUST = new ArmorRegistry.HazardClass[] {
            ArmorRegistry.HazardClass.LIGHT,
            ArmorRegistry.HazardClass.SAND,
            ArmorRegistry.HazardClass.PARTICLE_COARSE,
            ArmorRegistry.HazardClass.PARTICLE_FINE,
            ArmorRegistry.HazardClass.GAS_MONOXIDE,
    };

    public static final ArmorRegistry.HazardClass[] MASK_PISS_HAZARDS = new ArmorRegistry.HazardClass[] {
            ArmorRegistry.HazardClass.PARTICLE_COARSE,
            ArmorRegistry.HazardClass.PARTICLE_FINE,
            ArmorRegistry.HazardClass.GAS_MONOXIDE,
            ArmorRegistry.HazardClass.GAS_CHLORINE,
    };

    public static final ArmorRegistry.HazardClass[] MASK_FILTER_COMBO = new ArmorRegistry.HazardClass[] {
            ArmorRegistry.HazardClass.GAS_CHLORINE,
            ArmorRegistry.HazardClass.GAS_MONOXIDE,
            ArmorRegistry.HazardClass.GAS_INERT,
            ArmorRegistry.HazardClass.PARTICLE_COARSE,
            ArmorRegistry.HazardClass.PARTICLE_FINE,
            ArmorRegistry.HazardClass.BACTERIA,
            ArmorRegistry.HazardClass.NERVE_AGENT,


    };

    public static final ArmorRegistry.HazardClass[] MASK_ATTACHMENT = new ArmorRegistry.HazardClass[] {
            ArmorRegistry.HazardClass.PARTICLE_COARSE,
            ArmorRegistry.HazardClass.PARTICLE_FINE,
            ArmorRegistry.HazardClass.GAS_MONOXIDE, // Monoxide for nether coal gasses
            ArmorRegistry.HazardClass.LIGHT,
            ArmorRegistry.HazardClass.SAND,
    };


}
