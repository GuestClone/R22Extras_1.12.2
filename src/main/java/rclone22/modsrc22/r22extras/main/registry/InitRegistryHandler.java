package rclone22.modsrc22.r22extras.main.registry;



import rclone22.modsrc22.r22extras.main.event.initevents.InitEventsMinecraft;
import rclone22.modsrc22.r22extras.main.event.initevents.InitItemEvents;
import rclone22.modsrc22.r22extras.main.event.initevents.InitItemToolTip;
import rclone22.modsrc22.r22extras.main.oredict.OreDict;
import rclone22.modsrc22.r22extras.main.proxy.ICommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.Arrays;
import java.util.List;


public class InitRegistryHandler
{

    public InitRegistryHandler (FMLInitializationEvent event)
    {
        Object[] eventHandlers = createEventHandlers();

        IInitEvents.registerInitHandlers(event,
                this,
                IInitEvents.class,
                InitRegistryHandler.class,
                eventHandlers
        );

        OreDict.registerOreDictionaryEntries();

    }

    private Object[] createEventHandlers() {
        return new Object[]{
                new InitEventsMinecraft(),
                new InitItemEvents(),
                new InitItemToolTip()
        };
    }




    public interface IInitEvents
    {

        default void init(FMLInitializationEvent event)
        {
            IInitEvents.registerInitHandlers(this, IInitEvents.class);
        }



        static void registerInitHandlers(Object... handlers) {
            for (Object handler : handlers) {
                MinecraftForge.EVENT_BUS.register(handler);
            }
            // Additionally, you can use the event object if you need to register it
            MinecraftForge.EVENT_BUS.register(handlers);
        }

    }


}
