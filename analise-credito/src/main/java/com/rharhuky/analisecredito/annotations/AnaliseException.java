package com.rharhuky.analisecredito.annotations;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnaliseException {

    String value() default "";

}
