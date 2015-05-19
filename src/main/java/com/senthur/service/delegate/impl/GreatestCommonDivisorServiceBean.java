package com.senthur.service.delegate.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;

import com.senthur.dao.GreatestCommonDivosorDao;
import com.senthur.dao.NumberSequenceDao;
import com.senthur.dto.GreatestCommonDivisor;
import com.senthur.dto.NumberSequence;
import com.senthur.service.delegate.GreatestCommonDivisorService;
import com.senthur.service.queue.DataConsumer;

/**
 * Implementation class of <code>{@link GreatestCommonDivisorService}</code>
 * 
 * @author Senthur Shanmugalingm
 * 
 * */
@Named("greatCommonDivisorService")
@Stateless
public class GreatestCommonDivisorServiceBean implements GreatestCommonDivisorService {

	@Inject
	private DataConsumer retreiver;

	@Inject
	private GreatestCommonDivosorDao greatestCommonDivisorDao;

	@Inject
	private NumberSequenceDao numberSequenceDao;

	/**
	 * This method performs GCD calculation by obtaining the first two sequences from the head of the underlying queue. Further, if the calculation was
	 * successful, then the sequences and the GCD are stored in the underlying data store.
	 * 
	 * @see GreatestCommonDivisorService#calculateGCD()
	 * */
	@Override
	public int calculateGCD() {
		int result = 0;
		try {
			Integer firstNumber = retreiver.getMessage();
			Integer secondNumber = retreiver.getMessage();

			if ((firstNumber != null) && (secondNumber != null)) {
				result = calculateGCD(firstNumber, secondNumber);
				persistDataToDataStore(result, firstNumber, secondNumber);
			}
		} catch (JMSException e) {
			result = -1;
		}
		return result;
	}

	/**
	 * This method will calculate the GCD using the provided numbers.
	 * 
	 * @param number1
	 *            <code>int</code> the first integer number.
	 * @param number2
	 *            <code>int</code> the second integer number.
	 * 
	 * @return <code>int</code> calculated GCD integer.
	 * */
	private int calculateGCD(int number1, int number2) {
		BigInteger firstNumber = BigInteger.valueOf(number1);
		BigInteger secondNumber = BigInteger.valueOf(number2);
		return firstNumber.gcd(secondNumber).intValue();
	}

	/**
	 * This method performs persistence operation
	 * 
	 * @param gcd
	 *            <code>int</code> calculated integer gcd
	 * @param firstNumber
	 *            <code>int</code> first Sequence
	 * @param secondNumber
	 *            <code>int</code> second Sequence
	 * */
	private void persistDataToDataStore(int gcd, int firstNumber, int secondNumber) {
		saveNumberSequence(firstNumber);
		saveNumberSequence(secondNumber);
		saveGCD(gcd);
	}

	/**
	 * This method saves the number sequence to the underlying data store
	 * */
	private void saveNumberSequence(int sequence) {
		numberSequenceDao.save(new NumberSequence(sequence));
	}

	/**
	 * This method saves the passed GCD to the underlying data store
	 * */
	private void saveGCD(int gcd) {
		greatestCommonDivisorDao.save(new GreatestCommonDivisor(gcd));
	}

	/**
	 * Obtains all available GCD from the database.
	 * 
	 * @see GreatestCommonDivisorService#getAllGreatestCommonDivisors()
	 * */
	@Override
	public List<Integer> getAllGreatestCommonDivisors() {
		List<Integer> result = new ArrayList<>(0);
		List<GreatestCommonDivisor> availableGreatestCommonDivisors = greatestCommonDivisorDao
				.listAllGreatCommonDivisors();
		for (GreatestCommonDivisor greatestCommonDivisor : availableGreatestCommonDivisors) {
			result.add(greatestCommonDivisor.getGcd());
		}
		return result;
	}

	/**
	 * Obtains a total of all GCD in the database.
	 * 
	 * @see GreatestCommonDivisorService#getTotalOfAllGCD()
	 * */
	@Override
	public int getTotalOfAllGCD() {
		int result = 0;
		List<Integer> allGCD = getAllGreatestCommonDivisors();
		for (Integer gcd : allGCD) {
			result = result + gcd;
		}
		return result;
	}
}
