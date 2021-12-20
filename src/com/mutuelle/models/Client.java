package com.mutuelle.models;

public class Client extends Person {
	private String workBadgeNumber;
	private String companyName;
	private String address;
	
	private String beginDate;
	private String created_at;
	private int id;
	
	public Client() {
		super();
	}
	
	public Client(int id,String workBadgeNumber,String companyName,String address,String firstname,String lastname,String phone,String email,String cin,String beginDate,String created_at) {
		super( firstname, lastname, phone, email, cin);
		this.setWorkBadgeNumber(workBadgeNumber);
		this.setCompanyName(companyName);
		this.setAddress(address);
		this.setId(id);
		this.setBeginDate(beginDate);
		this.setCreatedAt(created_at);
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
	
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	
	public String getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(String created_at) {
		this.created_at = created_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
