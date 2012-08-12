/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.validation.validators;

import java.lang.annotation.Annotation;

import com.icode.validation.ErrorCodes;
import com.icode.validation.iMessages;
import com.icode.validation.validators.annotation.Fields.XMLField;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.base.BasePropertyValidator;

/**
 *
 * @author Nes
 */
@Validator(fieldAnnotation = XMLField.class)
public class XMLValidator extends BasePropertyValidator {
    /**
     *
     * @param propertyName
     */
    public XMLValidator(String propertyName) {
        super(propertyName);
        this.setErrorCode(ErrorCodes.INVALID_PHONE_NUMBER);
    }

    /**
     *
     * @param propertyName
     * @param annotation
     */
    public XMLValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        XMLField urlField = (XMLField) annotation;
        setErrorCode(urlField.errorMessage());
    }

    /**
     * 
     * @return
     */
    @Override
    public String getDefaultErrorCode() {
        return ErrorCodes.INVALID_XML_MARKUP;
    }

    public void doValidatePropertyValue(Object object, Object propertyValue, iMessages errors) {
        System.out.println(this.getClass().getName());
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
