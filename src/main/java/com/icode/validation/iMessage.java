package com.icode.validation;

/*
 * $Header: /cvsroot/jvalidate/jvalidate-framework/jvalidate/src/main/java/nl/knowlogy/validation/iMessage.java,v 1.4 2007/06/25 21:28:32 roberthofstra Exp $
 * $Revision: 1.4 $
 * $Date: 2007/06/25 21:28:32 $
 * 
 * 
 * Copyright   2004 - 2005 Knowlogy, the Netherlands, www.knowlogy.nl
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * <p>
 * A <code>iMessage</code> is container which contains data of one specific
 * message. It contains data like message, messagecode, messagetype, related
 * object and propertyname.
 * </p>
 * 
 * @author <a href="mailto:robert.hofstra@knowlogy.nl">Robert Hofstra,
 *         Knowlogy</a>
 * 
 */
public interface iMessage {

	/**
	 * <p>
	 * Returns the name of the property to which the message is related. Null is
	 * returned if the message is not related to a specific property.
	 * </p>
	 * 
	 * @return The name of the property which contains a message. null if the
	 *         message is not related to any property
	 */
	String getPropertyName();

	/**
	 * <p>
	 * Returns the messagecode for the message.
	 * </p>
	 * 
	 * @return the messagecode.
	 */
	String getMessageCode();

	/**
	 * <p>
	 * Returns the message.
	 * </p>
	 * 
	 * @return the messssageMessage.
	 */
	String getMessage();

	/**
	 * 
	 * @param message
	 */
	void setMessage(String message);

	/**
	 * <p>
	 * Returns the string representation.
	 * </p>
	 * 
	 * @return the string representation.
	 */
	@Override
	String toString();

	/**
	 * <p>
	 * Returns the messssage Arguments which contain information about the
	 * occured event. Which arguments are returned can depend on the
	 * messagecode.
	 * </p>
	 * 
	 * @return Returns the messssage Arguments which contain information about
	 *         the occured event. Which arguments are returned can depend on the
	 *         messagecode.
	 */
	Object[] getMessageArgs();

	// String[] getValidationGroups();
	/**
	 * <p>
	 * Returns the object to which the message is related.
	 * </p>
	 * 
	 * @return object to which the message is related.
	 */
	Object getRelatedObject();

	/**
	 * <p>
	 * Returns type of the message.
	 * </p>
	 * 
	 * @see MessageType
	 * 
	 * @return type of the message
	 */
	MessageType getMessageType();

	/**
	 * 
	 * @return
	 */
	boolean isRendered();

	/**
	 * 
	 * @param rendered
	 */
	void setRendered(boolean rendered);
}
