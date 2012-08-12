package com.icode.validation.validators;

import java.lang.annotation.Annotation;

import com.icode.validation.ErrorCodes;
import com.icode.validation.MessageType;
import com.icode.validation.iMessages;
import com.icode.validation.validators.annotation.Fields.RangeField;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.base.NumberValidator;

/**
 *
 * @author Nes
 */
@Validator(fieldAnnotation = RangeField.class)
public class RangeValidator extends NumberValidator {

    private Long minSize = 0L;
    private Long maxSize = 0L;

    /**
     *  
     * @param propertyName
     */
    public RangeValidator(String propertyName) {
        super(propertyName);
    }

    /**
     *
     * @param propertyName
     * @param annotation
     */
    public RangeValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        RangeField rangeField = (RangeField) annotation;
        this.maxSize = rangeField.max();
        this.minSize = rangeField.min();
        this.setErrorCode(propertyName);
    }

    /*F
     * (non-Javadoc)
     * 
     * @see nl.knowlogy.validation.validators.BasePropertyValidator#getDefaultErrorCode()
     */
    /**
     *
     * @return
     */
    @Override
	public String getDefaultErrorCode() {
        return ErrorCodes.INVALID_RANGE;
    }

    private Object[] createMessageArg(long actualValue) {
        Object[] messageArg = new Object[3];
        messageArg[0] = new Long(minSize.longValue());
        messageArg[1] = new Long(maxSize.longValue());
        messageArg[2] = new Long(actualValue);
        return messageArg;
    }
    /*
     * (non-Javadoc)
     * 
     * @see nl.knowlogy.validation.metadata.PropertyValidation#doValidatePropertyValue(java.lang.Object,
     *      nl.knowlogy.validation.errors.Errors)
     */

    /**
     * 
     * @param toValidate
     * @param value
     * @param messages
     */
    @Override
	protected void doValidateNumber(Object toValidate, Number value, iMessages messages) {
        if ((value.longValue() < minSize.longValue() || value.longValue() > maxSize.longValue())) {
            Object[] errorArg = createMessageArg(value.longValue());
            messages.addPropertyMessage(MessageType.ERROR, toValidate, getPropertyName(), getErrorCode(), errorArg, null);
        }
    }

    @Override
    public String toString() {
        return "MaxSizeValidator, maxSize=[" + maxSize + "]" + super.toString();
    }
}
