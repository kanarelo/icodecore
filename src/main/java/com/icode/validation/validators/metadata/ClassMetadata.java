package com.icode.validation.validators.metadata;

/**
 * Interface for retrieving class metadata info.
 * 
 * @author Robert
 */
public interface ClassMetadata {

    /**
     * <p>
     * Returns PropertyMetadata for the given propertyName.  If no property with
     * propertyName exist null is returned.
     * </p>
     *
     * @see PropertyMetadata
     * @param propertyName name of the property
     * @return PropertyMetadata for the given propertyName. If no property with
     * propertyName exist null is returned.
     */
    PropertyMetadata getPropertyMetadata(String propertyName);
}
