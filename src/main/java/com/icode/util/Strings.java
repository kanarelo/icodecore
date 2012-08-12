/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icode.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/*
 *@author kanarelo
 */
/**
 * class
 * 
 * @author Nes
 */
public class Strings extends StringUtils {

	public static final String NEW_LINE = "\n";
	public static final String NEW_TAB = "\t";
	public static final String SPACE = " ";
	public static final String SAVE_SUCCESSFUL = "The new data has been successfully saved";
	public static final String CHANGE_SUCCESSFUL = "The changes have been successfully saved";

	public static class beans {

		private static final String accessorPrefix = "get";
		private static final String mutatorPrefix = "set";

		private static final String formFieldNamePrefix = "___";

		public static String getMutator(String fieldName) {
			return mutatorPrefix + CapitaliseFirstLetter(fieldName);
		}

		public static String getMutatorFromFormField(String formFieldName) {
			return getMutator(getFieldFromFormField(formFieldName));
		}

		public static String getAccessor(String fieldName) {
			return accessorPrefix + CapitaliseFirstLetter(fieldName);
		}

		public static String getAccessorFromFormField(String formFieldName) {
			return getAccessor(getFieldFromFormField(formFieldName));
		}

		public static String getReadableFieldName(String fieldName) {
			String str = CapitaliseFirstLetter(fieldName);
			String[] strArr = splitByCharacterTypeCamelCase(str);
			StringBuilder str2 = new StringBuilder(2);
			for (String s : strArr) {
				str2.append(s);
				str2.append(SPACE);
			}
			return str2.toString().trim();
		}

		public static String[] splitByCharacterTypeCamelCase(String str) {
			return splitByCharacterType(str, true);
		}

		private static String[] splitByCharacterType(String str,
				boolean camelCase) {
			if (str == null) {
				return null;
			}
			if (str.length() == 0) {
				return new String[0];
			}
			char[] c = str.toCharArray();
			List<String> list = new ArrayList<String>();
			int tokenStart = 0;
			int currentType = Character.getType(c[tokenStart]);
			for (int pos = tokenStart + 1; pos < c.length; pos++) {
				int type = Character.getType(c[pos]);
				if (type == currentType) {
					continue;
				}
				if (camelCase && type == Character.LOWERCASE_LETTER
						&& currentType == Character.UPPERCASE_LETTER) {
					int newTokenStart = pos - 1;
					if (newTokenStart != tokenStart) {
						list.add(new String(c, tokenStart, newTokenStart
								- tokenStart));
						tokenStart = newTokenStart;
					}
				} else {
					list.add(new String(c, tokenStart, pos - tokenStart));
					tokenStart = pos;
				}
				currentType = type;
			}
			list.add(new String(c, tokenStart, c.length - tokenStart));
			return list.toArray(new String[list.size()]);
		}

		public static String getFormFieldName(String fieldName) {
			return formFieldNamePrefix + fieldName;
		}

		public static String getFieldFromFormField(String formFieldName) {
			if (isFormFieldName(formFieldName)) {
				return formFieldName.split(formFieldNamePrefix)[1];
			}
			return StringUtils.EMPTY;
		}

		public static boolean isFormFieldName(String formFieldName) {
			return formFieldName.startsWith(formFieldNamePrefix);
		}

		public static String getFormLabel(String fieldName) {
			return getReadableFieldName(fieldName) + ":";
		}
	}

	/**
     *
     */
	public static final String DELETE_CONFIRM = "Are you sure you want to delete the selected record?";
	/**
     *
     */
	public static final String DELETE_SUCCESSFUL = "Record has been successfully deleted";
	/**
     *
     */
	public static final String DELETE_FAILED = "Record was not deleted";
	/**
     *
     */
	public static final String JOPTION_TITLE = "iCode Framework System";
	/**
     *
     */
	public static final String JOPTION_DELETE_TITLE = "Delete Record";
	/**
     *
     */
	public static final String BOF = "Start Of File";
	/**
     *
     */
	public static final String EOF = "End Of File";
	/**
     *
     */
	public static final String VALIDATION_ERR_MESS = "Error validating the data Entered.\n"
			+ "Please check your entry for Error.\n"
			+ "Hint: check for invalid characters or\n"
			+ "valid names or numbers";
	/**
     *
     */
	public static final String SEARCH_RESULT_MESSAGE = "%d results for '%s'";
	/**
     *
     */
	public static final String NULL_STRING = "";
	/**
     *
     */
	public static final String VALIDATORS_PACKAGE_NAME = "";

	/**
     *
     */
	public static class persistence {

		private static String PU_String = "iCodeAPIPU";
		// private static String PU_String = "iCodeAPIPU_Test";

		/**
         *
         */
		// public static final String DB_CATALOG = "icode";
		// public static final String DB_CATALOG = "onesi_test";
		public static final String DB_CATALOG = "onesi";
		/**
         *
         */
		// public static final String AUTH_SCHEMA = "public";
		public static final String SCHEMA = "";
	}

	/**
     *
     */
	public static class patterns {

		/**
		 * "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"
		 */
		public static final String IPV4_PATTERN = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
		/**
         *
         */
		public static final String IPV6_PATTERN = "";
		/**
         *
         */
		public static final String ALPHA_NUMERIC_PATTERN = "^[A-Za-z0-9]+$";
		public static final String NAME_PATTERN = "^[A-Za-z]+$";
		public static final String PHONE_PATTERN = "^\\+[0-9]{1,3}(\\.|-)?[0-9]{4,14}(?:x.+)?$";
		public static final String SLUG_PATTERN = "^[-\\w]+$";
		public static final String COMMA_SEPARATED_PATTERN = "^[\\d,]+$";
		public static final String URL_PATTERN = "^https?://"
				+ // http:// or https://
				"(?:(?:[A-Z0-9](?:[A-Z0-9-]{0,61}[A-Z0-9])?\\.)+[A-Z]{2,6}\\.?|"
				+ // domain...
				"localhost|" + // localhost...
				"\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})" + // ...or ip
				"(?::\\d+)?" + // optional port
				"(?:/?|[/?]\\S+)$";
		public static final String FILE_PATTERN = "";
	}

	public static class formarts {
	}

	public static String CapitaliseFirstLetter(String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	/**
	 * 
	 * @return
	 */
	public static String getPU_String() {
		return persistence.PU_String;
	}

	/**
	 * 
	 * @param PU_String
	 */
	public static void setPU_String(String PU_String) {
		Strings.persistence.PU_String = PU_String;
	}
}
