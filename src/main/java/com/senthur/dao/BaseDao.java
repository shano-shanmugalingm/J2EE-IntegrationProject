package com.senthur.dao;

/**
 * Implement this interface for CRUD functionality
 * 
 * @author Senthur Shanmugalingm
 * 
 * */
public interface BaseDao<T> {

	/**
	 * This method should be implemented in a manner to provide the functionality of Save
	 * 
	 * @param entity
	 *            <code>T</code> a Generic Entity
	 * 
	 * */
	public void save(T entity);

	/**
	 * This method should be implemented in a manner to provide the functionality of Update
	 * 
	 * @param entity
	 *            <code>T</code> a Generic Entity
	 * 
	 * */
	public void update(T entity);

}
