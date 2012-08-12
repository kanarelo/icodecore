/*
 * $Header: /cvsroot/jvalidate/jvalidate-framework/jvalidate/src/main/java/nl/knowlogy/validation/metadata/PropertyMetadataImpl.java,v 1.4 2007/04/20 12:53:18 roberthofstra Exp $
 * $Revision: 1.4 $
 * $Date: 2007/04/20 12:53:18 $
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
package com.icode.validation.validators.metadata;

import java.util.Map;

import org.apache.commons.collections.FastHashMap;

/**
 * 
 * 
 * @author Robert
 */
public class PropertyMetadataImpl implements PropertyMetadata {
    private FastHashMap metadataMap = new FastHashMap();
    private String propertyName;

    /**
     *
     * @param propertyName
     */
    public PropertyMetadataImpl(String propertyName) {
        this.propertyName = propertyName;
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.metadata.PropertyMetadata#getPropertyName()
     */
    public String getPropertyName() {
        return propertyName;
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.metadata.PropertyMetadata#setRequired(boolean)
     */
    public void setRequired(Boolean value) {
        metadataMap.put(PropertyMetadata.REQUIRED, value);
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.metadata.PropertyMetadata#isRequired()
     */
    public Boolean isRequired() {
        Boolean value = (Boolean) metadataMap.get(PropertyMetadata.REQUIRED);
        return value != null ? value : Boolean.FALSE;
    }

    /**
     * 
     * @param value
     */
    public void setMaxLength(Long value) {

        metadataMap.put(PropertyMetadata.MAX_LENGTH, value);
    }
    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.metadata.PropertyMetadata#getMaxLength()
     */

    public Long getMaxLength() {

        return (Long) metadataMap.get(PropertyMetadata.MAX_LENGTH);
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.metadata.PropertyMetadata#getMaxSize()
     */
    public Long getMaxSize() {
        return (Long) metadataMap.get(PropertyMetadata.MAX_SIZE);
    }

    /**
     *
     * @param value
     */
    public void setMaxSize(Long value) {
        metadataMap.put(PropertyMetadata.MAX_SIZE, value);

    }

    /**
     *
     * @param value
     */
    public void setMinLength(Long value) {
        metadataMap.put(PropertyMetadata.MIN_LENGTH, value);
    }
    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.metadata.PropertyMetadata#getMinLength()
     */

    public Long getMinLength() {
        return (Long) metadataMap.get(PropertyMetadata.MIN_LENGTH);
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.metadata.PropertyMetadata#getMinSize()
     */
    public Long getMinSize() {
        return (Long) metadataMap.get(PropertyMetadata.MIN_SIZE);
    }

    /**
     *
     * @param value
     */
    public void setMinSize(Long value) {
        metadataMap.put(PropertyMetadata.MIN_SIZE, value);

    }
    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.metadata.PropertyMetadata#getRegEx()
     */

    public String getPattern() {
        return (String) metadataMap.get(PropertyMetadata.PATTERN);
    }

    /**
     *
     * @param pattern
     */
    public void setPattern(String pattern) {
        metadataMap.put(PropertyMetadata.PATTERN, pattern);
    }
    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.metadata.PropertyMetadata#getAllowedValues()
     */

    public String getAllowedValues() {
        return (String) metadataMap.get(PropertyMetadata.ALLOWED_VALUES);
    }

    /**
     *
     * @param value
     */
    public void setAllowedValues(String value) {
        metadataMap.put(PropertyMetadata.ALLOWED_VALUES, value);
    }

    /**
     *
     * @param name
     * @param value
     */
    public void addMetadata(String name, Object value) {
        //check if name is one of registred
        metadataMap.setFast(false);
        try {
            metadataMap.put(name, value);
        } finally {
            metadataMap.setFast(true);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.metadata.PropertyMetadata#getMetadata()
     */
    public Map getMetadata() {
        return metadataMap;
    }
}
