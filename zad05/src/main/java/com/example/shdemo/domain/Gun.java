package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "gun.all", query = "Select g from Gun g")
})
public class Gun {
	
	private long id;
	
	private String name;
	private String productionDate;
	private Boolean damaged;
	private double weight;
	private Producer producer;
	private Label label;
	private List<User> users = new ArrayList<User>();
	
	public Gun() {
		super();
	}
	public Gun(String name, String productionDate, Boolean damaged, double weight) {
		super();
		this.name = name;
		this.productionDate = productionDate;
		this.damaged = damaged;
		this.weight = weight;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}
	public Boolean getDamaged() {
		return damaged;
	}
	public void setDamaged(Boolean damaged) {
		this.damaged = damaged;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	@ManyToOne
	public Producer getProducer() {
		return producer;
	}
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	@OneToOne
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

	
}
