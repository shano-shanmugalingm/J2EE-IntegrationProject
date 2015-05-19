package com.senthur.api.soap;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.senthur.service.delegate.GreatestCommonDivisorService;

/**
 * GCD related API
 * @author Senthur Shanmugalingm 
 * */
@WebService
public class GCDService {

	@Inject
	private GreatestCommonDivisorService greatCommonDivisorService;

	
	/**
	 * This method will return the calculated GCD.
	 * If no GCD could be calculated 0 will be returned.
	 * If any exception occurs the return value will be -1 
	 * 
	 * @return <code>int</code> calculated GCD.  0 if no numbers found for calculation. -1 if any exceptions occur.
	 * 
	 * */
	@WebMethod
	public int getGCD() {
		return greatCommonDivisorService.calculateGCD();
	}
	
	/**
	 * This method will return all available GCD in the system, at the time of execution.
	 * If no GCD is found the returned {@link List} will be empty.
	 * 
	 * @return <code>{@link List}</code> of <code>{@link Integer}</code> 
	 * */
	@WebMethod
	public List<Integer> getAllGCD() {
		return greatCommonDivisorService.getAllGreatestCommonDivisors();
	}
	
	/**
	 * This method will return the total of all GCD available in the system at the time of execution. 
	 * If no GCDs are found the returned value will be 0.
	 * 
	 * @return <code>{@link Integer}</code> 
	 * */
	@WebMethod
	public int getSumOfAllGCD() {
		return greatCommonDivisorService.getTotalOfAllGCD();
	}
}
