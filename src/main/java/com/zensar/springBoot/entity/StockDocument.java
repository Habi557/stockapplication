package com.zensar.springBoot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="STOCKS")
public class StockDocument {
@Id	
private int id;	
 private String name;
 private String market;
 private double amount;
public StockDocument(String name, String market, double amount) {
	super();
	this.name = name;
	this.market = market;
	this.amount = amount;
}
public StockDocument(int id,String name, String market, double amount) {
	super();
	this.id=id;
	this.name = name;
	this.market = market;
	this.amount = amount;
}
public StockDocument() {
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
