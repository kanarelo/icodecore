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
public @interface SlugField {
    /**
     * 
     * @return
     */
    public String  errorMessage() default ErrorCodes.INVALID_SLUG_TEXT;

    public String match() default Strings.patterns.SLUG_PATTERN;
}
