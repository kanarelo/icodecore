/**
 * 
 */
package com.icode.resources;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.icode.util.ICodeUtils;

/**
 * @author Alex Anderson <alex@frontlinesms.com>
 */
public class ClasspathPropertySet extends BasePropertySet {
	// > STATIC CONSTANTS
	/** Logging object for this instance. */
	public static final Logger LOG = ICodeUtils
			.getLogger(ClasspathPropertySet.class);

	// > INSTANCE PROPERTIES

	// > CONSTRUCTORS
	/**
	 * Load a {@link BasePropertySet} from the classpath.
	 * 
	 * @param path
	 *            The classpath path of the resource
	 * @throws IOException
	 */
	protected ClasspathPropertySet(String path) throws IOException {
		super.setProperties(BasePropertySet.load(BasePropertySet.class
				.getResourceAsStream(path)));
	}

	// > ACCESSORS
	/**
	 * @param propertyKey
	 *            The key for the property to get
	 * @return value from {@link #properties}
	 */
	@Override
	protected String getProperty(String propertyKey) {
		return super.getProperty(propertyKey);
	}

	// > INSTANCE HELPER METHODS

	// > STATIC FACTORIES

	// > STATIC HELPER METHODS
}
