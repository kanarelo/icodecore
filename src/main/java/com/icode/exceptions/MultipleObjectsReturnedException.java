/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.exceptions;

/**
 *
 * @author Nes
 */
public class MultipleObjectsReturnedException extends Exception {
    /**
     *
     * @param message
     * @param cause
     */
    public MultipleObjectsReturnedException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     *
     * @param message
     */
    public MultipleObjectsReturnedException(String message) {
        super(message);
    }
}