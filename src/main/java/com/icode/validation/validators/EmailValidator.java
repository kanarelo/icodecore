/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icode.validation.validators;

import java.lang.annotation.Annotation;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.icode.validation.ErrorCodes;
import com.icode.validation.MessageType;
import com.icode.validation.iMessages;
import com.icode.validation.validators.annotation.Fields.EmailField;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.base.BasePropertyValidator;

/**
 *
 * @author Nes
 */
@Validator(fieldAnnotation = EmailField.class)
public class EmailValidator extends BasePropertyValidator {
    /**
     *
     * @param propertyName
     */
    public EmailValidator(String propertyName) {
        super(propertyName);
    }

    /**
     * 
     * @param propertyName
     * @param annotation
     */
    public EmailValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        EmailField emailField = (EmailField) annotation;
        setErrorCode(emailField.errorMessage());
    }

    @Override
    public String toString() {
        return EmailValidator.class.getName()+", " + super.toString();
    }

    /**
     *
     * @return
     */
    @Override
    public String getDefaultErrorCode() {
        return ErrorCodes.INVALID_EMAIL_ADDRESS;
    }

    public void doValidatePropertyValue(Object toValidate, Object propertyValue, iMessages errors) {
        //System.out.println(this.getClass().getName());
        if (propertyValue != null) {
            if (!org.apache.commons.validator.EmailValidator.getInstance().isValid((String) propertyValue)) {
                errors.addPropertyMessage(MessageType.ERROR, toValidate, getPropertyName(), getErrorCode());
            }
        } else {
            if (logger.isLoggable(Level.CONFIG)) {
                logger.log(Level.WARNING, "Skipping validation propertyvalue == null for {0}", this);
            }
        }
    }
    private static final Logger logger = Logger.getLogger(EmailValidator.class.getName());
}
