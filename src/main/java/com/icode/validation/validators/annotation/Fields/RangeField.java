package com.icode.validation.validators.annotation.Fields;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.icode.validation.ErrorCodes;

/**
 *
 * @author Nes
 */
@Retention(RUNTIME)
@Target({ElementType.METHOD})
public @interface RangeField {
    /**
     *
     * @return
     */
    public long min();
    /**
     *
     * @return
     */
    public long max();
    /**
     *
     * @return
     */
    public String errorMessage()  default ErrorCodes.INVALID_RANGE;
}
