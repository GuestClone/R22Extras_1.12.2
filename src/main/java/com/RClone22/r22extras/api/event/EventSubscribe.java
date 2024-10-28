package com.RClone22.r22extras.api.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EventSubscribe
{
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface PreInitES
    {
        Class<?>[] clazz() default {};

        String modid() default "";


    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface InitES
    {
        Class<?>[] clazz() default {};

        String modid() default "";


    }
}
