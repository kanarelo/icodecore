package com.icode.validation;

import java.util.Iterator;

import org.apache.commons.lang.enums.Enum;

/**
 * <p>
 * A type of Message. The constants of this enumerated type provide a simple classification of types
 * of messages declared in <code>Message</code>. 
 * </p>
 * <p>
 * Message type can be a one of the following
 * <ul>
 *  <li>Error</li>
 *  <li>Warning</li>
 *  <li>Info</li>
 * </ul> 
 * 
 * @author <a href="mailto:robert.hofstra@knowlogy.nl">Robert Hofstra, Knowlogy</a>
 *
 */
public class MessageType extends Enum {

    /**
     *
     */
    public static final long serialVersionUID = 0;
    /**
     *
     */
    public static final MessageType ERROR = new MessageType("Error");
    /**
     *
     */
    public static final MessageType WARNING = new MessageType("Warning");
    /**
     *
     */
    public static final MessageType INFO = new MessageType("Info");

    private MessageType(String name) {
        super(name);
    }

    /**
     *
     * @param name
     * @return
     */
    public static MessageType getEnum(String name) {
        return (MessageType) getEnum(MessageType.class, name);
    }

    /**
     *
     * @return
     */
    public static Iterator iterator() {
        return iterator(MessageType.class);
    }
}
