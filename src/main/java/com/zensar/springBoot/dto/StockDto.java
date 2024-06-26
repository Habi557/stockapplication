package com.zensar.springBoot.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public class StockDto {
 private int id;
 private String name;
 private String market;
 private double amount;
public StockDto(String name, String market, double amount) {
	super();
	this.name = name;
	this.market = market;
	this.amount = amount;
}
public StockDto() {
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
