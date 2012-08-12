/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.validation.validators.annotation.Fields;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.icode.validation.ErrorCodes;
import com.icode.validation.validators.annotation.enums.TextType;

/**
 *
 * @author Nes
 */
@Target(value = {ElementType.METHOD, ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface TextField {
    /**
     * 
     * @return
     */
    public TextType Validation() default TextType.ANY;
    /**
     *
     * @return
     */
    public String  errorMessage() default ErrorCodes.INVALID_TEXT;
}
