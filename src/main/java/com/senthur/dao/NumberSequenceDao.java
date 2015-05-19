package com.senthur.dao;

import java.util.List;

import com.senthur.dto.NumberSequence;

/**
 * Implement this interface to provide functionality related to Number Sequences
 * 
 * @author Senthur Shanmugalingm
 * 
 * */
public interface NumberSequenceDao extends BaseDao<NumberSequence> {

	/**
	 * This method should be implemented in a manner that will return all the available number sequences stored in the underlying data store.
	 * 
	 * @return <code>{@link List}</code> of <code>{@link NumberSequence}</code>
	 * */
	public List<NumberSequence> listAllNumberSequences();

}
