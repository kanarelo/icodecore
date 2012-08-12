package com.icode.validation;

/**
 * iMessageImpl is the default implementation for the <tt>iMessage</tt>
 * interface.
 * 
 * @author <a href="mailto:robert.hofstra@knowlogy.nl">Robert Hofstra,
 *         Knowlogy</a>
 * 
 */
public class iMessageImpl implements iMessage {
	private Object objectPertainingMessage;
	private String propertyName;
	private String message;
	private String messageCode;
	private Object[] messageArgs;
	private MessageType messageType;
	private boolean rendered = false;

	/**
	 * 
	 * @param messageType
	 * @param objectPertainingMessage
	 * @param propertyName
	 * @param messageCode
	 */
	public iMessageImpl(MessageType messageType,
			Object objectPertainingMessage, String propertyName,
			String messageCode) {
		this(messageType, objectPertainingMessage, propertyName, messageCode,
				null, null);
	}

	/**
	 * 
	 * @param messageType
	 * @param objectPertainingMessage
	 * @param propertyName
	 * @param messageCode
	 * @param message
	 */
	public iMessageImpl(MessageType messageType,
			Object objectPertainingMessage, String propertyName,
			String messageCode, String message) {
		this(messageType, objectPertainingMessage, propertyName, messageCode,
				null, message);
	}

	/**
	 * 
	 * @param messageType
	 * @param objectPertainingMessage
	 * @param propertyName
	 * @param messageCode
	 * @param errorArgs
	 * @param message
	 */
	public iMessageImpl(MessageType messageType,
			Object objectPertainingMessage, String propertyName,
			String messageCode, Object[] errorArgs, String message) {
		this.messageType = messageType;
		this.objectPertainingMessage = objectPertainingMessage;
		this.propertyName = propertyName;
		this.messageCode = messageCode;
		this.messageArgs = errorArgs;
		this.message = message;

	}

	public Object getRelatedObject() {
		return objectPertainingMessage;
	}

	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * @param message
	 */
	public void setMessage(String message) {

		this.message = message;
	}

	/**
	 * @return Returns the propertyName.
	 */
	public String getPropertyName() {
		return propertyName;
	}

	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * @return Returns the messageArgs.
	 */
	public Object[] getMessageArgs() {
		return messageArgs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.knowlogy.validation.errors.PropertyError#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder(6).append("\n\"Message\":{")
				.append("\n\t\"propertyName\":").append(this.getPropertyName())
				.append("\n").append("\t\"messageCode\":")
				.append(this.getMessageCode()).append("\n")
				.append("\t\"message\":").append(this.getMessage())
				.append("\n").append("\t\"messageArgs\":")
				.append(this.getMessageArgs()).append("\n}").toString();
	}

	public MessageType getMessageType() {
		return messageType;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isRendered() {
		return rendered;
	}

	/**
	 * 
	 * @param rendered
	 */
	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}
}
