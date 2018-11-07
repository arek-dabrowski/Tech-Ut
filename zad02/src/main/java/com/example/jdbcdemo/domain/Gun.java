package com.example.jdbcdemo.domain;

public class Gun {

	private long id;
	private String name;
	private String productionDate;
	private boolean isDamaged;
	private double weight;
	
	
	public Gun() {
	}
	
	public Gun(String name, String productionDate, boolean isDamaged, double weight) {
		super();
		this.name = name;
		this.productionDate = productionDate;
		this.isDamaged = isDamaged;
		this.weight = weight;
	}
	
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
	public void setProductionDate(String date) {
		this.productionDate = date;
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
	
}
