package com.icode.validation;

/*
 * $Header: /cvsroot/jvalidate/jvalidate-framework/jvalidate/src/main/java/nl/knowlogy/validation/iPropertyValidation.java,v 1.4 2007/11/11 14:34:36 roberthofstra Exp $
 * $Revision: 1.4 $
 * $Date: 2007/11/11 14:34:36 $
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
/**
 * Interface for validation of a specific property of a class 
 * identified with propertyName.
 * 
 * @author <a href="mailto:robert.hofstra@knowlogy.nl">Robert Hofstra, Knowlogy</a>
 */
public interface iPropertyValidation {

    /**
     * Returns the propertyName from the property which this propertyValidation
     * is validating.
     *
     * @return the propertyName from the property which this propertyValidation
     *         is validating.
     */
    String getPropertyName();

    /**
     * Validates the property value of object obj. In case of any validation
     * errors, an they are added to the errors object.
     *
     * @param object
     * @param propertyValue
     * @param errors
     *            errors object to which any validation errors are added.
     */
    void doValidatePropertyValue(Object object, Object propertyValue, iMessages errors);
}
