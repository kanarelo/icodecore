package com.icode.validation.validators.annotation.Fields;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 *
 * @author Nes
 */
@Retention(RUNTIME)
@Target({ElementType.METHOD})
public @interface AllowedValuesField {
    /**
     * 
     * @return
     */
    String[] allowedValues();
    /**
     *
     * @return
     */
    String errorMessage() default "";
}
