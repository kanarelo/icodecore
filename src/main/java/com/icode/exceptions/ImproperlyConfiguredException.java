/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.exceptions;

/**
 *
 * @author Nes
 */
public class ImproperlyConfiguredException extends Exception {

    /**
     *
     * @param message
     * @param cause
     */
    public ImproperlyConfiguredException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message
     */
    public ImproperlyConfiguredException(String message) {
        super(message);
    }
}
