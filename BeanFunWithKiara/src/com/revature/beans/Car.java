package com.revature.beans;

public class Car {
	
	// instance variables
	private String make;
	private String model;
	private int year;
	
	
	// constructor
	public Car (String make, String model, int year) {
		
		this.make = make;
		this.model = model;
		this.year = year;
		
	}


	// getters and setters
	public String getMake() {
		return make;
	}


	public void setMake(String make) {
		this.make = make;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	// toString method
	// overrides the method
	@Override
	public String toString() {
		return "Car [make=" + make + ", model=" + model + ", year=" + year + "]";
	}
	
	
	
	
	

}
