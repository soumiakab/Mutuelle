package com.mutuelle.Impl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.mutuelle.app.dao.OfficerDao;
import com.mutuelle.interfaces.OfficerInterface;
import com.mutuelle.models.Officer;



public class OfficerImpl implements OfficerInterface{
	
	private OfficerDao officerDao;
	
	@Override
	public Integer searchOfficerByEmail(String email,List<Officer> officers) {
		int find =-1;
		for(Officer officer:officers) {
			if(officer.getEmail().equals(email)) {
				find=officers.indexOf(officer);
				break;
			}
		}
		return find;
	}
	
	
	@Override
	public boolean validateOfficerPassword(String password,int index,List<Officer> officers) {
		boolean equal =false;
		if(officers.get(index).getPassword().equals(password)) {
			equal=true;
		}
		
		return equal;
	}
	
	
	@Override
	public boolean Login(String email ,String password) {
		officerDao= new OfficerDao();
		boolean equal =false;
		equal=officerDao.login(email, password);
		
		return equal;
	}
	
	public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(11));
    }

    public static boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

}
