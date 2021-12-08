package com.mutuelle.Impl;

import java.util.List;

import com.mutuelle.interfaces.OfficerInterface;
import com.mutuelle.models.Officer;

public class OfficerImpl implements OfficerInterface{
	
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

}
