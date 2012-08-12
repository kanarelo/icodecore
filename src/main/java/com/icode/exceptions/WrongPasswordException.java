/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.exceptions;

/**
 *
 * @author Nes
 */
public class WrongPasswordException extends Exception {
    /**
     *
     * @param message
     * @param cause
     */
    public WrongPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     *
     * @param message
     */
    public WrongPasswordException(String message) {
        super(message);
    }
}