package com.mutuelle.app.dao;

public class DAOFactory {

	public static ClientDao getClientDao() {
		return new ClientDao();
	}
	
	public static OfficerDao getOfficerDao() {
		return new OfficerDao();
	}
}
