/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.icode.validation.validators.annotation.Fields;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.icode.util.Strings;
import com.icode.validation.validators.annotation.enums.FilePathType;

/**
 *
 * @author Nes
 */
@Target(value = {ElementType.METHOD, ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface FileField {
    /**
     *
     * @return
     */
    public String path() default "";
    /**
     *
     * @return
     */
    public String match() default Strings.patterns.FILE_PATTERN;
    /**
     *
     * @return
     */
    public FilePathType URLAddressType();// default FilePathType.WINDOWS;
    /**
     *
     * @return
     */
    public String  ValidationText() default "The Entered File Path is not Valid!";
    /**
     * 
     * @return
     */
    public boolean VerifyExists() default false;
    /**
     *
     * @return
     */
    public String  FileDoesNotExistText() default "The Entered file path does not exist!";
}
