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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "gun.findByProd", query = "Select g from Gun g where g.producer.id = :id"),
	@NamedQuery(name = "gun.all", query = "Select g from Gun g")
})
public class Gun {
	
	private Long id;
	private String name;
	private String productionDate;
	private Boolean sold = false;
	private Double weight;
	private Producer producer;
	private Label label;
	private List<User> users = new ArrayList<User>();
	private List<Distributor> distributors = new ArrayList<Distributor>();
	
	public Gun() {
		super();
	}

	public Gun(String name, String productionDate, Boolean sold, Double weight, Producer producer) {
		super();
		this.name = name;
		this.productionDate = productionDate;
		this.sold = sold;
		this.weight = weight;
		this.producer = producer;
	}
	
	public Gun(String name, String productionDate, Boolean sold, Double weight) {
		super();
		this.name = name;
		this.productionDate = productionDate;
		this.sold = sold;
		this.weight = weight;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(nullable = false)
	public String getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}
	@Column(nullable = false)
	public Boolean getSold() {
		return sold;
	}
	public void setSold(Boolean sold) {
		this.sold = sold;
	}
	@Column(nullable = false, precision = 5, scale = 2)
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	public Producer getProducer() {
		return producer;
	}
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Label getLabel() {
		return label;
	}
	public void setLabel(Label label) {
		this.label = label;
	}
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Distributor> getDistributors() {
		return distributors;
	}
	public void setDistributors(List<Distributor> distributors) {
		this.distributors = distributors;
	}
	
}
