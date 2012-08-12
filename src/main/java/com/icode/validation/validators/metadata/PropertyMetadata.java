/*
 * $Header: /cvsroot/jvalidate/jvalidate-framework/jvalidate/src/main/java/nl/knowlogy/validation/metadata/PropertyMetadata.java,v 1.5 2007/04/01 12:59:51 roberthofstra Exp $
 * $Revision: 1.5 $
 * $Date: 2007/04/01 12:59:51 $
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

/**
 * <code>PropertyMetadata</code> is a interface for retrieving metadata from a property.
 * 
 * @see nl.knowlogy.validation.metadata.StringMetadata
 * @see nl.knowlogy.validation.metadata.IntegerMetadata
 * @author Robert
 */
public interface PropertyMetadata {
    /**
     *
     */
    String MAX_SIZE = "maxSize";
    /**
     *
     */
    String MIN_SIZE = "minSize";
    /**
     *
     */
    String REQUIRED = "required";
    /**
     *
     */
    String MIN_LENGTH = "minlength";
    /**
     *
     */
    String MAX_LENGTH = "maxlenght";
    /**
     *
     */
    String PATTERN = "pattern";
    /**
     *
     */
    String ALLOWED_VALUES = "allowed-values";

    /**
     * Returns the propertyName from the property to which this propertyMetadata
     * belongs.
     *
     * @return the propertyName from the property to which this propertyMetadata
     *         belongs.
     */
    String getPropertyName();

    /**
     * Return true if a value for this property is required.
     *
     * @return Returns the isRequired.
     */
    Boolean isRequired();

    /**
     *
     * @param value
     */
    void setRequired(Boolean value);

    /**
     * Return a maxsize of a number value
     * @return
     */
    Long getMaxSize();

    /**
     *
     * @param value
     */
    void setMaxSize(Long value);

    /**
     *  Returns a minsize of a number value.
     *
     * @return minsize of a number value
     */
    Long getMinSize();

    /**
     *
     * @param value
     */
    void setMinSize(Long value);

    /**
     * @return Returns the regEx.
     */
    String getPattern();

    /**
     * 
     * @param pattern
     */
    void setPattern(String pattern);

    /**
     * Returns a string with the allowed values. Values
     * a separeted with ','
     *
     * @return
     */
    String getAllowedValues();

    /**
     *
     * @param value
     */
    void setAllowedValues(String value);

    /**
     *
     * @param value
     */
    void setMaxLength(Long value);

    /**
     * @return Returns the maxLength.
     */
    Long getMaxLength();

    /**
     *
     * @param value
     */
    void setMinLength(Long value);

    /**
     * @return Returns the minLength.
     */
    Long getMinLength();

    /**
     * Retuns a map with metadata. Standard keys ared
     * maxSize, minSize, required,minlength, maxlenght, pattern
     *
     * @return a map with metadata. Standard keys ared
     * maxSize, minSize, required,minlength, maxlenght, pattern	.
     */
    Map getMetadata();
}
