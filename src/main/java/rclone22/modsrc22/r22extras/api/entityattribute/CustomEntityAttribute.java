package rclone22.modsrc22.r22extras.api.entityattribute;


import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

import java.util.UUID;

public class CustomEntityAttribute
{

    public static final String SUP_RES_ATTR_NAME = "mod.sup_res_attr";


    public static final UUID SUP_RES_ATTR_UUID = UUID.fromString("2b438da8-fcc6-4123-864a-6559fe98c3d7"); // Generate a unique UUID
    public static final IAttribute SUP_RES_ATTR =
            (new RangedAttribute(null,
                    CustomEntityAttribute.SUP_RES_ATTR_NAME,
                    1.0D,
                    0.0D,
                    Double.MAX_VALUE)
            ).setDescription("Super Resistance Attribute").setShouldWatch(true);





}

