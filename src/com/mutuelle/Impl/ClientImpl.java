package com.mutuelle.Impl;

import java.util.ArrayList;
import java.util.List;

import com.mutuelle.app.dao.ClientDao;
import com.mutuelle.interfaces.ClientInterface;
import com.mutuelle.models.Client;

public class ClientImpl implements ClientInterface{

	private List<Client> clients;
	private Client client;
	ClientDao ClientDao;
	
	
	public ClientImpl() {
		this.clients=new ArrayList<Client>();	
		this.client= new Client();
		ClientDao= new ClientDao();
	}
	
	public ClientImpl(List<Client> clients) {
		this();//ClientImpl()
		this.clients.addAll(clients);
		
	}
	
	
	public ClientImpl(int id,String workBadgeNumber,String companyName,String address,String firstname,String lastname,String phone,String email,String cin,String beginDate,String created_at) {
			this.client= new Client( id,workBadgeNumber, companyName, address, firstname, lastname, phone, email, cin, beginDate, created_at);
		
	}
	
	
	public  void clientInit() {
		ClientDao= new ClientDao();
		this.clients.addAll(ClientDao.findAll());				
	}
	
	public  void filterByCompanyName(String name) {
		this.clients.clear();
		this.clients.addAll(ClientDao.filterClientListByCompanyName(name));				
	}
	
	
	public  void filterClientList(String condition) {
		this.clients.clear();
		this.clients.addAll(ClientDao.filterClientList(condition));				
	}
	
	@Override
	public boolean addClient() {
		int inserted = ClientDao.insert(client);
		if(inserted == 1) {
			this.clients.clear();
			this.clients.addAll(ClientDao.findAll());
			return true;
		}else {
			return false;
		}
		//clients.add(this.client);
		
	}
	
	public List<String> companies() {
		return ClientDao.comapniesName();
	}
	
	@Override
	public List<Client> clients() {
		return this.clients;
	}
	
	@Override
	public Client getClient() {
		return client;
	}
	
	@Override
	public void setClient(String workBadgeNumber,String companyName,String address,String firstname,String lastname,String phone,String email,String cin,String beginDate,String created_at) {
		this.client= new Client();
		this.client.setWorkBadgeNumber(workBadgeNumber);
		this.client.setCompanyName(companyName);
		this.client.setAddress(address);
		this.client.setFirstname(firstname);
		this.client.setLastname(lastname);
		this.client.setPhone(phone);
		this.client.setEmail(email);
		this.client.setCin(cin);
		this.client.setBeginDate(beginDate);
		this.client.setCreatedAt(created_at);
		//System.out.println(this.client);
		
	}
}
