package com.example.shdemo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "user.all", query = "Select u from User u")
})
public class User {

	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDate = new Date();
	//fields to count
	private Boolean ofAge = true;
	private Integer numberOfRegisteredGuns = 0;
	
	public User() {
		super();
	}
	
	public User(String firstName, String lastName, Date birthDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

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
	@Temporal(TemporalType.DATE)
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
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
