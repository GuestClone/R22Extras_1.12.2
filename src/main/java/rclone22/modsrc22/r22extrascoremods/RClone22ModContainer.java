package rclone22.modsrc22.r22extrascoremods;

import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.ModMetadata;

import com.google.common.eventbus.EventBus;

import net.minecraftforge.fml.common.LoadController;

import java.util.Collections;

public class RClone22ModContainer extends DummyModContainer
{

    public RClone22ModContainer() {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = ConstR22CoreMod.mod_id;
        meta.name = ConstR22CoreMod.mod_name;
        meta.description = ConstR22CoreMod.mod_desc;
        meta.version = ConstR22CoreMod.mod_version;
        meta.authorList = Collections.singletonList(ConstR22CoreMod.mod_authors);
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }

}
