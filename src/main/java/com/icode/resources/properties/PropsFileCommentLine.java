/**
 * 
 */
package com.icode.resources.properties;

/**
 * @author alexanderson
 *
 */
class PropsFileCommentLine implements PropsFileLine {
	private final String lineContent;

	PropsFileCommentLine(String lineContent) {
		super();
		this.lineContent = lineContent;
	}

	public String getLineContent() {
		return lineContent;
	}
}
