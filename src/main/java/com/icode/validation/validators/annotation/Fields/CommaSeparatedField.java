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
import com.icode.validation.validators.annotation.enums.Groups;

/**
 *
 * @author Nes
 */
@Target(value = {ElementType.METHOD, ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CommaSeparatedField {
    /**
     *
     * @return
     */
    public String match() default Strings.patterns.COMMA_SEPARATED_PATTERN;
    /**
     * 
     * @return
     */
    public Groups  grouping() default  Groups.THREE;
    /**
     *
     * @return
     */
    public String  errorMessage() default ErrorCodes.INVALID_COMMA_SEPARATED_NUMBER;
}
