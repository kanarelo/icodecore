/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icode.validation.validators.custom;

import java.lang.annotation.Annotation;

import javax.persistence.Column;

import org.apache.commons.lang.StringUtils;

import com.icode.validation.ErrorCodes;
import com.icode.validation.MessageType;
import com.icode.validation.iMessages;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.base.BasePropertyValidator;

/**
 *
 * @author Nes
 */
@Validator(fieldAnnotation = Column.class)
public class ColumnValidator extends BasePropertyValidator {

    private int precision;
    private int length;
    private boolean nullable;
    private int scale;

    /**
     *
     */
    public ColumnValidator() {
        super("");
    }

    /**
     *
     * @param propertyName
     */
    public ColumnValidator(String propertyName) {
        super(propertyName);
        this.setErrorCode(ErrorCodes.INTEGRITY_VIOLATION);
    }

    /**
     * 
     * @param propertyName
     * @param annotation
     */
    public ColumnValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        Column column = (Column) annotation;
        this.length = column.length();
        this.nullable = column.nullable();
        this.precision = column.precision();
        this.scale = column.scale();
    }
    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.metadata.PropertyValidation#doValidatePropertyValue(java.lang.Object,
     *      nl.knowlogy.validation.errors.Errors)
     */

    public void doValidatePropertyValue(Object toValidate, Object propertyValue, iMessages errors) {
        if (propertyValue == null) {
            errors.addPropertyMessage(MessageType.ERROR, toValidate, getPropertyName(), getErrorCode());
        }

        if (propertyValue != null) {
            if (propertyValue instanceof String) {
                stringTests(toValidate, propertyValue.toString(), errors);
            }
        } else {
            errors.addPropertyMessage(MessageType.ERROR, toValidate, getPropertyName(), getErrorCode());
        }
    }

    private void stringTests(Object toValidate, String propertyValue, iMessages errors) {
        if (propertyValue != null) {
            if (StringUtils.isBlank(propertyValue) && !this.nullable ) {
                errors.addPropertyMessage(MessageType.ERROR, toValidate, getPropertyName(), getErrorCode());
            }
        } else {
            errors.addPropertyMessage(MessageType.ERROR, toValidate, getPropertyName(), ErrorCodes.ISNULL);
        }
        if ((propertyValue.length() > length)) {
            Object[] errorArg = new Object[2];
            errorArg[0] = new Long(length);
            errorArg[1] = new Long(propertyValue.length());
            errors.addPropertyMessage(MessageType.ERROR, toValidate, getPropertyName(), ErrorCodes.INVALID_MIN_LENGTH, errorArg, null);
        }
    }

    /**
     *
     * @return
     */
    @Override
	public String getDefaultErrorCode() {        
        return this.getErrorCode();
    }

    @Override
    public String toString() {
        return ColumnValidator.class.getName()+", " + super.toString();
    }
}
