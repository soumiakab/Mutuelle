package com.mutuelle.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mutuelle.app.dao.interfaces.DaoInterface;
import com.mutuelle.app.db.DatabaseConnection;
import com.mutuelle.helpers.SqlQueries;
import com.mutuelle.models.Client;
public class ClientDao implements DaoInterface<Client> {

	Connection conn = null;
	PreparedStatement stmt = null;
	List<Client> clients ;
	Client client;
	List<Map<String,Integer>> clientsInDay;
	
	
	
	
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	public void fillClientList(PreparedStatement stmt) {
		
		clients=new ArrayList<Client>();
		
		try {		
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Client client = new Client();				
				client.setId(rs.getInt("id"));
				client.setFirstname(rs.getString("firstname"));
				client.setLastname(rs.getString("lastname"));
				client.setPhone(rs.getString("phone"));
				client.setAddress(rs.getString("address"));
				client.setCin(rs.getString("identity"));
				client.setCompanyName(rs.getString("companyName"));
				client.setWorkBadgeNumber(rs.getString("workBadgeNumber"));
				client.setEmail(rs.getString("email"));
				clients.add(client);
			}
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	
	
	
	@Override
	public List<Client> findAll() {
			
		try {
			conn = DatabaseConnection.getConnection();
			stmt = conn.prepareStatement(SqlQueries.getAll("clients"));
			
		  fillClientList( stmt) ;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return this.clients;
	}

	@Override
	public Client findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	@Override
	public int insert(Client client) {
		if(ifExists(client.getCin(),client.getWorkBadgeNumber(),client.getEmail())==false) {
		   try {
	        	conn = DatabaseConnection.getConnection();
				stmt = conn.prepareStatement(SqlQueries.insert("clients",11));
				stmt.setInt(1,client.getId());
				stmt.setString(2,client.getFirstname());
				stmt.setString(3,client.getLastname());
				stmt.setString(4,client.getPhone());
				stmt.setString(5,client.getEmail());
				stmt.setString(6,client.getCin());
				stmt.setString(7,client.getAddress());
				stmt.setString(8,client.getCompanyName());
				stmt.setString(9,client.getWorkBadgeNumber());
				stmt.setString(10,client.getCreatedAt());
				stmt.setString(11,client.getBeginDate());
				 System.out.println(stmt);
				int result = stmt.executeUpdate();		
				
				return result;
				
	       }catch (SQLException e) {
	            // print SQL exception information
	       }
	       finally {
	        	closeStatement();
	        	DatabaseConnection.closeConnection();
	        }
		   }
		return 0;
	}
	
	
	public boolean ifExists(String cin,String WokBadgeN,String email) {
		try {
        	conn = DatabaseConnection.getConnection();
			stmt = conn.prepareStatement(SqlQueries.filter("clients", "where email like '"+email+"' or workBadgeNumber like '"+WokBadgeN+"' or identity like '"+cin+"' "));
			 System.out.println(stmt);
             ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
			
       }catch (SQLException e) {
            // print SQL exception information
       }
       finally {
        	closeStatement();
        	DatabaseConnection.closeConnection();
        }
		return false;
	}

	
	
	
	@Override
	public int update(Client object) {
		// TODO Auto-generated method stub
		return 0;
	}

	private  void closeStatement() {
		if (this.stmt != null) {
			try {
				this.stmt.close();
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	
	
	public List<String> comapniesName() {
		try {
			List<String> companiesName=new ArrayList<String>();
			conn = DatabaseConnection.getConnection();
			stmt = conn.prepareStatement(SqlQueries.getColumnData("clients"," distinct companyName "));
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				companiesName.add(rs.getString("companyName"));
			}
			
			return companiesName;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	public List<Client> filterClientList(String condition){
		try {
			conn = DatabaseConnection.getConnection();
			stmt = conn.prepareStatement(SqlQueries.filter("clients",condition));
			
			  fillClientList( stmt);
			  
			return this.clients;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	
	
	public List<Client> filterClientListByCompanyName(String cd){
		try {
			conn = DatabaseConnection.getConnection();
			if(cd.equalsIgnoreCase("All")){
				cd=" ";
			}
			else{
				cd=" where companyName='"+cd+"'";
			}
			stmt = conn.prepareStatement(SqlQueries.filter("clients",cd));
			
			  fillClientList( stmt);
			  
			return this.clients;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}
	
	
	
	public List<Map<String,Integer>> clientsInDay() {
		try {
			conn = DatabaseConnection.getConnection();
			
			stmt = conn.prepareStatement(SqlQueries.getColumnDataWithCondition("clients"," created_at , count(*) as nbClients "," Group By created_at "));
			
			clientsInDay=new ArrayList<Map<String,Integer>>();
			
				
			ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					Map<String,Integer> temp = new HashMap<String,Integer>();				
					temp.put(rs.getString("created_at"), rs.getInt("nbClients"));
					clientsInDay.add(temp);
				}
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		return clientsInDay;
		
	}
}
