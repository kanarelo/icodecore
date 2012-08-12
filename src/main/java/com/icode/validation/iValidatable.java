package com.icode.validation;

/*
 * $Header: /cvsroot/jvalidate/jvalidate-framework/jvalidate/src/main/java/nl/knowlogy/validation/iValidatable.java,v 1.3 2007/11/11 14:34:37 roberthofstra Exp $
 * $Revision: 1.3 $
 * $Date: 2007/11/11 14:34:37 $
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
 * Interface for objects that are iValidatable.
 * 
 * @author <a href="mailto:robert.hofstra@knowlogy.nl">Robert Hofstra, Knowlogy</a>
 */
public interface iValidatable {
    /**
     * Validates the object. Validation errors are added to the errors object.
     * @param messages
     */
    void validate(iMessages messages);
}
