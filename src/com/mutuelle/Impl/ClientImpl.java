package com.mutuelle.Impl;

import java.util.ArrayList;
import java.util.List;

import com.mutuelle.models.Client;

public class ClientImpl {

	private List<Client> clients;
	private Client client;
	
	public ClientImpl(List<Client> clients) {
		this.clients=new ArrayList<Client>();
		this.client= new Client();
		this.clients.addAll(clients);
		
	}
	
	
	public ClientImpl(String workBadgeNumber,String companyName,String address,String firstname,String lastname,String phone,String email,String cin) {
			this.client= new Client( workBadgeNumber, companyName, address, firstname, lastname, phone, email, cin);
		
	}
	
	public  void clientInit() {
		this.clients=new ArrayList<Client>();
		this.clients.add( new Client("Test","Test","Test","Test","Test","Test","Test","Test"));
		this.clients.add( new Client("Test","Test","Test","Test","Test","Test","Test","Test"));
		this.clients.add( new Client("Test","Test","Test","Test","Test","Test","Test","Test"));
		this.clients.add( new Client("Test","Test","Test","Test","Test","Test","Test","Test"));
		
	}
	
	public void addClient() {
		clients.add(this.client);		
	}
	
	
	public List<Client> clients() {
		return this.clients;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(String workBadgeNumber,String companyName,String address,String firstname,String lastname,String phone,String email,String cin) {
		this.client.setWorkBadgeNumber(workBadgeNumber);
		this.client.setCompanyName(companyName);
		this.client.setAddress(address);
		this.client.setFirstname(firstname);
		this.client.setLastname(lastname);
		this.client.setPhone(phone);
		this.client.setEmail(email);
		this.client.setCin(cin);
		addClient();
	}
}
