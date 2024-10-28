package com.RClone22.r22extras.api.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EventBus
{


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface InitBus
    {
        Class<?>[] value() default {};
        String modid() default "";

    }

}
