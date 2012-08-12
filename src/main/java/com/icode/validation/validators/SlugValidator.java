/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.validation.validators;

import java.lang.annotation.Annotation;

import com.icode.validation.validators.annotation.Fields.SlugField;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.base.basePatternValidator;

/**
 *
 * @author Nes
 */
@Validator(fieldAnnotation = SlugField.class)
public class SlugValidator extends basePatternValidator {
    /**
     *
     * @param propertyName
     */
    private SlugValidator(String propertyName) {
        super(propertyName);
    }

    /**
     * 
     * @param propertyName
     * @param annotation
     */
    public SlugValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        SlugField slgField = (SlugField) annotation;
        this.setErrorCode(slgField.errorMessage());
        this.setPattern(slgField.match()); 
    }
}
