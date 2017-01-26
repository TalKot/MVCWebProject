package com.shenkar.model;

public class user {
	
	private String firstName;
	private String lastName;
	private int id; 
	private int phoneNumber;
	private String email;
	private String password;
	private String userAgent;
	


	public user(){}

	public user(String firstName, String lastName, int id, int phoneNumber, String email, String password,String userAgent) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.setUserAgent(userAgent);
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

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", password=" + password + ", userAgent=" + userAgent + "]";
	}

}
