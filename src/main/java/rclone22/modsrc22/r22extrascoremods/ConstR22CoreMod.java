package rclone22.modsrc22.r22extrascoremods;

import java.util.Arrays;

public interface ConstR22CoreMod
{
    String mc_version = "1.12.2";

    String mod_id = "r22extrascore";

    String mod_name = "R22ExtrasCore";

    String mod_version = "1.12.2-1.0";

    String mod_desc = "R22Extras CoreMods of the main mod R22Extras";

    String mod_authors = Arrays.asList("RCloneBm22/RClone22", "Examples/Example").toString();

    String coremod_name = mod_name + "Injection";

    String ModContainerClass = RClone22ModContainer.class.getName();

}
