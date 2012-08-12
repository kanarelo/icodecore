/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.validation.validators;

import java.lang.annotation.Annotation;

import com.icode.validation.ErrorCodes;
import com.icode.validation.validators.annotation.Fields.TextField;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.annotation.enums.TextType;
import com.icode.validation.validators.base.basePatternValidator;

/**
 *
 * @author Nes
 */
@Validator(fieldAnnotation = TextField.class)
public class TextValidator extends basePatternValidator {
    private TextType textType;
    /**
     *
     * @param propertyName
     */
    public TextValidator(String propertyName) {
        super(propertyName);
        this.setErrorCode(ErrorCodes.INVALID_TEXT);
    }

    /**
     * 
     * @param propertyName
     * @param annotation
     */
    public TextValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        TextField txtField = (TextField) annotation;
        setErrorCode(txtField.errorMessage());
        setTextType(txtField.Validation());
    }

    private void setTextType(TextType textType) {
        this.textType = textType;
    }
}
