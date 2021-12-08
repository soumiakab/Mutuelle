package com.mutuelle.models;

public class Client extends Person {
	private String workBadgeNumber;
	private String companyName;
	private String address;
	
	public Client() {
		super();
	}
	
	public Client(String workBadgeNumber,String companyName,String address,String firstname,String lastname,String phone,String email,String cin) {
		super( firstname, lastname, phone, email, cin);
		this.setWorkBadgeNumber(workBadgeNumber);
		this.setCompanyName(companyName);
		this.setAddress(address);
	}

	public String getWorkBadgeNumber() {
		return workBadgeNumber;
	}

	public void setWorkBadgeNumber(String workBadgeNumber) {
		this.workBadgeNumber = workBadgeNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
