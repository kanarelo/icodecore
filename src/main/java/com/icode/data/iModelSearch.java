package com.icode.data;

import java.util.List;

import com.icode.data.domain.BaseModel;

/*
 *
 * @author ADMIN
 */

/**
 * 
 * @author Nes
 * @param <T>
 */
public interface iModelSearch<T extends BaseModel> {
	/**
	 * 
	 * @param sw
	 * @return
	 */
	public List<T> find(SearchWizard sw);
}
