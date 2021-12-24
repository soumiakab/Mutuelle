package com.mutuelle.app.views;


import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.mutuelle.Impl.OfficerImpl;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
	private PasswordField passwordInput;
	
	@FXML
	private Label passwordErr;
	
	private OfficerImpl officerImpl;
	
	//private OfficerDao officerDao;
	
	
	//private List<Officer> Officers;
	
	 //***********logger************//
	Logger logger = Logger.getLogger(LoginController.class.getCanonicalName());
	
	
	public int validateInputs() {
		int valid=0;
		if(emailInput.getText().trim().length()==0) {
			emailErr.setText("* please enter your email");
			valid++;
		}else {
			if(passwordInput.getText().trim().length()==0) {
				emailErr.setText("");
				passwordErr.setText("* please enter the password");
				valid++;
			}
		}
		return valid;
		
	}
	
	
	
//	public void loadUsersData() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		  try {
//	            InputStream inputStream = new FileInputStream(new File("C:/Users/adm/eclipse-workspace/MutuelleCentralisée/src/json/user.json"));
//	            TypeReference<List<Officer>> typeReference = new TypeReference<List<Officer>>() {};
//	             Officers = objectMapper.readValue(inputStream, typeReference);	          
//	        }catch(FileNotFoundException e) {
//	            e.printStackTrace();
//	        } catch (StreamReadException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (DatabindException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		  catch (InaccessibleObjectException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
	
	public void sinup() {
		if(validateInputs()==0) {
//			int findEmail=officerImpl.searchOfficerByEmail(emailInput.getText().toString(), this.Officers);
//			if(findEmail!=-1) {
//				if(officerImpl.validateOfficerPassword(passwordInput.getText().toString(),findEmail,Officers)) {
//					try {
//						Parent root = (Pane)FXMLLoader.load(getClass().getResource("App.fxml"));
//						Stage window=(Stage)loginButton.getScene().getWindow();					
//						window.setScene(new Scene(root,961,596));
//						window.show();
//					} catch(Exception e) {
//						e.printStackTrace();
//					}
//				}else {
//					emailErr.setText("");
//					passwordInput.setText("");
//					passwordErr.setText("* password invalid");
//				}
//				
//				
//			}
//			else {
//				passwordErr.setText("");
//				passwordInput.setText("");
//				emailErr.setText("*email not found");
//			}
			
		boolean log =officerImpl.Login(emailInput.getText().toString(), passwordInput.getText().toString());
		if(log) {
			try {
				 logger.info("User loggins successfuly "+emailInput.getText().toString());
				Parent root = (Pane)FXMLLoader.load(getClass().getResource("App.fxml"));
				Stage window=(Stage)loginButton.getScene().getWindow();					
				window.setScene(new Scene(root,961,596));
				window.show();
			} catch(Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}else{
			emailErr.setText("");
			passwordInput.setText("");
			passwordErr.setText("* email  or password invalid");
			logger.error("invalid password or email");
		}
		}
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String log4jConfPath = "src/com/mutuelle/app/logs/javaLogger.properties";
		PropertyConfigurator.configure(log4jConfPath);
		 logger.info("app opned");
		officerImpl=new OfficerImpl();
		//officerDao= new OfficerDao();
		//loadUsersData();
		
	}

}
