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
@Target(value = { ElementType.TYPE })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Meta {

	/**
	 * 
	 * @return
	 */
	public String ordering() default "";

	/**
	 * 
	 * @return
	 */
	public String LatestBy() default "";

	/**
	 * 
	 * @return
	 */
	public String verboseName() default "";

	/**
	 * 
	 * @return
	 */
	public String verboseNamePlural() default "";

	/**
	 * 
	 * @return
	 */
	public String searchCandidates() default "";

	/**
	 * 
	 * @return The Title for the Element to be used by the GUI creator This
	 *         marks a short headline.
	 */
	public String title();

	/**
	 * 
	 * @return
	 */
	public String description() default "";

	/**
	 * 
	 * @return
	 */
	public FieldGroup[] groups() default {};
}
