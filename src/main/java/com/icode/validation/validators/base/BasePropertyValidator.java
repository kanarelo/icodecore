/*
 * $Header: /cvsroot/jvalidate/jvalidate-framework/jvalidate/src/main/java/nl/knowlogy/validation/validators/BasePropertyValidator.java,v 1.7 2007/11/13 15:35:45 roberthofstra Exp $
 * $Revision: 1.7 $
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
package com.icode.validation.validators.base;

import com.icode.validation.iPropertyValidation;

/**
 * <p>
 * <tt>BasePropertyValidator</tt> is a base class for property validators.  
 * Subclass this class for building specific validators.
 * </p>
 * 
 * 
 * 
 * @author Robert
 */
public abstract class BasePropertyValidator implements iPropertyValidation {

    private String propertyName = "";
    private String errorCode = "";
    /**
     *
     */
    //protected static final Logger logger = Logger.getLogger(BasePropertyValidator.class);

    /**
     * 
     * @param propertyName
     */
    public BasePropertyValidator(String propertyName) {
        this.propertyName = propertyName;
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.metadata.iPropertyValidation#getPropertyName()
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     *
     * @param value
     */
    public void setPropertyName(String value) {
        propertyName = value;
    }

    /**
     * Returns a specific errorcode, if setted, otherwise the defaulterrorcode.
     *
     * Each subclass must specify the defaultErrorCode.
     *
     * @return a specific errorcode, if setted, otherwise the defaulterrorcode.
     */
    public String getErrorCode() {
        if (errorCode != null || errorCode.length() != 0) {
            return errorCode;
        } else {
            return getDefaultErrorCode();
        }
    }

    /**
     *
     * @param value
     */
    public void setErrorCode(String value) {
        if (value != null) {
            errorCode = value;
        }
    }

    /**
     *
     * @return
     */
    public abstract String getDefaultErrorCode();

    @Override
    public String toString() {
        return " propertyName = [" + propertyName + "],errorCode = [" + errorCode + "], defaultErrorCode = [" + getDefaultErrorCode() + "]";
    }
}
