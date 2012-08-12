/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.validation.validators;

import java.lang.annotation.Annotation;

import com.icode.validation.validators.annotation.Fields.PhoneField;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.base.basePatternValidator;

/**
 *
 * @author Nes
 */
@Validator(fieldAnnotation = PhoneField.class)
public class PhoneValidator extends basePatternValidator {
    /**
     * 
     * @param propertyName
     */
    public PhoneValidator(String propertyName) {
        super(propertyName);
    }

    /**
     *
     * @param propertyName
     * @param annotation
     */
    public PhoneValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        PhoneField phField = (PhoneField) annotation;
        setErrorCode(phField.errorMessage());
        this.setPattern(phField.match());
    }
}
