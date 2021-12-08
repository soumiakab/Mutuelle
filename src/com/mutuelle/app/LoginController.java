package com.mutuelle.app;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InaccessibleObjectException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutuelle.Impl.OfficerImpl;
import com.mutuelle.models.Officer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LoginController implements Initializable{
	@FXML
	private Button loginButton;
	
	@FXML
	private TextField emailInput;
	
	@FXML
	private Label emailErr;
	
	@FXML
	private TextField passwordInput;
	
	@FXML
	private Label passwordErr;
	
	private OfficerImpl officerImpl;
	private List<Officer> Officers;
	public int validateInputs() {
		int valid=0;
		if(emailInput.getText().length()==0) {
			emailErr.setText("* please enter your email");
			valid++;
		}else {
			if(passwordInput.getText().length()==0) {
				emailErr.setText("");
				passwordErr.setText("* please enter the password");
				valid++;
			}
		}
		return valid;
		
	}
	public void loadUsersData() {
		ObjectMapper objectMapper = new ObjectMapper();
		  try {
	            InputStream inputStream = new FileInputStream(new File("C:/Users/adm/eclipse-workspace/MutuelleCentralisée/src/json/user.json"));
	            TypeReference<List<Officer>> typeReference = new TypeReference<List<Officer>>() {};
	             Officers = objectMapper.readValue(inputStream, typeReference);	          
	        }catch(FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (StreamReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatabindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  catch (InaccessibleObjectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void sinup() {
		if(validateInputs()==0) {
			int findEmail=officerImpl.searchOfficerByEmail(emailInput.getText().toString(), this.Officers);
			if(findEmail!=-1) {
				if(officerImpl.validateOfficerPassword(passwordInput.getText().toString(),findEmail,Officers)) {
					try {
						Parent root = (Pane)FXMLLoader.load(getClass().getResource("Mutuelle.fxml"));
						Stage window=(Stage)loginButton.getScene().getWindow();					
						window.setScene(new Scene(root,1000,500));
						window.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}else {
					emailErr.setText("");
					passwordInput.setText("");
					passwordErr.setText("* password invalid");
				}
			}
			else {
				passwordErr.setText("");
				passwordInput.setText("");
				emailErr.setText("*email not found");
			}
		}
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		officerImpl=new OfficerImpl();
		loadUsersData();
		
	}

}
