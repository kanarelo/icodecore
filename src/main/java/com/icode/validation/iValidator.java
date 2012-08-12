/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icode.validation;

/**
 *
 * @author Nes
 */
public interface iValidator {

    /**
     * Validate the <code>Object</code> that implements
     * this interface to be exact, all its fields.
     */
    public void validate();

    /**
     * Validate a property from the Object that implements this interface
     * @param propertyName represents the Name of the field to be validated
     * @param propertyValue represents value of the field to be validated
     * @param messages represents the Object that holds the Validation iMessages
     */
    public void validateProperty(String propertyName, Object propertyValue, iMessages messages);
}
