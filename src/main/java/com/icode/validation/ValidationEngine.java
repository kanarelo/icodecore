/*
 * $Header: /cvsroot/jvalidate/jvalidate-framework/jvalidate/src/main/java/nl/knowlogy/validation/ValidationEngine.java,v 1.4 2007/10/31 17:00:52 roberthofstra Exp $
 * $Revision: 1.4 $
 * $Date: 2007/10/31 17:00:52 $
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

import java.util.logging.Level;

import org.apache.commons.collections.FastHashMap;

import com.icode.validation.validators.metadata.ClassMetadata;

/**
 * The ValidationEngine class is responsible for validating 
 * objects.
 * 
 * 
 * @author <a href="mailto:robert.hofstra@knowlogy.nl">Robert Hofstra, Knowlogy</a>
 * 
 */
public class ValidationEngine {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ValidationEngine.class.getName());
    private static final ValidationEngine validationEngine = new ValidationEngine();
    private FastHashMap validatorsMap;
    private iValidatorSupplier validatorSupplier;

    /**
     *
     * @return
     */
    public static ValidationEngine getInstance() {
        return validationEngine;
    }

    static {
        try {
            // trick to load, if existing, the AnnotatedClassesValidatorFactory, so it's
            // registers itself as a validatorsupplier
            Class.forName(AnnotatedClassesValidatorFactory.class.getName());
            logger.info("Loaded the AnnotatedClassesValidatorFactory");
        } catch (Exception ex) {
            logger.info("Not loaded the AnnotatedClassesValidatorFactory");
        }
    }

    /**
     *
     * @param object
     * @param messages
     */
    public static void validate(Object object, iMessages messages) {
        getInstance().doValidate(object, messages);
    }

    /**
     * Validates if a value is valid for the property of a javabean from clazz.
     * 
     * This is for validation a value without setting it actualy.
     * 
     * @param clazz of the javabean for which the value must be validated
     * @param propertyName name of the property
     * @param value value 
     * @param messages a messages container to which properties are added.
     */
    public static void validateProperty(Class clazz, String propertyName, Object value, iMessages messages) {
        getInstance().doValidateProperty(clazz, propertyName, value, messages);
    }

    /**
     *
     * @param object
     * @param groupName
     * @param messages
     */
    public static void validateGroup(Object object, String groupName, iMessages messages) {
        getInstance().doValidateGroup(object, groupName, messages);
    }

    /**
     *
     * @param object
     */
    public static void validate(Object object) {
        getInstance().doValidate(object);
    }

    /**
     *
     * @param clazz
     * @return
     */
    public static iModelValidator getModelValidator(Class clazz) {
        return getInstance().doGetValidator(clazz);
    }

    /**
     *
     * @param modelValidator
     */
    public static void registerValidator(iModelValidator modelValidator) {
        getInstance().doRegisterValidator(modelValidator);
    }

    /**
     *
     * @param validatorSupplier
     */
    public static void setValidatorSupplier(iValidatorSupplier validatorSupplier) {
        getInstance().doSetValidatorSupplier(validatorSupplier);
    }

    /**
     *
     * @param clazz
     * @return
     */
    public static ClassMetadata getClassMetaData(Class clazz) {
        return getInstance().doGetClassMetaData(clazz);
    }

    /**
     *
     * @param modelValidator
     */
    public synchronized void doRegisterValidator(iModelValidator modelValidator) {
        logger.log(Level.INFO, "Registered modelValidator {0}", modelValidator);
        validatorsMap.setFast(false);
        validatorsMap.put(modelValidator.getClazz().getName(), modelValidator);
        validatorsMap.setFast(true);
    }

    /**
     *
     * @param validatorSupplier
     */
    protected synchronized void doSetValidatorSupplier(iValidatorSupplier validatorSupplier) {
        this.validatorSupplier = validatorSupplier;
    }

    /**
     *
     * @param clazz
     * @return
     */
    protected ClassMetadata doGetClassMetaData(Class clazz) {
        iModelValidator modelValidator = doGetValidator(clazz);
        if (modelValidator instanceof ClassMetadata) {
            return (ClassMetadata) modelValidator;
        }
        return null;
    }

    /**
     *
     * @param clazz
     * @param propertyName
     * @param value
     * @param messages
     */
    protected void doValidateProperty(Class clazz, String propertyName, Object value, iMessages messages) {
        iModelValidator modelValidator = doGetValidator(clazz);
        if (modelValidator != null) {
            modelValidator.validatePropertyValue(propertyName, value, messages);
        }
    }

    /**
     *
     * @param clazz
     * @return
     */
    protected iModelValidator doGetValidator(Class clazz) {
        iModelValidator modelValidator = (iModelValidator) validationEngine.validatorsMap.get(clazz.getName());
        if (modelValidator == null) {
            if (validatorSupplier != null) {
                modelValidator = validatorSupplier.getValidator(clazz);
                if (modelValidator != null) {
                    registerValidator(modelValidator);
                }
            }
            // still null
            if (modelValidator == null) {
                try {
                    // force registration of xdoclet generated classes by loading the class
                    String validatorClassName = clazz.getName() + "ModelValidator";
                    Class.forName(validatorClassName);
                    modelValidator = (iModelValidator) validationEngine.validatorsMap.get(clazz.getName());
                } catch (Exception ex) {
                    // do nothing
                }
            }
            if (modelValidator == null) {
                // @todo still null maybe register a Null validator for this class
                // to reconize no validationEngine available
            }
        }
        return modelValidator;
    }

    /**
     *
     * @param object
     * @return
     */
    protected iModelValidator doGetValidator(Object object) {
        return doGetValidator(object.getClass());
    }

    /**
     *
     * @param object
     * @param messages
     */
    protected void doValidate(Object object, iMessages messages) {
        iModelValidator modelValidator = doGetValidator(object.getClass());
        if (modelValidator != null) {
            modelValidator.validate(object, messages);
        }
        if (object instanceof iValidatable) {
            ((iValidatable) object).validate(messages);
        }
    }

    /**
     *
     * @param object
     */
    protected void doValidate(Object object) {
        iMessages messages = new iMessagesImpl();
        doValidate(object, messages);
        if (messages.getNumberOfErrorMessages() > 0) {
            throw new ValidationException(messages);
        }
    }

    /**
     *
     * @param object
     * @param groupName
     * @param messages
     */
    protected void doValidateGroup(Object object, String groupName, iMessages messages) {
        iModelValidator modelValidator = doGetValidator(object.getClass());
        if (modelValidator != null) {
            modelValidator.validateGroup(object, groupName, messages);
        }
    }

    /**
     *
     */
    protected ValidationEngine() {
        validatorsMap = new FastHashMap();
        validatorsMap.setFast(true);
    }

    /**
     *
     */
    public interface iValidatorSupplier {

        /**
         *
         * @param clazz
         * @return
         */
        iModelValidator getValidator(Class clazz);
    }
}
