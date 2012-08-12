/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icode.validation.validators;

import java.lang.annotation.Annotation;

import com.icode.validation.validators.annotation.Fields.URLField;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.base.basePatternValidator;

/**
 *
 * @author Nes
 */
@Validator(fieldAnnotation = URLField.class)
public class URLValidator extends basePatternValidator {

    /**
     *
     * @param propertyName
     */
    private URLValidator(String propertyName) {
        super(propertyName);
    }

    /**
     * 
     * @param propertyName
     * @param annotation
     */
    public URLValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        URLField urlField = (URLField) annotation;
        this.setErrorCode(urlField.errorMessage());
        this.setPattern(urlField.match());
    }
}
