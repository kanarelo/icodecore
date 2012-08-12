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
import com.icode.validation.validators.annotation.Fields.CreditCardField;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.annotation.enums.CreditCardType;
import com.icode.validation.validators.base.BasePropertyValidator;

/**
 *
 * @author Nes
 */
@Validator(fieldAnnotation = CreditCardField.class)
public class CreditCardValidator extends BasePropertyValidator {

    private int creditCardIntVal;
    // TODO: Warning - Am not really sure that this validation is final check mayne... weird

    /**
     *
     * @param propertyName
     */
    public CreditCardValidator(String propertyName) {
        super(propertyName);
    }

    /**
     *
     * @param propertyName
     * @param annotation
     */
    public CreditCardValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        CreditCardField validateCD = (CreditCardField) annotation;
        CreditCardType creditCardType = validateCD.CreditCard();
        int type = 0;
        if (creditCardType == CreditCardType.AMEX) {
            type = org.apache.commons.validator.CreditCardValidator.AMEX;
        } else if (creditCardType == CreditCardType.DISCOVER) {
            type = org.apache.commons.validator.CreditCardValidator.DISCOVER;
        } else if (creditCardType == CreditCardType.MASTERCARD) {
            type = org.apache.commons.validator.CreditCardValidator.MASTERCARD;
        } else if (creditCardType == CreditCardType.VISA) {
            type = org.apache.commons.validator.CreditCardValidator.VISA;
        } else if (creditCardType == CreditCardType.NONE) {
            type = org.apache.commons.validator.CreditCardValidator.NONE;
        }
        setCreditCardType(type);
    }

    private void setCreditCardType(int creditCardIntVal) {
        this.creditCardIntVal = creditCardIntVal;
    }

    @Override
    public String toString() {
        return "CreditCardValidator," + super.toString();
    }

    /**
     * 
     * @return
     */
    @Override
    public String getDefaultErrorCode() {
        return ErrorCodes.INVALID_CREDITCARDNUMBER;
    }
    private static final org.apache.commons.validator.CreditCardValidator validator = new org.apache.commons.validator.CreditCardValidator();

    public void doValidatePropertyValue(Object toValidate, Object propertyValue, iMessages errors) {
        if (propertyValue != null) {
            String stringValue = (String) propertyValue;
            validator.addAllowedCardType(null);
            if (!validator.isValid(stringValue)) {
                errors.addPropertyMessage(MessageType.ERROR, toValidate, getPropertyName(), getErrorCode());
            }
        } else {
            if (logger.isLoggable(Level.CONFIG)) {
                logger.info("Skipping validation propertyvalue == null for " + this);
            }
        }
    }
    private static final Logger logger = Logger.getLogger(CreditCardValidator.class.getName());
}
