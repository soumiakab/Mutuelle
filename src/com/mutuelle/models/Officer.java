package com.mutuelle.models;

public class Officer extends Person {
	private String entity;
	private String password;
	
	public Officer() {
		super();
	}
	
	public Officer(String entity,String firstname,String lastname,String phone,String email,String cin) {
		super( firstname, lastname, phone, email,cin);
		this.setEntity(entity);
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
