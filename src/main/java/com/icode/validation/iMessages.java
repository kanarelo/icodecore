package com.icode.validation;

/*
 * $Header: /cvsroot/jvalidate/jvalidate-framework/jvalidate/src/main/java/nl/knowlogy/validation/iMessages.java,v 1.5 2007/04/20 12:53:18 roberthofstra Exp $
 * $Revision: 1.5 $
 * $Date: 2007/04/20 12:53:18 $
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
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

/**
 * <p>
 * A <code>iMessages</code> is a message container,in which messages are stored
 * during validating and is used to provide feedback about the validation.
 * </p>
 * 
 * 
 * @see iMessage
 * 
 * @author Robert
 */
public interface iMessages extends Serializable {

	/**
	 * Stores an message in the messages.
	 * 
	 * @param message
	 *            message to add
	 */
	void addMessage(iMessage message);

	/**
	 * <p>
	 * Stores and creates an <code>iMessage</code> in the messages, based on the
	 * give params.
	 * </p>
	 * <p>
	 * These messages are not bound to a specific property of the object.
	 * </p>
	 * 
	 * @param messageType
	 *            type of the message
	 * @param object
	 *            the rejected object
	 * @param messageCode
	 *            message code, interpretable as message key
	 */
	void addMessage(MessageType messageType, Object object, String messageCode);

	/**
	 * Add a message.
	 * 
	 * @param messageType
	 *            type of the message
	 * @param object
	 * @param messageCode
	 *            message code, interpretable as message key
	 * @param defaultMessage
	 *            fallback default message
	 */
	void addMessage(MessageType messageType, Object object, String messageCode,
			String defaultMessage);

	/**
	 * Add a message.
	 * 
	 * @param messageType
	 *            type of the message
	 * @param object
	 * @param messageCode
	 *            message code, interpretable as message key
	 * @param messageArgs
	 *            message arguments, for argument binding via MessageFormat (can
	 *            be null)
	 * @param defaultMessage
	 *            fallback default message
	 */
	void addMessage(MessageType messageType, Object object, String messageCode,
			Object[] messageArgs, String defaultMessage);

	/**
	 * Add a message for a specific property of the object.
	 * 
	 * @param messageType
	 *            type of the message
	 * @param object
	 * @param propertyName
	 *            the property name
	 * @param messageCode
	 *            message code, interpretable as message key
	 */
	void addPropertyMessage(MessageType messageType, Object object,
			String propertyName, String messageCode);

	/**
	 * Add a message for a specific property of the object.
	 * 
	 * @param messageType
	 *            type of the message
	 * @param object
	 * @param propertyName
	 *            the field name
	 * @param messageCode
	 *            message code, interpretable as message key
	 * @param defaultMessage
	 *            fallback default message
	 */
	void addPropertyMessage(MessageType messageType, Object object,
			String propertyName, String messageCode, String defaultMessage);

	/**
	 * Add a message for a specific property of the object.
	 * 
	 * @param messageType
	 *            type of the message
	 * @param object
	 * @param propertyName
	 *            the property name
	 * @param messageCode
	 *            message code, interpretable as message key
	 * @param messageArgs
	 *            message arguments, for argument binding via MessageFormat (can
	 *            be null)
	 * @param defaultMessage
	 *            fallback default message
	 */
	void addPropertyMessage(MessageType messageType, Object object,
			String propertyName, String messageCode, Object[] messageArgs,
			String defaultMessage);

	/**
	 * Returns the number of message's.
	 * 
	 * @return
	 */
	int getNumberOfMessages();

	/**
	 * 
	 * @return
	 */
	int getNumberOfErrorMessages();

	/**
	 * Returns the number of messages with a specific messagetype.
	 * 
	 * @param messageType
	 * @return
	 */
	int getNumberOfMessages(MessageType messageType);

	/**
	 * Returns the first message associated with the given object and
	 * propertyName, if such a message is present else null.
	 * 
	 * @param object
	 *            object that is associated with the message.
	 * @param propertyName
	 *            that is associated with the message.
	 * 
	 * @return Returns the first message associated with the given object and
	 *         propertyName, if such a message is present else null.
	 */
	iMessage getMessage(Object object, String propertyName);

	/**
	 * Returns a list of <code>iMessage</code> instances associated with the
	 * given object and propertyName, if such a message is present.
	 * 
	 * @param object
	 *            object that is associated with the message.
	 * @param propertyName
	 *            that is associated with the message.
	 * 
	 * @return a list of <code>iMessage</code>instances associated with the
	 *         given object and propertyName, if such a message is present.
	 */
	List getMessages(Object object, String propertyName);

	/**
	 * 
	 * @param object
	 * @param objectLevelOnly
	 * @return
	 */
	iMessage getMessage(Object object, boolean objectLevelOnly);

	/**
	 * 
	 * @param object
	 * @param objectLevelOnly
	 * @return
	 */
	List getMessages(Object object, boolean objectLevelOnly);

	/**
	 * 
	 * @return
	 */
	List getMessages();

	/**
	 * Adds messages.
	 * 
	 * @param messages
	 */
	void addMessages(iMessages messages);

	/**
	 * Returns an overal messageMessage.
	 * 
	 * @return
	 */
	String getMessage();

	/**
	 * 
	 * @param resourceBundle
	 */
	void convertMessageCodes(ResourceBundle resourceBundle);

	/**
	 * Clears all messages in the container.
	 */
	void clear();
}
