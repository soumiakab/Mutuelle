package com.mutuelle.models;

public class Person {

	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String cin;
	
	public Person() {
		
	}
	
	public Person(String firstname,String lastname,String phone,String email,String cin) {
		this.firstName = firstname;
		this.lastName = lastname;
		this.phone = phone;
		this.email = email;
		this.setCin(cin);
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastname) {
		this.lastName = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Person [  firstname=" + firstName + ", lastname=" + lastName + ", phone=" + phone
				+ ", address=" + email + "]";
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}
}
