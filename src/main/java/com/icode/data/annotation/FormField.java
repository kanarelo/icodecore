/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icode.data.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Nes
 */
@Target(value = { ElementType.FIELD, ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface FormField {

	public String labelForField();

	/**
	 * 
	 * @return a simple message that is next to the textfield to direct the user
	 */
	public String helptext();

	public int columns() default 15;

	public String watermark() default "";

	public boolean searchable() default false;
}
