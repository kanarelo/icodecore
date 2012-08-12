package com.icode.validation;

/**
 * Interface that indicates that a javabeans exposes, in addition 
 * to the default defined ,extra programmatic validations.
 * validates.
 * 
 * @author roho
 *
 */
public interface iExposesValidates {

    /**
     * Do the extra validates.
     *
     * Validates the object. Validation errors are added to the errors object.
     *
     * @param messages
     */
    void internalValidate(iMessages messages);
}
