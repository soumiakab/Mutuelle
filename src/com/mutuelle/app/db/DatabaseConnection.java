package com.mutuelle.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	  public static Connection con;
	  
	    public static Connection getConnection(){
	    	
	        String databaseName = "mutuelledb";
	        String user = "root";
	        String password = "soumia";
	        String url = "jdbc:mysql://localhost/" + databaseName;
	        
	        try{
	        	
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection(url,user,password); 
	            return con;
	            
	        }catch (Exception e){
	        	
	            e.getStackTrace();
	            e.getCause();
	        }
	        
	       return null;
	    }
	    
	    public static void closeConnection() {
	    	if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
	    }
	    


}
