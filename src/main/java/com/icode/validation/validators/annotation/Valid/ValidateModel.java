package com.icode.validation.validators.annotation.Valid;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * @author Nes
 */
@Inherited
@Target(value = {ElementType.TYPE})
@Retention(RUNTIME)
public @interface ValidateModel {
}
