/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.exceptions;

/**
 *
 * @author Nes
 */
public class FieldErrorException extends Exception {

    /**
     *
     * @param message
     * @param cause
     */
    public FieldErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message
     */
    public FieldErrorException(String message) {
        super(message);
    }
}
