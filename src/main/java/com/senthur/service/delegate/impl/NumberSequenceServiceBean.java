package com.senthur.service.delegate.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.senthur.dao.NumberSequenceDao;
import com.senthur.dto.NumberSequence;
import com.senthur.service.delegate.NumberSequenceService;
import com.senthur.service.queue.DataPublisher;

/**
 * Implementation class for <code>{@link NumberSequenceService}</code>
 * 
 * @author Senthur Shanmugalingm
 * */
@Named("numberSequenceService")
@Stateless
public class NumberSequenceServiceBean implements NumberSequenceService {

	@Inject
	private DataPublisher publisher;

	@Inject
	private NumberSequenceDao numberSequenceDao;

	/**
	 * This method will send the passed parameters to the JMS Message Queue.
	 * 
	 * @see NumberSequenceService#sendData(int, int)
	 * */
	@Override
	public boolean sendData(int param1, int param2) {
		return publisher.publishMessage(param1, param2);
	}

	/**
	 * This method will obtain all available number sequences from the underlying database.
	 * 
	 * @see NumberSequenceService#getAllAvialableNumberSequences()
	 * */
	@Override
	public List<Integer> getAllAvialableNumberSequences() {
		List<Integer> numbers = new ArrayList<Integer>(0);
		List<NumberSequence> availableNumberSequences = numberSequenceDao.listAllNumberSequences();

		if (availableNumberSequences != null) {
			for (NumberSequence numberSequence : availableNumberSequences) {
				numbers.add(numberSequence.getNumber());
			}
		}
		return numbers;
	}

}
