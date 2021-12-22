package com.mutuelle.helpers;

import javafx.scene.control.Alert;

public class AlertHelper {

	   
	    public static void SuccessAlert(String info,String message){
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Success");
	        alert.setHeaderText(info);
	        alert.setContentText(message);
	        alert.showAndWait();
	  
	   }
	    
	    
	    public static void ErrorAlert(String info,String message){
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Faill");
	        alert.setHeaderText(info);
	        alert.setContentText(message);
	        alert.showAndWait();

	    }
	
	
}
