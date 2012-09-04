/**
 * 
 */
package com.icode.data.repository.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.BooleanQuery;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.icode.data.domain.BaseModel;
import com.icode.data.repository.BaseDao;

/**
 * @author Alex
 * @param <E>
 *            Entity that this dao is for
 */
public abstract class BaseHibernateDao<E extends BaseModel> extends
		HibernateDaoSupport implements BaseDao<E> {
	/** Logging object */
	final Log log = LogFactory.getLog(getClass());

	/** Class that this dao deals with. */
	private final Class<E> clazz;
	/** The unqualified name of {@link #clazz} */
	private final String className;

	/** EventNotifier that sends out FrontlineEvents **/

	/**
	 * @param clazz
	 */
	protected BaseHibernateDao(Class<E> clazz) {
		this.clazz = clazz;
		this.className = clazz.getName();
	}

	/**
	 * Save an entity, without checking for exceptions thrown for duplicate keys
	 * or unique columns.
	 * 
	 * @param entity
	 *            entity to save
	 */
	public void saveWithoutDuplicateHandling(E entity) {
		log.trace("Saving entity: " + entity);
		this.getHibernateTemplate().save(entity);
		log.trace("Entity saved.");
	}

	/**
	 * Saves an entity .
	 * 
	 * @param entity
	 *            entity to save
	 * @throws DuplicateKeyException
	 *             if there was a {@link ConstraintViolationException} thrown
	 *             while saving
	 */
	public void save(E entity) throws DuplicateKeyException {
		try {
			saveWithoutDuplicateHandling(entity);
		} catch (RuntimeException ex) {
			if (isClashOfUniqueColumns(ex)) {
				throw new DuplicateKeyException(ex.getMessage());
			} else {
				throw ex;
			}
		}
	}

	/**
	 * Checks if a {@link Throwable} was caused by a clash of unique items.
	 * 
	 * @param t
	 *            {@link Throwable} thrown
	 * @return <code>true</code> if the {@link Throwable} represents a clash of
	 *         unique column values
	 */
	private boolean isClashOfUniqueColumns(Throwable t) {
		Throwable cause = t.getCause();
		return cause != null
				&& (cause instanceof ConstraintViolationException || cause instanceof NonUniqueObjectException);
	}

	/**
	 * Updates an entity.
	 * 
	 * @param entity
	 *            entity to update
	 * @throws DuplicateKeyException
	 *             if there was a {@link ConstraintViolationException} thrown
	 *             while updating
	 */
	public void update(E entity) throws DuplicateKeyException {
		try {
			updateWithoutDuplicateHandling(entity);
		} catch (RuntimeException ex) {
			if (isClashOfUniqueColumns(ex)) {
				throw new DuplicateKeyException(ex.getMessage());
			} else {
				throw ex;
			}
		}
	}

	/**
	 * Updates an entity, without checking for exceptions thrown for duplicate
	 * keys or unique columns.
	 * 
	 * @param entity
	 *            entity to update
	 */
	protected void updateWithoutDuplicateHandling(E entity) {
		log.trace("Updating entity: " + entity);
		this.getHibernateTemplate().update(entity);
		log.trace("Entity updated.");
	}

	/**
	 * Deletes an entity.
	 * 
	 * @param entity
	 *            entity to delete
	 */
	public void delete(E entity) {
		log.trace("Deleting entity: " + entity);
		this.getHibernateTemplate().delete(entity);
		log.trace("Entity deleted.");
	}

	/**
	 * Gets all entities of type {@link #clazz}.
	 * 
	 * @return list of all entities of type {@link #clazz}
	 */
	public List<E> getAll() {
		return this.getList(getCriterion());
	}

	/**
	 * Gets a list of E matching the supplied criteria.
	 * 
	 * @param criteria
	 * @return a list of Es matching the supplied criteria
	 */
	@SuppressWarnings("unchecked")
	protected List<E> getList(DetachedCriteria criteria) {
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * Gets a list of E matching the supplied HQL query.
	 * 
	 * @param hqlQuery
	 *            HQL query
	 * @param values
	 *            values to insert into the HQL query
	 * @return a list of Es matching the supplied query
	 */
	@SuppressWarnings("unchecked")
	public List<E> getList(String hqlQuery, Object... values) {
		return this.getHibernateTemplate().find(hqlQuery, values);
	}

	/**
	 * Gets a list of E matching the supplied HQL query.
	 * 
	 * @param hqlQuery
	 *            HQL query
	 * @param startIndex
	 *            the index of the first result object to be retrieved (numbered
	 *            from 0)
	 * @param limit
	 *            the maximum number of result objects to retrieve (or <=0 for
	 *            no limit)
	 * @param values
	 *            values to insert into the HQL query
	 * @return a list of Es matching the supplied query
	 */
	public List<E> getList(String hqlQuery, int startIndex, int limit,
			Object... values) {
		List<E> list = getList(hqlQuery, values);
		if (limit <= 0) {
			return list.subList(startIndex, Integer.MAX_VALUE);
		} else {
			return list.subList(startIndex,
					Math.min(list.size(), startIndex + limit));
		}
	}

	/**
	 * Gets total number of this entity saved in the database.
	 * 
	 * @return total number of this entity saved in the database
	 */
	public int count() {
		return (int) ((Long) this.getHibernateTemplate()
				.find("select count(*) from " + this.className).get(0))
				.longValue();
	}

	/**
	 * Get all entities within a specific range.
	 * 
	 * @param startIndex
	 *            index of first entity to fetch
	 * @param limit
	 *            maximum number of entities to fetch
	 * @return all entities within a specific range
	 */
	protected List<E> getAll(int startIndex, int limit) {
		return this.getList(getCriterion(), startIndex, limit);
	}

	protected DetachedCriteria getCriterion() {
		DetachedCriteria criteria = DetachedCriteria.forClass(this
				.getModelClass());
		return criteria;
	}

	public E getUnique(Integer i) {
		return DataAccessUtils.uniqueResult(this.getList(null));
	}

	/**
	 * Gets a unique result of type E from the supplied criteria.
	 * 
	 * @param criteria
	 * @return a single E, or <code>null</code> if none was found.
	 */
	@SuppressWarnings("unchecked")
	public E getUnique(DetachedCriteria criteria) {
		return DataAccessUtils.uniqueResult(this.getList(criteria));
	}

	/**
	 * Gets a paged list of {@link #clazz}.
	 * 
	 * @param criteria
	 * @param startIndex
	 * @param limit
	 * @return paged list fitting the supplied criteria.
	 */
	@SuppressWarnings("unchecked")
	protected List<E> getList(DetachedCriteria criteria, int startIndex,
			int limit) {
		return this.getHibernateTemplate().findByCriteria(criteria, startIndex,
				limit);
	}

	/**
	 * Gets an equals {@link Criterion} for the supplied field and value. If the
	 * value is <code>null</code>, a isNull {@link Criterion} is created
	 * instead.
	 * 
	 * @param field
	 * @param value
	 * @return an isNull {@link Criterion} if the value to equal is
	 *         <code>null</code>, or a an equals {@link Criterion} if the value
	 *         is not <code>null</code>
	 */
	protected Criterion getEqualsOrNull(String field, Object value) {
		if (value == null) {
			return Restrictions.isNull(field);
		} else {
			return Restrictions.eq(field, value);
		}
	}

	/**
	 * Gets a count of the results for the supplied criteria.
	 * 
	 * @param criteria
	 * @return number of result rows there are for the supplied criteria.
	 */
	protected int count(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		return DataAccessUtils.intResult(this.getHibernateTemplate()
				.findByCriteria(criteria));
	}

	/**
	 * Gets a count of the results for the supplied HQL query string. The HQL
	 * query should be a COUNT statement.
	 */
	protected int count(String queryString, Object... values) {
		List<?> results = getHibernateTemplate().find(queryString, values);
		return DataAccessUtils.intResult(results);
	}

	protected Class<E> getModelClass() {
		return clazz;
	}

	public List<E> search(String keyword, String... columns) {
		FullTextSession fullTextSession = Search
				.getFullTextSession(getSession());
		
		Transaction tx = fullTextSession.beginTransaction();
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(clazz).get();

		org.apache.lucene.search.Query query = qb.keyword().onFields(columns)
				.matching(keyword).createQuery();

		// execute search
		org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(
				query, clazz);
		List<E> result = hibQuery.list();

		tx.commit();
		getSession().close();

		return result;
	}
}
