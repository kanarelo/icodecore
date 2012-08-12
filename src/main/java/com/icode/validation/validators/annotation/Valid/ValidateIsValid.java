package com.icode.validation.validators.annotation.Valid;

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
public @interface ValidateIsValid {
    /**
     * 
     * @return
     */
    String errorMessage() default "";//ErrorCodes.NOT_ALLOWED_VALUE;
}
