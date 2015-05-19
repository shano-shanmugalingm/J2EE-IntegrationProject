package com.senthur.service.delegate;

import java.util.List;

/**
 * Implement this interface to provide support related to GCD.
 * 
 * @author Senthur Shanmugalingm
 * 
 * */
public interface GreatestCommonDivisorService {

	/**
	 * This method will return the calculated GCD. If no GCD could be calculated 0 will be returned. If any exception occurs the return value will be -1
	 * 
	 * @return <code>int</code> calculated GCD. 0 if no numbers found for calculation. -1 if any exceptions occur.
	 * 
	 * */
	public int calculateGCD();

	/**
	 * This method will return all available GCD in the system, at the time of execution. If no GCD is found the returned {@link List} will be empty.
	 * 
	 * @return <code>{@link List}</code> of <code>{@link Integer}</code>
	 * */
	public List<Integer> getAllGreatestCommonDivisors();

	/**
	 * This method will return the total of all GCD available in the system at the time of execution. If no GCDs are found the returned value will be 0.
	 * 
	 * @return <code>{@link Integer}</code>
	 * */
	public int getTotalOfAllGCD();

}
