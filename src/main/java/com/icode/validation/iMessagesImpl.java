/*
 * $Header: /cvsroot/jvalidate/jvalidate-framework/jvalidate/src/main/java/nl/knowlogy/validation/iMessagesImpl.java,v 1.4 2007/04/20 12:53:18 roberthofstra Exp $
 * $Revision: 1.4 $
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
package com.icode.validation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.collections.FastHashMap;

/**
 *  
 * 
 * @see nl.knowlogy.validation.iMessages
 * 
 * @author <a href="mailto:robert.hofstra@knowlogy.nl">Robert Hofstra, Knowlogy</a>
 */
public class iMessagesImpl implements iMessages {

    /**
     *
     */
    public static final long serialVersionUID = 0;
    private List<iMessage> messagesList = new ArrayList<iMessage>(4);
    private static final Logger logger = Logger.getLogger(iMessagesImpl.class.getName());
    private FastHashMap messagesMap = new FastHashMap();

    public void addMessage(iMessage message) {
        add(message);
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.errors.Errors#
     */
    public void addMessage(MessageType messageType, Object object,
            String errorCode) {
        add(new iMessageImpl(messageType, object, null, errorCode));
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.errors.Errors#reject(java.lang.String,
     *      java.lang.Object[], java.lang.String)
     */
    public void addMessage(MessageType messageType, Object object,
            String errorCode, Object[] errorArgs, String errorMessage) {
        add(new iMessageImpl(messageType, object, null, errorCode, errorArgs,
                errorMessage));

    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.errors.Errors#reject(java.lang.String,
     *      java.lang.String)
     */
    public void addMessage(MessageType messageType, Object object,
            String errorCode, String defaultMessage) {
        add(new iMessageImpl(messageType, object, null, errorCode,
                defaultMessage));
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.errors.Errors#rejectValue(java.lang.String,
     *      java.lang.String)
     */
    public void addPropertyMessage(MessageType messageType, Object object,
            String propertyName, String errorCode) {

        add(new iMessageImpl(messageType, object, propertyName, errorCode));

    }

    public void addPropertyMessage(MessageType messageType, Object object,
            String propertyName, String errorCode, String errorMessage) {

        add(new iMessageImpl(messageType, object, propertyName, errorCode,
                errorMessage));

    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.metadata.errors.Errors#rejectValue(java.lang.String,
     *      java.lang.String, java.lang.Object[], java.lang.String)
     */
    public void addPropertyMessage(MessageType messageType, Object object,
            String propertyName, String errorCode, Object[] errorArgs,
            String errorMessage) {
        add(new iMessageImpl(messageType, object, propertyName, errorCode,
                errorArgs, errorMessage));

    }

    @Override
    public String toString() {
        StringBuilder messageBuffer = new StringBuilder(5);
        messageBuffer.append("\n\"Messages\":{");

        for (Iterator<iMessage> messageIterator = messagesList.iterator(); messageIterator.hasNext();) {
            iMessage message = messageIterator.next();
            messageBuffer.append(message.toString());
        }
        messageBuffer.append("}");
        return messageBuffer.toString();
    }

    /**
     *
     * @return
     */
    public List<iMessage> getMessages() {
        List<iMessage> copyMessageList = new ArrayList<iMessage>(4);
        copyMessageList.addAll(messagesList);
        return copyMessageList;
    }

    public void addMessages(iMessages messages) {

        for (Iterator messagesIter = messages.getMessages().iterator(); messagesIter.hasNext();) {
            iMessage message = (iMessage) messagesIter.next();
            add(message);
        }

    }

    /**
     *
     * @return
     */
    public int getNumberOfErrorMessages() {
        return getNumberOfMessages(MessageType.ERROR);
    }

    /**
     *
     * @param resourceBundle
     */
    public void convertMessageCodes(ResourceBundle resourceBundle) {
        for (Iterator<iMessage> messageIterator = messagesList.iterator(); messageIterator.hasNext();) {
            iMessage message = messageIterator.next();
            if (message.getMessage() == null && message.getMessageCode() != null) {
                try {
                    String messagestr = resourceBundle.getString(message.getMessageCode());
                    message.setMessage(messagestr);
                } catch (java.util.MissingResourceException mre) {
                    logger.log(Level.WARNING, "Can''t find message for key {0}{1}", new Object[]{message.getMessageCode(), mre});
                    message.setMessage("Field has error, no key found in resoucebundle " + message.getMessageCode());
                }
            }
        }
    }

    /**
     *
     * @return
     */
    public boolean hasErrors() {
        return getNumberOfErrorMessages() != 0;
    }

    /**
     *
     * @param object
     * @param fieldName
     * @return
     */
    public iMessage getObjectMessage(Object object, String fieldName) {
        return getMessage(object, fieldName);
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.errors.Errors#getObjectError(java.lang.Object)
     */
    /**
     *
     * @param object
     * @return
     */
    public iMessage getObjectMessage(Object object) {
        return getMessage(object, false);
    }

    /*
     * (non-Javadoc)
     *
     * @see nl.knowlogy.validation.errors.Errors#getObjectError(java.lang.Object,
     *      boolean)
     */
    /**
     *
     * @param object
     * @param objectLevelOnly
     * @return
     */
    public iMessage getObjectMessage(Object object, boolean objectLevelOnly) {
        return getMessage(object, objectLevelOnly);
    }

    /**
     *
     * @param message
     */
    public void add(iMessage message) {
        messagesList.add(message);
        messagesMap.put(message.getPropertyName(), message);
    }

    public int getNumberOfMessages() {
        return messagesList.size();
    }

    /**
     *
     * @param index
     * @return
     */
    public iMessage get(int index) {
        return messagesList.get(index);
    }

    public iMessage getMessage(Object object, String propertyName) {
        for (Iterator<iMessage> messageListIterator = messagesList.iterator(); messageListIterator.hasNext();) {
            iMessage message = messageListIterator.next();
            if ((message.getRelatedObject() == object)
                    && (propertyName.equals(message.getPropertyName()))) {
                return message;
            }
        }
        return null;
    }

    public List getMessages(Object object, String propertyName) {
        List errorList = new ArrayList(4);
        for (Iterator<iMessage> messageListIterator = messagesList.iterator(); messageListIterator.hasNext();) {
            iMessage message = messageListIterator.next();
            if ((message.getRelatedObject() == object)
                    && (propertyName.equals(message.getPropertyName()))) {
                errorList.add(message);
            }
        }

        return errorList;
    }

    /**
     *
     * @param object
     * @param objectLevelOnly
     * @return
     */
    public iMessage getMessage(Object object, boolean objectLevelOnly) {
        for (Iterator<iMessage> messageListIterator = messagesList.iterator(); messageListIterator.hasNext();) {
            iMessage message = messageListIterator.next();
            if ((message.getRelatedObject() == object)
                    && (includeForObjectLevel(message.getPropertyName(),
                    objectLevelOnly))) {
                return message;
            }
        }
        return null;
    }

    /**
     *
     * @param object
     * @param objectLevelOnly
     * @return
     */
    public List getMessages(Object object, boolean objectLevelOnly) {
        List messages = new ArrayList(4);
        for (Iterator<iMessage> messageListIterator = messagesList.iterator(); messageListIterator.hasNext();) {
            iMessage message = messageListIterator.next();
            if ((message.getRelatedObject() == object)
                    && (includeForObjectLevel(message.getPropertyName(),
                    objectLevelOnly))) {
                messages.add(message);
            }
        }
        return messages;
    }

    private boolean includeForObjectLevel(String fieldName,
            boolean objectLevelOnly) {
        return !objectLevelOnly || fieldName.isEmpty();
    }

    public String getMessage() {
        return toString();
    }

    public int getNumberOfMessages(MessageType messageType) {
        int count = 0;
        for (Iterator<iMessage> messageListIterator = messagesList.iterator(); messageListIterator.hasNext();) {
            iMessage message = messageListIterator.next();
            if (message.getMessageType().equals(messageType)) {
                count++;
            }

        }
        return count;
    }

    public void clear() {
        messagesList.clear();
        messagesMap.clear();
    }
}
