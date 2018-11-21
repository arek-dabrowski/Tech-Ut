package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	private long id;
	
	private String firstName;
	private String lastName;
	private String birthDate;
	private boolean isOfAge;
	private int numberOfRegisteredGuns;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String firstName, String lastName, String birthDate, boolean isOfAge, int numberOfRegisteredGuns) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.isOfAge = isOfAge;
		this.numberOfRegisteredGuns = numberOfRegisteredGuns;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public boolean isOfAge() {
		return isOfAge;
	}
	public void setOfAge(boolean isOfAge) {
		this.isOfAge = isOfAge;
	}
	public int getNumberOfRegisteredGuns() {
		return numberOfRegisteredGuns;
	}
	public void setNumberOfRegisteredGuns(int numberOfRegisteredGuns) {
		this.numberOfRegisteredGuns = numberOfRegisteredGuns;
	}
}
