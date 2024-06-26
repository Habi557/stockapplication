package com.zensar.springBoot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "STOCKS")
public class StockEntity {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private int id;
@Column(name="Stock_Name")
 private String name;
@Column(name="Market")
 private String market;
@Column(name="Stock_price")
 private double amount;
public StockEntity(String name, String market, double amount) {
	super();
	this.name = name;
	this.market = market;
	this.amount = amount;
}
public StockEntity() {
	super();
	// TODO Auto-generated constructor stub
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getMarket() {
	return market;
}
public void setMarket(String market) {
	this.market = market;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
@Override
public String toString() {
	return "Dto [name=" + name + ", market=" + market + ", amount=" + amount + "]";
}
 
}
