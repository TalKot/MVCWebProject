package com.shenkar.model;

public class User {
	
	private String firstName;
	private String lastName;
	private int id; 
	private int phoneNumber;
	private String email;
	private String password;
	
	public User(){}

	public User(String firstName, String lastName, int id, int phoneNumber, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}



	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + ", id=" + id + ", phoneNumber=" + phoneNumber + ", email=" + email;
	}


}
