package com.senthur.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.senthur.dao.NumberSequenceDao;
import com.senthur.dto.NumberSequence;

/**
 * Implementation class for {@link NumberSequenceDao}
 * 
 * @author Senthur Shanmugalingm
 * */
@Named("numberSequenceDao")
@Stateless
public class NumberSequenceDaoBean extends AbstractBaseDao<NumberSequence> implements NumberSequenceDao {

	/**
	 * This method uses <code>{@link EntityManager}</code> to query the underlying data store, to fetch all the <code>{@link NumberSequence}</code>
	 * 
	 * @see NumberSequenceDao#listAllNumberSequences()
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<NumberSequence> listAllNumberSequences() {
		Query query = entityManager
				.createQuery("select ns from NumberSequence ns order by ns.id", NumberSequence.class);
		List<NumberSequence> resultSet = query.getResultList();
		return resultSet;
	}

}
