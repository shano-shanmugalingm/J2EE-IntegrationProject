package com.senthur.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.senthur.dao.BaseDao;

/**
 * This is the abstract implementation of {@link BaseDao}
 * 
 * @author Senthur Shanmugalingm
 * */
public abstract class AbstractBaseDao<T> implements BaseDao<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * This method uses <code>{@link EntityManager}</code> to persist data to the data store
	 * 
	 * @see BaseDao#save(Object)
	 * */
	public void save(T entity) {
		entityManager.persist(entity);
	}

	/**
	 * This method uses <code>{@link EntityManager}</code> to merge data to the data store
	 * 
	 * @see BaseDao#update(Object)
	 * 
	 * */
	public void update(T entity) {
		entityManager.merge(entity);
	}
}
