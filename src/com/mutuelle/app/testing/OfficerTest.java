package com.mutuelle.app.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mutuelle.Impl.OfficerImpl;

class OfficerTest {
	OfficerImpl officerImpl;

	@BeforeEach 
	public void initOfficermpl() {
		officerImpl= new OfficerImpl();
	}
	
		
	@Test
	void lginTest() {
		assertTrue(officerImpl.Login("yc.a.mohammed@gmail.com", "12345678"));
		
	}

}
