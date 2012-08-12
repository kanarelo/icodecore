package com.icode.validation;

/*
 * Created on 11-jul-2005
 *
 * @author <a href="mailto:robert.hofstra@knowlogy.nl">Robert Hofstra, Knowlogy</a>
 */



/**
 * ErrorCodes containins default errorcodes
 * used by validation. 
 *
 * @author <a href="mailto:robert.hofstra@knowlogy.nl">Robert Hofstra, Knowlogy</a>
 */
public abstract class ErrorCodes {
    
    private static final String PREFIX = "com.icode.Error.";
    
    /**
     * 
     */
    public static final String INVALID_MAX_LENGTH =  PREFIX + "invalidmaxlength";

    /**
     *
     */
    public static final String INVALID_CURRENCY_TEXT =  PREFIX + "invalidCurrencyText";

    /**
     *
     */
    public static final String INVALID_DATE_TEXT =  PREFIX + "invalidDateText";

    /**
     *
     */
    public static final String INVALID_ALPHANUMERIC_TEXT = PREFIX + "invalidAlphanumericText";
    
    /**
     *
     */
    public static final String INVALID_MIN_LENGTH = PREFIX + "invalidminlength";

    /**
     *
     */
    public static final String INVALID_NAME_TEXT = PREFIX + "invalidName";

    /**
     *
     */
    public static final String INVALID_EMAIL_ADDRESS = PREFIX + "invalidEmailAddress";

    /**
     *
     */
    public static final String INVALID_PHONE_NUMBER = PREFIX + "invalidPhoneNumber";

    /**
     *
     */
    public static final String INVALID_URL_ADDRESS = PREFIX + "invalidURLAddress";

    /**
     *
     */
    public static final String INVALID_DATETIME_TEXT = PREFIX + "invalidDateTimeText";

    /**
     *
     */
    public static final String INVALID_FILE_PATH = PREFIX + "invalidFilePath";

    /**
     *
     */
    public static final String INVALID_IP_ADDRESS = PREFIX + "invalidIPAddress";

    /**
     *
     */
    public static final String INVALID_SLUG_TEXT = PREFIX + "invalidSlugText";

    /**
     *
     */
    public static final String INVALID_TEXT = PREFIX + "invalidText";

    /**
     *
     */
    public static final String INVALID_TIME_TEXT = PREFIX + "invalidTimeText";
    
    /**
     *
     */
    public static final String INVALID_MIN_SIZE =  PREFIX + "invalidminsize";
    
    /**
     *
     */
    public static final String INVALID_MAX_SIZE =  PREFIX + "invalidmaxsize";
    
    /**
     *
     */
    public static final String NOT_ALLOWED_VALUE = PREFIX + "notallowedvalue";
    
    /**
     *
     */
    public static final String INVALID_FORMAT = PREFIX + "invalidformat";
    
    /**
     *
     */
    public static final String ISREQUIRED_VALUE = PREFIX + "isrequiredvalue";
    
    /**
     *
     */
    public static final String ISNOTBLANK = PREFIX + "isnotblank";

    /**
     *
     */
    public static final String INVALID_COMMA_SEPARATED_NUMBER = PREFIX + "invalidCommaSeparatedNumber";
    
    /**
     *
     */
    public static final String INVALID_RANGE = PREFIX + "invalidrange";
    
    /**
     *
     */
    public static final String INVALID_XML_MARKUP = PREFIX + "invalidXMLmarkup";
    
    /**
     *
     */
    public static final String INVALID_CREDITCARDNUMBER = PREFIX + "invalidCreditCardNumber";

    /**
     *
     */
    public static final String INVALID_FLOAT_TEXT = PREFIX + "invalidFloatValue";
    /**
     * 
     */
    public static final String ISNULL  = PREFIX + "NullValue";

    public static final String INTEGRITY_VIOLATION = PREFIX + "integrityViolation";
}
