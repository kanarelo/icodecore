/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.validation.validators.base;

import gnu.regexp.RE;
import gnu.regexp.REException;
import gnu.regexp.REMatch;

import org.apache.commons.lang.StringUtils;

import com.icode.validation.ErrorCodes;
import com.icode.validation.MessageType;
import com.icode.validation.iMessageImpl;
import com.icode.validation.iMessages;
import com.icode.validation.validators.annotation.Valid.Validator;
/**
 *
 * @author Nes
 */
public abstract class basePatternValidator extends BasePropertyValidator {
    private String pattern;

    /**
     * 
     * @param propertyName
     */
    public basePatternValidator(String propertyName) {
        super(propertyName);
    }
    /**
     *
     * @return
     */
    @Override
	public String getDefaultErrorCode() {
        return ErrorCodes.INVALID_FORMAT;
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.metadata.PropertyValidation#doValidatePropertyValue(java.lang.Object,
     *      nl.knowlogy.validation.errors.Errors)
     */
    public void doValidatePropertyValue(Object toValidate, Object propertyValue, iMessages errors) {
        if (propertyValue != null) {
            String stringValue = propertyValue.toString();
            if (StringUtils.isNotBlank(stringValue)) {
                if (pattern != null) {
                    try {
                        RE re = new RE(pattern);
                        REMatch match = re.getMatch(stringValue);

                        if (match == null) {
                            iMessageImpl mess1 = new iMessageImpl(MessageType.ERROR, toValidate, getPropertyName(), getErrorCode());
                            mess1.setMessage("\"The Data entered on field '"+this.getPropertyName()+"' >> '"+propertyValue+"' is not valid.\"");
                            errors.addMessage(mess1);
                        }
                    } catch (REException re) {
                        throw new IllegalStateException(re.getMessage());
                    }
                }else{
                    iMessageImpl mess1 = new iMessageImpl(MessageType.ERROR, toValidate, getPropertyName(), getErrorCode());
                    mess1.setMessage("The required Pattern is either not defined or is missing on the AnnotationType: "+this.getClass().getAnnotation(Validator.class).fieldAnnotation());
                    errors.addMessage(mess1);
                }
            }
        }
    }

    /**
     * @return Returns the regularExpression.
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * @param regularExpression
     *            The regularExpression to set.
     */
    public final void setPattern(String regularExpression) {
        this.pattern = regularExpression;
    }
    private static final String myName = "PatternValidator";

    @Override
    public String toString() {
        return this.getClass().getName() + "," + super.toString();
    }

}
