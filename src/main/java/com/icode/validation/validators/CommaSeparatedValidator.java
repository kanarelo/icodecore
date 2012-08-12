/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.validation.validators;

import java.lang.annotation.Annotation;

import com.icode.validation.validators.annotation.Fields.CommaSeparatedField;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.base.basePatternValidator;

/**
 *
 * @author Nes
 */
@Validator(fieldAnnotation = CommaSeparatedField.class)
public class CommaSeparatedValidator extends basePatternValidator {
    private CommaSeparatedValidator(String propertyName) {
        super(propertyName);
    }

    /**
     * 
     * @param propertyName
     * @param annotation
     */
    public CommaSeparatedValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        CommaSeparatedField csfield = (CommaSeparatedField) annotation;
        this.setErrorCode(csfield.errorMessage());
        this.setPattern(csfield.match());
    }
}
