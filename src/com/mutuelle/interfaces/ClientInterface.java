package com.mutuelle.interfaces;

import java.util.List;

import com.mutuelle.models.Client;

public interface ClientInterface {

	public boolean addClient();

	public void setClient(String workBadgeNumber, String companyName, String address, String firstname, String lastname,
			String phone, String email, String cin,String beginDate,String created_at);

	public Client getClient();

	public List<Client> clients();

}
