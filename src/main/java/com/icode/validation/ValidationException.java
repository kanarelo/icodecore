package com.icode.validation;


/**
 * A ValidationException is a exception which contains
 * validation messages.
 * 
 * @author <a href="mailto:robert.hofstra@knowlogy.nl">Robert Hofstra, Knowlogy</a>
 *
 */
public class ValidationException extends RuntimeException {

    /**
     *
     */
    public static final long serialVersionUID = 0;
    private iMessages messages;

    /**
     *
     * @param messages
     */
    public ValidationException(iMessages messages) {
        this.messages = messages;
    }

    /**
     *
     * @return
     */
    public iMessages getMessages() {
        return messages;
    }

    @Override
    public String getMessage() {
        return messages != null ? messages.toString() : null;
    }
}
