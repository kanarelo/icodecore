/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icode.validation.validators;

import java.lang.annotation.Annotation;

import com.icode.util.Strings;
import com.icode.validation.ErrorCodes;
import com.icode.validation.validators.annotation.Fields.NameField;
import com.icode.validation.validators.annotation.Valid.Validator;

/**
 *
 * @author Nes
 */
@Validator(fieldAnnotation = NameField.class)
public class NameValidator extends TextValidator {

    /**
     * 
     * @param propertyName
     */
    public NameValidator(String propertyName) {
        super(propertyName);
    }

    /**
     *
     * @param propertyName
     * @param annotation
     */
    public NameValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        NameField nmfield = (NameField) annotation;
        this.setPattern(Strings.patterns.NAME_PATTERN);
        setErrorCode(nmfield.errorMessage());
    }

    /**
     *
     * @return
     */
    @Override
    public String getDefaultErrorCode() {
        return ErrorCodes.INVALID_NAME_TEXT;
    }
}
