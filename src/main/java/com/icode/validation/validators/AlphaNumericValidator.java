/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.validation.validators;

import java.lang.annotation.Annotation;

import com.icode.util.Strings;
import com.icode.validation.ErrorCodes;
import com.icode.validation.validators.annotation.Fields.AlphaNumericField;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.base.basePatternValidator;

/**
 *
 * @author Nes
 */
@Validator(fieldAnnotation = AlphaNumericField.class)
public class AlphaNumericValidator extends basePatternValidator {
    private AlphaNumericValidator(String propertyName) {
        super(propertyName);
        this.setPattern(Strings.patterns.ALPHA_NUMERIC_PATTERN);
        this.setErrorCode(ErrorCodes.INVALID_ALPHANUMERIC_TEXT);
    }

    /**
     * 
     * @param propertyName
     * @param annotation
     */
    public AlphaNumericValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        AlphaNumericField alpNField = (AlphaNumericField) annotation;
        setErrorCode(alpNField.errorMessage());
    }
}