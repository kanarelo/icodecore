/*
 * $Header: /cvsroot/jvalidate/jvalidate-framework/jvalidate/src/main/java/nl/knowlogy/validation/validators/PatternValidator.java,v 1.6 2007/10/31 17:02:19 roberthofstra Exp $
 * $Revision: 1.6 $
 * $Date: 2007/10/31 17:02:19 $
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
package com.icode.validation.validators;


import java.lang.annotation.Annotation;

import com.icode.validation.validators.annotation.Fields.PatternField;
import com.icode.validation.validators.annotation.Valid.Validator;
import com.icode.validation.validators.base.basePatternValidator;

/**
 * 
 * @author Robert
 */
@Validator(fieldAnnotation = PatternField.class)
public class PatternValidator extends basePatternValidator{
    /**
     * 
     * @param propertyName
     * @param annotation
     */
    public PatternValidator(String propertyName, Annotation annotation) {
        super(propertyName);
        PatternField validatePattern = (PatternField) annotation;
        this.setPattern(validatePattern.pattern());
        this.setErrorCode(validatePattern.errorMessage());
    }    
}
