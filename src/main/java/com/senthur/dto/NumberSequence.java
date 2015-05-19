package com.senthur.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents a Number Sequence
 * 
 * @author Senthur Shanmugalingm
 * */
@Entity
public class NumberSequence {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	private int number;

	public NumberSequence() {

	}

	public NumberSequence(int number) {
		this.number = number;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
