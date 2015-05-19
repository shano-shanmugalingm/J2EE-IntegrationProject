package com.senthur.dao;

import java.util.List;

import com.senthur.dto.GreatestCommonDivisor;

/**
 * This interface should be implemented to provide functionality related to Greatest Common Divisor
 * 
 * @author Senthur Shanmugalingm
 * 
 * */
public interface GreatestCommonDivosorDao extends BaseDao<GreatestCommonDivisor> {

	/**
	 * Implement this method to List all the available Greatest Common Divisors stored in the underlying data store.
	 * 
	 * @return <code>{@link List}</code> of <code>{@link GreatestCommonDivisor}</code>
	 * */
	public List<GreatestCommonDivisor> listAllGreatCommonDivisors();

}
