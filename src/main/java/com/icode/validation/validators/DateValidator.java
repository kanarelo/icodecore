/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icode.validation.validators;

import java.lang.annotation.Annotation;

import com.icode.validation.ErrorCodes;
import com.icode.validation.validators.annotation.Fields.DateField;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.base.basePatternValidator;

/**
 *
 * @author Nes
 */
@Validator(fieldAnnotation = DateField.class)
public class DateValidator extends basePatternValidator {

    private boolean autonow;

    private DateValidator(String propertyName) {
        super(propertyName);
        this.setErrorCode(ErrorCodes.INVALID_DATE_TEXT);
    }

    /**
     * 
     * @param propertyName
     * @param annotation
     */
    public DateValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        DateField datefield = (DateField) annotation;
        this.setPattern(datefield.match());
        setErrorCode(datefield.errorMessage());
        this.autonow = datefield.AutoNow();
    }
}
