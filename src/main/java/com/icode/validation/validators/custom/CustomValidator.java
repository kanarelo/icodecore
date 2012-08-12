package com.icode.validation.validators.custom;

/*
 * $Header: /cvsroot/jvalidate/jvalidate-framework/jvalidate/src/main/java/nl/knowlogy/validation/CustomValidator.java,v 1.6 2007/11/13 15:35:45 roberthofstra Exp $
 * $Revision: 1.6 $
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


/**
 * <p>
 * Interface for building custom validators. A custom validator can validate a value and can
 * be used by a Custom validators can be used by a CustomPropertyValidator.
 * </p>
 * <p>Note there is a difference between a <tt>PropertyValidation</tt>, which is a combination of property and
 * validation and a <tt>CustomValidator</tt>.
 * 
 * @see CustomPropertyValidator
 * 
 * @author <a href="mailto:robert.hofstra@knowlogy.nl">Robert Hofstra, Knowlogy</a>
 * 
 */
public interface CustomValidator {

    /**
     *
     * @param value
     * @return
     */
    boolean isValid(Object value);
}
