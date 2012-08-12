package com.icode.validation.validators.base;

import com.icode.validation.iMessages;

/**
 * 
 * @author Nes
 */
public abstract class NumberValidator extends BasePropertyValidator {

    /**
     *
     * @param propertyName
     */
    public NumberValidator(String propertyName) {
        super(propertyName);
    }

    /**
     *
     * @param object
     * @param value
     * @param messages
     */
    protected abstract void doValidateNumber(Object object, Number value, iMessages messages);

    public void doValidatePropertyValue(Object object, Object propertyValue, iMessages messages) {
        if (propertyValue != null) {
            doValidateNumber(object, (Number) propertyValue, messages);
        }
    }
}
