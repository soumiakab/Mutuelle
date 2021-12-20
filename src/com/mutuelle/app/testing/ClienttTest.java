package com.mutuelle.app.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mutuelle.Impl.ClientImpl;
import com.mutuelle.models.Client;

class ClienttTest {

	ClientImpl clientImpl;
	List<String> stringList;
	
	@BeforeEach 
	public void initClientImpl() {
		clientImpl= new ClientImpl();
		clientImpl.clientInit();
	}
	
	
	
	@Test
	public void getClient() throws Exception {

		    try {
		    	
		        Client client =clientImpl.getClient();
		        assertNotNull(client); //check if the object is != null
		        //check if the returned object is of class Expression.
		        assertTrue(client instanceof Client);
		    } catch(Exception e){
		        // let the test fail, if your function throws an Exception.
		        fail("got Exception, i want an Expression");
		     }
		
	  }
	
	
	
	@Test
	public void companiesTest() {
		assertTrue( !clientImpl.companies().isEmpty());
		//test equal
		//assertArrayEquals(stringList,clientImpl.companies());
	}
	
	
	@Test
	public  void filterByCompanyName() {
		
		clientImpl.filterByCompanyName("Avamm");
		assertTrue( !clientImpl.companies().isEmpty());
	}
	
	
	@Test
	public  void filterClientList() {
		clientImpl.filterClientList(" where identity like '%d%'");
		
		assertTrue( !clientImpl.clients().isEmpty());
	}
	
	
	@Test
	public void addClient() {
		int size=clientImpl.clients().size();
		clientImpl.setClient( "az12345678","company","22 db ffjfjf","firstName","lastNAme","+212645456776","fjjf@gmail.com","ZE234567","2021-12-22","2021-04-12");
		
		assertEquals( clientImpl.clients().size(),(size+1));
	}
	
	
	
	
	@Test
	public void clients() {
		
		assertTrue( !clientImpl.companies().isEmpty());
	}
	
	
	
}
