package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "producer.all", query = "Select p from Producer p"),
	@NamedQuery(name = "producer.active", query = "Select p from Producer p where p.active = true")
})
public class Producer {

	private Long id;
	private String companyName;
	private Boolean active = true;
	
	private List<Gun> guns = new ArrayList<Gun>();
	
	public Producer() {
		super();
	}
	
	public Producer(String companyName, Boolean active) {
		super();
		this.companyName = companyName;
		this.active = active;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(unique = true, nullable = false)
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Column(nullable = false)
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "producer")
	public List<Gun> getGuns() {
		return guns;
	}
	public void setGuns(List<Gun> guns) {
		this.guns = guns;
	}
	
}
