/*
 * $Header: /cvsroot/jvalidate/jvalidate-framework/jvalidate/src/main/java/nl/knowlogy/validation/validators/AllowedValuesValidator.java,v 1.7 2007/06/20 16:24:57 roberthofstra Exp $
 * $Revision: 1.7 $
 * $Date: 2007/06/20 16:24:57 $
 * 
 * 
 * Created on Oct 6, 2004
 *
 * All right reserved(c) 2004, Knowlogy
 * 
 * Copyright   2004 - 2005 Knowlogy, the Netherlands. All rights reserved. 
 * All Knowlogy brand and product names are trademarks or registered trademarks 
 * of Knowlogy in the Netherlands and other countries. 
 * 
 * No part of this publication may be reproduced, transmitted, stored in a retrieval system, 
 * or translated into any human or computer language, in any form, or by any means, electronic, 
 * mechanical, magnetic, optical, chemical, manual, or otherwise, 
 * without the prior written permission of the copyright owner, Knowlogy.
 * 
 */
package com.icode.validation.validators;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.commons.collections.FastArrayList;

import com.icode.validation.ErrorCodes;
import com.icode.validation.MessageType;
import com.icode.validation.iMessages;
import com.icode.validation.validators.annotation.Fields.AllowedValuesField;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.base.BasePropertyValidator;

/**
 * Validates if a property contains specific values.
 * Default the values are separated by ',' .
 * 
 * @author Robert
 */
@Validator(fieldAnnotation = AllowedValuesField.class)
public class AllowedValuesValidator extends BasePropertyValidator {

    private FastArrayList allowedValuesList;

    /**
     *
     * @param propertyName
     */
    public AllowedValuesValidator(String propertyName) {
        super(propertyName);
    }

    /**
     *
     * @param propertyName
     * @param annotation
     */
    public AllowedValuesValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        AllowedValuesField allowedValuesField = (AllowedValuesField) annotation;
        this.setErrorCode(allowedValuesField.errorMessage());
        this.setAllAllowedValues(allowedValuesField.allowedValues());
    }

    /**
     * @return 
     * @see nl.knowlogy.validation.validators.BasePropertyValidator#getDefaultErrorCode()
     */
    @Override
	public String getDefaultErrorCode() {
        return ErrorCodes.NOT_ALLOWED_VALUE;
    }

    /**
     * @param toValidate 
     * @param propertyValue
     * @param messages
     * @see nl.knowlogy.validation.metadata.PropertyValidation#doValidatePropertyValue(java.lang.Object,
     *      nl.knowlogy.validation.iMessages)
     */
    public void doValidatePropertyValue(Object toValidate, Object propertyValue, iMessages messages) {
        String stringValue = (String) propertyValue;
        if ((stringValue != null) && (allowedValuesList != null) && (!allowedValuesList.contains(stringValue))) {
            messages.addPropertyMessage(MessageType.ERROR, toValidate, getPropertyName(), getErrorCode());
        }
    }

    /**
     * Sets the allowed values of the property. This is a comma seperated string
     * containing the values.
     *
     * @param allAllowedValues
     */
    public final synchronized void setAllAllowedValues(String allAllowedValues) {
        StringTokenizer stringTokenizer = new StringTokenizer(allAllowedValues,  ",");
        this.allowedValuesList = new FastArrayList();
        this.allowedValuesList.setFast(false);
        while (stringTokenizer.hasMoreTokens()) {
            this.allowedValuesList.add(stringTokenizer.nextToken());
        }
        this.allowedValuesList.setFast(true);
    }

    /**
     *
     * @param allowedValues
     */
    public final synchronized void setAllAllowedValues(String[] allowedValues) {
        this.allowedValuesList = new FastArrayList();
        this.allowedValuesList.setFast(false);
        this.allowedValuesList.addAll(Arrays.asList(allowedValues));
        this.allowedValuesList.setFast(true);
    }

    private String getAllowedValuesStr() {
        String allowedValueStr = "";
        Iterator allowedValuesIt = allowedValuesList.iterator();
        if (allowedValuesIt.hasNext()) {
            allowedValueStr = (String) allowedValuesIt.next();
        }
        while (allowedValuesIt.hasNext()) {
            allowedValueStr = allowedValueStr + "," + (String) allowedValuesIt.next();
        }
        return allowedValueStr;
    }

    @Override
    public String toString() {
        return "AllowedValuesValidator, allowedValues=[" + getAllowedValuesStr() + "]" + super.toString();
    }
}
