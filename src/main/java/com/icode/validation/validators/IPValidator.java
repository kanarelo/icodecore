/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.validation.validators;

import java.lang.annotation.Annotation;

import com.icode.validation.validators.annotation.Fields.IPField;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.base.basePatternValidator;

/*
 *
 * @author Nes
 */
/**
 *
 * @author Nes
 */
@Validator(fieldAnnotation = IPField.class)
public class IPValidator extends basePatternValidator {
    /**
     * 
     * @param propertyName
     * @param annotation
     */
    public IPValidator(String propertyName, Annotation annotation) {
        super(propertyName);
        IPField validateIP = (IPField) annotation;
        setPattern(validateIP.pattern());
        this.setErrorCode(validateIP.errorMessage());
    }
}