package com.mutuelle.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.mutuelle.app.dao.interfaces.DaoInterface;
import com.mutuelle.app.db.DatabaseConnection;
import com.mutuelle.helpers.SqlQueries;
import com.mutuelle.models.Officer;

public class OfficerDao implements DaoInterface<Officer> {

	
	private static final String login_Query= "SELECT * FROM officers WHERE email= ?";
	Connection conn = null;
	Officer officer;
	private PreparedStatement stmt = null;
	
	
	public boolean login(String email, String password){

        try {
        	conn = DatabaseConnection.getConnection();
			stmt = conn.prepareStatement(login_Query);
			 officer = new Officer();
			stmt.setString(1,email);
			 ResultSet resultSet = stmt.executeQuery();
	            if (resultSet.next()) {
	            boolean verify=	verifyHash(password, resultSet.getString("password"));
	                return verify;
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

	
	public  String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(11));
    }

    public  boolean verifyHash(String password, String hash) {
    	
        return BCrypt.checkpw(password, hash);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Officer> findAll() {
		
		List<Officer> officers = new ArrayList<Officer>();
		
		try {
			conn = DatabaseConnection.getConnection();
			stmt = conn.prepareStatement(SqlQueries.getAll("officers"));
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				 officer = new Officer();
				
//				Officer.set
//				Officer.setId(rs.getInt("id"));
//				Officer.setName(rs.getString("name"));
//				Officer.setTel(rs.getString("tel"));
//				Officer.setPasswd(rs.getString("passwd"));
				
				officers.add(officer);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return officers;
	}

	@Override
	public Officer findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Officer findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Officer object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Officer object) {
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

}
