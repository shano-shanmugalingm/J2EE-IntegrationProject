package com.senthur.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents a Greatest Common Divisor
 * 
 * @author Senthur Shanmugalingm
 * 
 * */
@Entity
public class GreatestCommonDivisor {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	private int gcd;

	public GreatestCommonDivisor() {

	}

	public GreatestCommonDivisor(int gcd) {
		this.gcd = gcd;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGcd() {
		return gcd;
	}

	public void setGcd(int gcd) {
		this.gcd = gcd;
	}
}
