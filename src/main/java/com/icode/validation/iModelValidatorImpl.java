/*
 * $Header: /cvsroot/jvalidate/jvalidate-framework/jvalidate/src/main/java/nl/knowlogy/validation/iModelValidatorImpl.java,v 1.13 2007/11/13 15:35:45 roberthofstra Exp $
 * $Revision: 1.13 $
 * $Date: 2007/11/13 15:35:45 $
 * 
 * 
 * Copyright   2004 - 2005 Knowlogy, the Netherlands, www.knowlogy.nl
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.icode.validation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.FastArrayList;
import org.apache.commons.collections.FastHashMap;

import com.icode.validation.validators.metadata.ClassMetadata;
import com.icode.validation.validators.metadata.PropertyMetadata;
import com.icode.validation.validators.metadata.PropertyMetadataImpl;

/**
 *  iModelValidatorImpl implements the <tt>iModelValidator</tt> interface
 *  for validation of objects and the <tt>ClassMetadata<tt> interface for
 *  providing metadata of that class.
 * 
 * @author <a href="mailto:robert.hofstra@knowlogy.nl">Robert Hofstra, Knowlogy</a>
 */
public class iModelValidatorImpl implements iModelValidator, ClassMetadata {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(iModelValidatorImpl.class.getName());
    //private final static Logger logger = Logger.getLogger(iModelValidatorImpl.class);
    /**
     * Map with property name as key and a propertyEntry object as value.
     * A propertyEntry has a list of validators and a propertyMetaData object.
     *
     */
    private FastHashMap propertyEntryMap = new FastHashMap();
    /**
     * Map with group name as key and as
     * value a list with propertyName's
     *
     */
    private FastHashMap groups = new FastHashMap();
    private Class clazz;

    /**
     *
     * @param clazz
     */
    public iModelValidatorImpl(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.metadata.ClassMetadata#getPropertyMetadata(java.lang.String)
     */
    public PropertyMetadata getPropertyMetadata(String propertyName) {
        PropertyEntry propertyEntry = (PropertyEntry) propertyEntryMap.get(propertyName);
        if (propertyEntry != null) {
            return propertyEntry.getPropertyMetadata();
        }
        return null;
    }

    /**
     *
     * @return
     */
    public List getPropertyMetadata() {
        List propertyMetaDataList = new ArrayList(5);
        for (Iterator<PropertyEntry> iter = propertyEntryMap.values().iterator(); iter.hasNext();) {
            PropertyEntry propertyEntry = iter.next();
            propertyMetaDataList.add(propertyEntry.getPropertyMetadata());
        }
        return propertyMetaDataList;
    }

    public void add(iPropertyValidation propertyValidation) {
        iPropertyMetadataSuplier propertyMetadataSuplier = null;
        if (propertyValidation instanceof iPropertyMetadataSuplier) {
            propertyMetadataSuplier = (iPropertyMetadataSuplier) propertyValidation;
        }
        add(propertyValidation, propertyMetadataSuplier);
    }

    /**
     *
     * @param propertyValidation
     * @param propertyMetadataSuplier
     */
    public void add(iPropertyValidation propertyValidation, iPropertyMetadataSuplier propertyMetadataSuplier) {
        PropertyEntry propertyEntry = (PropertyEntry) propertyEntryMap.get(propertyValidation.getPropertyName());

        if (propertyEntry == null) {
            propertyEntry = new PropertyEntry(propertyValidation.getPropertyName());
            propertyEntryMap.setFast(false);
            propertyEntryMap.put(propertyValidation.getPropertyName(), propertyEntry);
            propertyEntryMap.setFast(true);
        }
        propertyEntry.addPropertyValidation(propertyValidation);
        if (propertyMetadataSuplier != null) {
            PropertyMetadata propertyMetadata = propertyEntry.getPropertyMetadata();
            synchronized (propertyMetadata) {
                propertyMetadataSuplier.supplyMetaData(propertyMetadata);
            }
        }
        logger.log(Level.INFO, "added propertyValidation to [{0}] ,propertyValidator {1}", new Object[]{this, propertyValidation});
    }

    public void addPropertyToGroups(String groupNames, String propertyName) {
        StringTokenizer stringTokenizer = new StringTokenizer(groupNames, ",");
        FastArrayList propertyNames;

        while (stringTokenizer.hasMoreTokens()) {
            String groupName = stringTokenizer.nextToken();
            propertyNames = (FastArrayList) groups.get(groupName);

            if (propertyNames == null) {
                propertyNames = new FastArrayList();
                groups.setFast(false);
                groups.put(groupName, propertyNames);
                groups.setFast(true);
            }
            propertyNames.setFast(false);
            propertyNames.add(propertyName);
            propertyNames.setFast(true);
        }
    }

    public void validate(Object toValidate) {
        iMessages messages = new iMessagesImpl();
        validate(toValidate, messages);

        if (messages.getNumberOfMessages(MessageType.ERROR) > 0) {
            throw new ValidationException(messages);
        }
    }

    /**
     *
     * @param toValidate
     * @param propertyName
     * @return
     */
    protected Object getValue(Object toValidate, String propertyName) {
        try {
            return PropertyUtils.getSimpleProperty(toValidate, propertyName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void doValidateProperties(Object toValidate, iMessages messages) {
        for (Iterator propertyEntryIterator = propertyEntryMap.values().iterator(); propertyEntryIterator.hasNext();) {
            PropertyEntry propertyEntry = (PropertyEntry) propertyEntryIterator.next();
            for (Iterator validatorsIterator = propertyEntry.getValidatorList().iterator(); validatorsIterator.hasNext();) {
                iPropertyValidation propertyValidation = (iPropertyValidation) validatorsIterator.next();
                propertyValidation.doValidatePropertyValue(toValidate, getValue(toValidate, propertyValidation.getPropertyName()), messages);
            }
        }
    }

    public void validate(Object toValidate, iMessages messages) {
        doValidateProperties(toValidate, messages);
        if (toValidate instanceof iExposesValidates) {
            ((iExposesValidates) toValidate).internalValidate(messages);
        }
    }

    public void validateGroup(Object toValidate, String groupName, iMessages errors) {
        if (groups.containsKey(groupName)) {
            FastArrayList propertyNames = (FastArrayList) groups.get(groupName);
            validateProperties(toValidate, propertyNames, errors);
        }
        validateGroupForIsValidValidators(toValidate, groupName, errors);
    }

    private void validateGroupForIsValidValidators(Object toValidate, String groupName, iMessages messages) {
        for (Iterator propertyEntryIterator = propertyEntryMap.values().iterator(); propertyEntryIterator.hasNext();) {
            PropertyEntry propertyEntry = (PropertyEntry) propertyEntryIterator.next();
            for (Iterator validatorsIterator = propertyEntry.getValidatorList().iterator(); validatorsIterator.hasNext();) {
                iPropertyValidation propertyValidation = (iPropertyValidation) validatorsIterator.next();
                if (propertyValidation instanceof IsValidValidator) {
                    ((IsValidValidator) propertyValidation).doValidatePropertyValue(toValidate, getValue(
                            toValidate, propertyValidation.getPropertyName()), groupName, messages);
                }
            }
        }
    }

    public void validateProperties(Object toValidate, List propertyNames, iMessages messages) {
        for (Iterator propertyNamesIterator = propertyNames.iterator(); propertyNamesIterator.hasNext();) {
            String propertyName = (String) propertyNamesIterator.next();
            Object value = getValue(toValidate, propertyName);
            validatePropertyValue(toValidate, propertyName, value, messages);
        }
    }

    /**
     *
     * @param toValidate
     * @param propertyName
     * @param value
     * @param messages
     */
    public void validatePropertyValue(Object toValidate, String propertyName, Object value, iMessages messages) {
        PropertyEntry propertyEntry = (PropertyEntry) propertyEntryMap.get(propertyName);
        if (propertyEntry != null) {
            for (Iterator validatorsIterator = propertyEntry.getValidatorList().iterator(); validatorsIterator.hasNext();) {
                iPropertyValidation propertyValidation = (iPropertyValidation) validatorsIterator.next();
                logger.log(Level.INFO, "validate property [{0}], value [{1}], for class [{2}] with {3}", new Object[]{propertyName, value, clazz.getName(), propertyValidation});
                propertyValidation.doValidatePropertyValue(toValidate, value, messages);
            }
        }
    }

    public void validatePropertyValue(String propertyName, Object value, iMessages messages) {
        validatePropertyValue(null, propertyName, value, messages);
    }

    class PropertyEntry {

        private FastArrayList validatorList;
        private PropertyMetadata propertyMetadata;
        private String propertyName;

        PropertyEntry(String propertyName) {
            this.propertyName = propertyName;
            validatorList = new FastArrayList();
            validatorList.setFast(true);
        }

        public FastArrayList getValidatorList() {
            return validatorList;
        }

        public PropertyMetadata getPropertyMetadata() {
            if (propertyMetadata == null) {
                propertyMetadata = new PropertyMetadataImpl(propertyName);
            }
            return propertyMetadata;
        }

        public void addPropertyValidation(iPropertyValidation propertyValidation) {
            validatorList.setFast(false);
            validatorList.add(propertyValidation);
            validatorList.setFast(true);
        }
    }
}
