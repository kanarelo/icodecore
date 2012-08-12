/*
 * $Header: /cvsroot/jvalidate/jvalidate-framework/jvalidate/src/main/java/nl/knowlogy/validation/validators/IsValidValidator.java,v 1.8 2007/06/20 16:24:57 roberthofstra Exp $
 * $Revision: 1.8 $
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
package com.icode.validation;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Iterator;

import org.hibernate.classic.Validatable;

import com.icode.validation.validators.annotation.Valid.ValidateIsValid;
import com.icode.validation.validators.base.BasePropertyValidator;

/**
 * <p>
 * The <code>IsValidValidator</code> validates properties which are not
 * returning default types (like Integer, Long, String etc) but user defined classes or
 * arrays/collections of user defined classes.
 * </p>
 * <p>
 * These instances are validated, if exists, with the use of the <code>Validatable</code>
 * interface, otherwise with <code>ValidationEngine<code> instance.
 * </p>
 * 
 * @see ValidationEngine
 * @see Validatable
 * 
 * @author Robert
 */

public class IsValidValidator extends BasePropertyValidator {

    /**
     *
     * @param propertyName
     */
    public IsValidValidator(String propertyName) {
        super(propertyName);
    }

    /**
     *
     * @param propertyName
     * @param annotation
     */
    public IsValidValidator(String propertyName, Annotation annotation) {
        this(propertyName);
        ValidateIsValid validateIsValid = (ValidateIsValid) annotation;
        setErrorCode(validateIsValid.errorMessage());
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.validators.BasePropertyValidator#getDefaultErrorCode()
     */
    @Override
	public String getDefaultErrorCode() {
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.metadata.PropertyValidation#doValidatePropertyValue(java.lang.Object,
     *      nl.knowlogy.validation.errors.Errors)
     */
    public void doValidatePropertyValue(Object toValidate, Object propertyValue, iMessages messages) {
        if ((propertyValue != null) && (propertyValue instanceof Collection)) {
            Collection collection = (Collection) propertyValue;
            Iterator collectionIterator = collection.iterator();
            while (collectionIterator.hasNext()) {
                Object obj = collectionIterator.next();
                ValidationEngine.validate(obj, messages);
            }
        } else if ((propertyValue != null) && (propertyValue instanceof Object[])) {
            Object[] objectArray = (Object[]) propertyValue;
            for (int i = 0; i < objectArray.length; i++) {
                Object object = objectArray[i];
                ValidationEngine.validate(object, messages);
            }
        } else if (propertyValue != null) {
            ValidationEngine.validate(propertyValue, messages);
        }

    }

    /**
     *
     * @param toValidate
     * @param propertyValue
     * @param group
     * @param messages
     */
    public void doValidatePropertyValue(Object toValidate, Object propertyValue, String group,
            iMessages messages) {
        if ((propertyValue != null) && (propertyValue instanceof Collection)) {
            Collection collection = (Collection) propertyValue;
            Iterator collectionIterator = collection.iterator();
            while (collectionIterator.hasNext()) {
                Object object = collectionIterator.next();
                ValidationEngine.validateGroup(object, group, messages);
            }
        } else if ((propertyValue != null) && (propertyValue instanceof Object[])) {
            Object[] objectArray = (Object[]) propertyValue;
            for (int i = 0; i < objectArray.length; i++) {
                Object object = objectArray[i];
                ValidationEngine.validateGroup(object, group, messages);
            }
        } else if (propertyValue != null) {
            ValidationEngine.validateGroup(propertyValue, group, messages);
        }
    }

    @Override
    public String toString() {
        return "IsValidValidator, " + super.toString();
    }
}
