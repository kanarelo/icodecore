/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.exceptions;

/**
 *
 * @author Nes
 */
public class ObjectDoesNotExistException extends Exception {
    /**
     *
     * @param message
     * @param cause
     */
    public ObjectDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     *
     * @param message
     */
    public ObjectDoesNotExistException(String message) {
        super(message);
    }
}