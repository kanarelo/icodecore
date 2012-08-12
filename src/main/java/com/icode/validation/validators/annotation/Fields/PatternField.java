/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.validation.validators.annotation.Fields;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 ** @author Nes
 **/

@Target(value = {ElementType.METHOD, ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface PatternField {
    /**
     * 
     * @return
     */
    public String pattern() default "";
    /**
     *
     * @return
     */
    public String errorMessage() default "The Entered Data value is not Valid!";
}
