/*
 * $Header: /cvsroot/jvalidate/jvalidate-framework/jvalidate/src/main/java/nl/knowlogy/validation/iModelValidator.java,v 1.8 2007/11/13 15:35:45 roberthofstra Exp $
 * $Revision: 1.8 $
 * $Date: 2007/11/13 15:35:45 $
 * 
 * 
 * Created on Oct 6, 2004
 *
 * All right reserved(c) 2004, Knowlogy
 * 
 * Copyright   2004 - 2005 Knowlogy, the Netherlands. All rights reserved. 
 * All Knowlogy brand and product names are trademarks or registered trademarks 
 * of Knowlogy in the Netherlands and other countries. 
 * 
 * No part of this publication may be reproduced, transmitted, stored in a retrieval system, 
 * or translated into any human or computer language, in any form, or by any means, electronic, 
 * mechanical, magnetic, optical, chemical, manual, or otherwise, 
 * without the prior written permission of the copyright owner, Knowlogy.
 * 
 */
package com.icode.validation;

import java.util.List;

/**
 * <p>
 * A <tt>iModelValidator</tt> validates objects of specific classes and can be registered
 * with a <tt>ValidationEngine</tt>. The <tt>ValidationEngine</tt> uses these classvalidators
 * for validating objects, by calling the <tt>validate</tt> method.
 * <p>
 * <p>
 * <tt>ClassValidatorImpl</tt> forms the baseclass for objects which are implementing the 
 * <tt>iModelValidator</tt> interface. These validation classes can be generated with XDoclet tags or
 * annotations in classes which they are validating. 
 * </p>
 * 
 * 
 * 
 * @see iPropertyValidation
 * @see ValidationEngine
 * 
 * @author <a href="mailto:robert.hofstra@knowlogy.nl">Robert Hofstra, Knowlogy</a>
 */
public interface iModelValidator {

    /**
     * Returns clazz that can be validated by this validator.
     * Subclasses of this class can also be validated by this class.
     * 
     * 
     * @return class
     */
    Class getClazz();

    /**
     * Adds a iPropertyValidation to the classValidator. This can be used to
     * add programmatically add additional validations to generated classvalidators.
     *
     * @param propertyValidation
     * @see iPropertyValidation
     */
    void add(iPropertyValidation propertyValidation);

    /**
     * Add a property identified to groups.
     *
     * @param groupNames string with names of groups seperated by <tt>","</tt>.
     * @param propertyName name of the property
     */
    void addPropertyToGroups(String groupNames, String propertyName);

    /**
     * Validates the object, if an error is signaled a Validation exception is thrown.
     *
     * @param toValidate object to validate
     */
    void validate(Object toValidate);

    /**
     * Validates the whole object. In case of validation errors, validation messages are
     * added to the messages.
     *
     * @param toValidate object to validate
     * @param messages container to which validate messages are added.
     */
    void validate(Object toValidate, iMessages messages);

    /**
     * Validates only the properties, which are contained in the group. This group is
     * identified by groupname. In case of validation errors, validation messages are
     * added to the messages.
     * 
     * @param toValidate object which group is validated.
     * @param groupName name of the group.
     * @param messages container to which validate messages are added.
     */
    void validateGroup(Object toValidate, String groupName, iMessages messages);

    /**
     * Validates only the properties, supplied in de propertyNames list, of
     * the object toValidate.  In case of validation errors, validation messages are
     * added to the messages.
     * 
     * @param toValidate object which properties are validated
     * @param propertyNames List of names (String) of the properties which are
     * validated
     * @param messages container to which validate messages are added.
     */
    void validateProperties(Object toValidate, List propertyNames, iMessages messages);

    /**
     * Validates the value of a property identified with name. This to validate a value
     * without setting the value of the javabean property. Validation messages are 
     * provided without a object which has the value.
     * 
     * @param propertyName name of the property
     * @param value value
     * @param messages a messages container to provide messages. 
     */
    void validatePropertyValue(String propertyName, Object value, iMessages messages);
}
