package com.example.shdemo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "user.all", query = "Select u from User u")
})
public class User {

	private Long id;
	private String firstName;
	private String lastName;
	private String birthDate;
	private Boolean ofAge = true;
	private Integer numberOfRegisteredGuns = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(nullable = false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(nullable = false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(nullable = false)
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	@Column(nullable = false)
	public Boolean getOfAge() {
		return ofAge;
	}
	public void setOfAge(Boolean ofAge) {
		this.ofAge = ofAge;
	}
	@Column(nullable = false)
	public Integer getNumberOfRegisteredGuns() {
		return numberOfRegisteredGuns;
	}
	public void setNumberOfRegisteredGuns(Integer numberOfRegisteredGuns) {
		this.numberOfRegisteredGuns = numberOfRegisteredGuns;
	}
}
