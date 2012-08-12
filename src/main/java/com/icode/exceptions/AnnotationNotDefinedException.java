/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.exceptions;

/**
 *
 * @author Nes
 */
public class AnnotationNotDefinedException extends Exception {
    /**
     *
     * @param message
     * @param cause
     */
    public AnnotationNotDefinedException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     *
     * @param message
     */
    public AnnotationNotDefinedException(String message) {
        super(message);
    }
}