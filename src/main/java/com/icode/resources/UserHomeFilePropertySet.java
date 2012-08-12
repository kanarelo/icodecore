/*
 * ICode <http://www.frontlinesms.com>
 * Copyright 2007, 2008 kiwanja
 * 
 * This file is part of ICode.
 * 
 * ICode is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 * 
 * ICode is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with ICode. If not, see <http://www.gnu.org/licenses/>.
 */
package com.icode.resources;

import org.apache.log4j.Logger;

import com.icode.util.ICodeUtils;

/**
 * Set of properties with String value. Each {@link UserHomeFilePropertySet} is
 * tied to a specific file located in the properties directory of the
 * application's config.
 * 
 * TODO This class should be renamed FilePropertySet
 * 
 * @author Alex
 */
public abstract class UserHomeFilePropertySet extends FilePropertySet {

	// > CONSTANTS
	/** Logging object for this instance. */
	public static final Logger LOG = ICodeUtils
			.getLogger(UserHomeFilePropertySet.class);

	// > INSTANCE METHODS
	/**
	 * Create a new instance of this class from the supplied name.
	 * 
	 * @param name
	 *            The name of the {@link UserHomeFilePropertySet} from which is
	 *            derived the file it is persisted to
	 */
	protected UserHomeFilePropertySet(String name) {
		super(ResourceUtils.getPropertiesFile(name));
		super.setProperties(FilePropertySet.loadPropertyMap(ResourceUtils
				.getPropertiesFile(name)));
	}

	/**
	 * Gets the {@link Boolean} value of a property.
	 * 
	 * @param propertyName
	 * @return The value of the property as a {@link Boolean} or
	 *         <code>null</code> if it is not set.
	 * @deprecated Should use {@link #getPropertyAsBoolean(String, boolean)}
	 *             instead
	 */
	@Deprecated
	protected Boolean getPropertyAsBoolean(String propertyName) {
		String value = getProperty(propertyName);
		if (value == null)
			return null;
		else
			return Boolean.parseBoolean(value);
	}

	// > STATIC FACTORY METHODS
}
