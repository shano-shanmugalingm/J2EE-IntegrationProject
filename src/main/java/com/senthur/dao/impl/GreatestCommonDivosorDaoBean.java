package com.senthur.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.senthur.dao.GreatestCommonDivosorDao;
import com.senthur.dto.GreatestCommonDivisor;

/**
 * This class implements {@link GreatestCommonDivosorDao}
 * 
 * @author Senthur Shanmugalingm
 * 
 * */
@Named("greatestCommonDivosorDao")
@Stateless
public class GreatestCommonDivosorDaoBean extends AbstractBaseDao<GreatestCommonDivisor> implements
		GreatestCommonDivosorDao {

	/**
	 * This method uses <code>{@link EntityManager}</code> to query the underlying data store, which will fetch all the
	 * <code>{@link GreatestCommonDivisor}</code>
	 * 
	 * @see GreatestCommonDivosorDao#listAllGreatCommonDivisors()
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<GreatestCommonDivisor> listAllGreatCommonDivisors() {
		Query query = entityManager.createQuery("select gcd from GreatestCommonDivisor gcd",
				GreatestCommonDivisor.class);
		return query.getResultList();
	}

}
