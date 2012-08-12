package com.icode.data.repository;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DuplicateKeyException;

import com.icode.data.SearchWizard;
import com.icode.data.domain.BaseModel;
import com.icode.exceptions.IllegalOrphanException;
import com.icode.exceptions.NonexistentEntityException;

/**
 *
 * @author kanarelo
 */
public interface BaseDao<E extends BaseModel> {
	//Create Object
    void saveWithoutDuplicateHandling(E e);
    void save(E e) throws DuplicateKeyException;
    //Edit Object
    void update(E e) throws DuplicateKeyException;
    //Delete Object
    void delete(E e) throws IllegalOrphanException, NonexistentEntityException; 
    //Count Objects
    int count();

    E getUnique(Integer id);
    E getUnique(DetachedCriteria criteria);
    
    List<E> getAll();
	List<E> getList(String hqlQuery, Object... values);
	//List but paginate
	List<E> getList(String hqlQuery, int startIndex, int limit, Object... values);

	List<E> getListBySearchWizard(SearchWizard sw);
}
