package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Gun {
	
	private long id;
	
	private String name;
	private String productionDate;
	private boolean isDamaged;
	private double weight;
	private Producer producer;
	
	public Gun() {
		super();
	}
	public Gun(String name, String productionDate, boolean isDamaged, double weight) {
		super();
		this.name = name;
		this.productionDate = productionDate;
		this.isDamaged = isDamaged;
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
	public boolean isDamaged() {
		return isDamaged;
	}
	public void setDamaged(boolean isDamaged) {
		this.isDamaged = isDamaged;
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
}
