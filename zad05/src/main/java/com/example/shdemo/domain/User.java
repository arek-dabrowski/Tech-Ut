package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	private Long id;
	private String firstName;
	private String lastName;
	private String birthDate;
	private Boolean ofAge;
	private Integer numberOfRegisteredGuns;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public boolean getOfAge() {
		return ofAge;
	}
	public void setOfAge(Boolean ofAge) {
		this.ofAge = ofAge;
	}
	public Integer getNumberOfRegisteredGuns() {
		return numberOfRegisteredGuns;
	}
	public void setNumberOfRegisteredGuns(Integer numberOfRegisteredGuns) {
		this.numberOfRegisteredGuns = numberOfRegisteredGuns;
	}
}
