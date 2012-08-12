/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icode.validation.validators.annotation.Fields;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.icode.util.Strings;
import com.icode.validation.ErrorCodes;

/**
 *
 * @author Nes
 */
@Target(value = {ElementType.METHOD, ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface IPField {
    /**
     * 
     * @return
     */
    public String pattern() default Strings.patterns.IPV4_PATTERN;

    /**
     *
     * @return
     */
    public String errorMessage() default ErrorCodes.INVALID_IP_ADDRESS;
}
