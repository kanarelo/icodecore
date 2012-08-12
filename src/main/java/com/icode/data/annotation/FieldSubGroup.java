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
 * @author Nes
 **/
@Target(value = { ElementType.FIELD, ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface FieldSubGroup {
	public String groupName();
	public String[] fieldList();
}
