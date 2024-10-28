package com.RClone22.r22extras.api.event;

import com.RClone22.r22extras.main.ConstantExt;
import com.RClone22.r22extras.main.registry.PreInitRegistryHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.lang.reflect.Method;
import java.util.Set;

@Mod.EventBusSubscriber(modid = ConstantExt.MODID)
public class EventRegister
{


    public static final EventRegister.InitEventRegister INIT_EVENT_EVENT_REGISTER = new EventRegister.InitEventRegister(MinecraftForge.EVENT_BUS);

    @Mod.EventBusSubscriber(modid = ConstantExt.MODID)
    public static class InitEventRegister
    {

        public InitEventRegister(Object instance)
        {
            EventRegister.InitEventRegister.processInitSubscribers(instance);
            MixinBootstrap.init();
            Mixins.addConfiguration("mixins."+ ConstantExt.MODID+".json");
        }

        public static void processInitSubscribers(Object instance) {
            Reflections reflections = new Reflections(new ConfigurationBuilder()
                    .setUrls(ClasspathHelper.forPackage("")) // Use forPackage to scan the entire classpath
                    .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner()));


            Set<Class<?>> annotatedClassesOne = reflections.getTypesAnnotatedWith(EventBus.InitBus.class);

            for (Class<?> clazz : annotatedClassesOne) {
                registerAnnotatedMethods(clazz);
                EventRegister.InitEventRegister.register(clazz);
            }


           EventRegister.InitEventRegister.register(instance);
        }



        private static void registerAnnotatedMethods(Class<?> clazz) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(SubscribeEvent.class)) {
                    try {
                        Object instance = clazz.getDeclaredConstructor().newInstance();
                        EventRegister.InitEventRegister.register(instance);
                        EventRegister.InitEventRegister.registerMethod(instance, method);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Method to allow manual registration of instances
        public static void register(Object instance) {
            MinecraftForge.EVENT_BUS.register(instance);
        }

        // Method to allow manual registration of event methods
        public static void registerMethod(Object instance, Method method) {
            if (method.isAnnotationPresent(SubscribeEvent.class)) {
                EventRegister.InitEventRegister.register(instance);
            } else {
                System.err.println("Method " + method.getName() + " is not annotated with @SubscribeEvent.");
            }
        }
    }

    //
    //
    //


}
