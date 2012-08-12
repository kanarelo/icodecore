/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.validation.validators;

import java.lang.annotation.Annotation;

import com.icode.validation.ErrorCodes;
import com.icode.validation.validators.annotation.Fields.TimeField;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.base.basePatternValidator;

/**
 *
 * @author Nes
 */
@Validator(fieldAnnotation = TimeField.class)
public class TimeValidator extends basePatternValidator {
    /**
     *
     * @param propertyName
     */
    public TimeValidator(String propertyName) {
        super(propertyName);
        this.setErrorCode(ErrorCodes.INVALID_TIME_TEXT);
    }

    /**
     * 
     * @param propertyName
     * @param annotation
     */
    public TimeValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        TimeField timeField = (TimeField) annotation;
        setErrorCode(timeField.errorMessage());
    }
}
