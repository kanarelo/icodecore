package com.icode.validation;

import com.icode.validation.validators.metadata.PropertyMetadata;

/**
 * 
 * @author <a href="mailto:robert.hofstra@knowlogy.nl">Robert Hofstra, Knowlogy</a>
 *
 */
public interface iPropertyMetadataSuplier {
    /**
     *
     * @param propertyMetadata
     */
    void supplyMetaData(PropertyMetadata propertyMetadata);
}
