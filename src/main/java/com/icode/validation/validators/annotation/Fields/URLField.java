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
import com.icode.validation.validators.annotation.enums.URLType;

/**
 *
 * @author Nes
 */
@Target(value = {ElementType.METHOD, ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface URLField {

    /**
     *
     * @return
     */
    public URLType URLAddressType() default URLType.HTTP;

    /**
     * 
     * @return
     */
    public boolean VerifyExists() default false;

    /**
     * @return
     */
    public String errorMessage() default ErrorCodes.INVALID_URL_ADDRESS;

    public String match() default Strings.patterns.URL_PATTERN;
}
