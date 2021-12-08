package com.mutuelle.interfaces;

import java.util.List;

import com.mutuelle.models.Officer;

public interface OfficerInterface {

	Integer searchOfficerByEmail(String email, List<Officer> officers);

	boolean validateOfficerPassword(String password, int index, List<Officer> officers);

}
