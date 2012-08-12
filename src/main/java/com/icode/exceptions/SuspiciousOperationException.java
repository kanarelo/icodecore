/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.exceptions;

/**
 *
 * @author Nes
 */
public class SuspiciousOperationException extends Exception {
    /**
     *
     * @param message
     * @param cause
     */
    public SuspiciousOperationException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     *
     * @param message
     */
    public SuspiciousOperationException(String message) {
        super(message);
    }
}