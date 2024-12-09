package rclone22.modsrc22.r22extrascoremods;


import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion(ConstR22CoreMod.mc_version)
@IFMLLoadingPlugin.Name(ConstR22CoreMod.coremod_name)
@IFMLLoadingPlugin.SortingIndex(1111) // not 1000
@IFMLLoadingPlugin.TransformerExclusions(value =
        {
                "rclone22.modsrc22.r22extrascoremods",
        }
)
public class RClone22Coremod implements IFMLLoadingPlugin {

    public RClone22Coremod()
    {

    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[] {
            ""
        };

    }

    @Override
    public String getModContainerClass() {
        return ConstR22CoreMod.ModContainerClass;
    }

    @Override
    public String getSetupClass() {
        return null;
    }


    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }


}