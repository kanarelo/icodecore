/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.exceptions;

/**
 *
 * @author Nes
 */
public class PermissionDeniedException extends Exception {
    /**
     *
     * @param message
     * @param cause
     */
    public PermissionDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     *
     * @param message
     */
    public PermissionDeniedException(String message) {
        super(message);
    }
}