package com.RClone22.r22extras.api.event;

import com.RClone22.r22extras.main.ConstantExt;
import com.RClone22.r22extras.main.proxy.ICommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Set;

@Mod.EventBusSubscriber(modid = ConstantExt.MODID)
public class PreInitEventRegister
{

    public PreInitEventRegister(Object instance)
    {
        PreInitEventRegister.processPreInitSubscribers(instance);
    }

    // TODO Use this for registering Init Events like living update events
    // TODO NOT FOR REGISTERING PRE INIT EVENTS

    public static void processPreInitSubscribers(Object instance) {
        // Use Reflections library to scan the classpath for classes
        Reflections reflections = new Reflections(""); // Empty string scans the entire classpath

        // Find all classes with the @AutoEventSubscriber annotation
        Set<Class<?>> annotatedClassesOne = reflections.getTypesAnnotatedWith(EventSubscribe.PreInitES.class);


        for (Class<?> clazz : annotatedClassesOne) {
            registerAnnotatedMethods(clazz);
            PreInitEventRegister.register(clazz);
        }
        PreInitEventRegister.register(instance);
    }

    private static void registerAnnotatedMethods(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(SubscribeEvent.class)) {
                try {
                    Object instance = clazz.getDeclaredConstructor().newInstance();
                    PreInitEventRegister.register(instance);
                    PreInitEventRegister.registerMethod(instance, method);
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
            PreInitEventRegister.register(instance);
        } else {
            System.err.println("Method " + method.getName() + " is not annotated with @SubscribeEvent.");
        }
    }



}
