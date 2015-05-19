package com.senthur.service.delegate;

import java.util.List;

/**
 * Implement this Service to provide support for handling Number Sequences
 * 
 * @author Senthur Shanmugalingm
 * */
public interface NumberSequenceService {

	/**
	 * This method will send the data to the underlying store Example could be use of a JMS Queue, a Database or some other service which may process this data
	 * 
	 * @param param1
	 *            <code>int</code> the first integer parameter.
	 * @param param2
	 *            <code>int</code> the second integer parameter.
	 * */
	public boolean sendData(int param1, int param2);

	/**
	 * This method will return all available number sequences from an underlying store.
	 * 
	 * @return <code>{@link List}</code> of <code>{@link Integer}</code>
	 * */
	public List<Integer> getAllAvialableNumberSequences();

}
