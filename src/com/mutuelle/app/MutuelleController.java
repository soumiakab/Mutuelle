package com.mutuelle.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InaccessibleObjectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutuelle.Impl.ClientImpl;
import com.mutuelle.models.Client;
import com.mutuelle.models.CodePays;
import com.mutuelle.models.Officer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class MutuelleController implements Initializable {
	
	
	
	@FXML
	private Button AddClient;
	@FXML
	private TextField workBadgeNumberClient;
	@FXML
	private TextField companyNameClient;
	@FXML
	private TextField lastNameClient;
	@FXML
	private TextField firstNameClient;
	@FXML
	private TextField phoneClient;
	@FXML
	private TextField cinClient;
	@FXML
	private TextField adsressClient;
	@FXML
	private TextField emailClient;
	@FXML
	private DatePicker dateDebutClient;
	
	@FXML
	private Label errorsLabel;
	
	@FXML
	private Label cinPassLabel;
	
	@FXML
	private RadioButton cinRadioB;
	
	 @FXML
	 private TableView<Client> tableClientList;
	 
	 @FXML
	private TableColumn<Client,String> firstName;
	 
	 @FXML
		private TableColumn<Client,String> workBadgeNumber;
	 
	 @FXML
		private TableColumn<Client,String> lastName;
	 @FXML
		private TableColumn<Client,String> phone;
	 @FXML
		private TableColumn<Client,String> email;
	 @FXML
		private TableColumn<Client,String> cin;
	 @FXML
		private TableColumn<Client,String> address;
	 @FXML
		private TableColumn<Client,String> companyName;
	 
	 boolean filledCin=true;
	 
	 @FXML
		private ComboBox<CodePays> codepaysChoose;
	 	List<CodePays> codepays;
	 
	 ObservableList<CodePays> cdpays = FXCollections.<CodePays>observableArrayList();
	 
	 @FXML
	 public void onCheckCinRdB() {
		 cinPassLabel.setText("CIN:");
		 filledCin=true;
	 }
	 
	 @FXML
	 public void onCheckPassportRdB() {
		 cinPassLabel.setText("Passport:");
		 filledCin=false;
	 }
	 
	 ClientImpl clientImp;
	 ObservableList<Client> data = FXCollections.<Client>observableArrayList();
	
	 public void initList() {
		 clientImp= new ClientImpl(new ArrayList<Client>());
		 clientImp.clientInit();
		 fillList();
	 }
	
	 
	 @FXML
	 public void addToList(ActionEvent event) {
		 int errcmp=validateInputs();
		 if(errcmp==0) {
			 clientImp.setClient(workBadgeNumberClient.getText().toString(),companyNameClient.getText().toString(),adsressClient.getText().toString(),firstNameClient.getText().toString(),lastNameClient.getText().toString(),phoneClient.getText().toString(),emailClient.getText().toString(),cinClient.getText().toString());
			 fillList();
			 viderInputs();
		 }
		 //tableClientList.setItems(data);
	 }
	 
	 public void fillList() {
		 data.clear();
		 data.addAll(clientImp.clients());
	 }
	 
	 
	 public Integer validateInputs() {
		 int compErr=0;
		 StringBuilder err = new StringBuilder();
		// err.delete(0, err.length());
			if(workBadgeNumberClient.getText().isBlank() == true || emailClient.getText().isBlank() == true || adsressClient.getText().isBlank() == true || cinClient.getText().isBlank() == true || phoneClient.getText().isBlank() == true || firstNameClient.getText().isBlank() == true || lastNameClient.getText().isBlank() == true || companyNameClient.getText().isBlank() == true || dateDebutClient.getValue().toString().isBlank() == true) {
				  err.append("\nplease fill all inputs \n");
				compErr++;
			}
			if(workBadgeNumberClient.getText().length() != 10 ) {
				  err.append("the workBadge Number must contains 10 Letters\n");				
				compErr++;
			}
			if(companyNameClient.getText().length() > 50) {
				compErr++;
				 err.append("the company name can not have more then 50 L\n");
			}
			if(firstNameClient.getText().length() > 50) {
				compErr++;
				 err.append("the firstname can not have more then 50 L\n");
			}
			if(lastNameClient.getText().length() > 50) {
				compErr++;
				 err.append("the lastname can not have more then 50 L\n");
			}
			
			if(!phoneClient.getText().matches("[+]\\d{10}(\\d{3}?)")) {
				
				 err.append("the phone number is invalid\n");
				 compErr++;
			}
			if(!emailClient.getText().matches("^(.+)@(.+)$")) {
	            compErr++;
	            err.append("email not correct");
	        }
			if(filledCin && cinClient.getText().length() > 8) {
				compErr++;
				 err.append("the cin number can not have more then 8 N\n");
				
			}
		
			if(filledCin && !cinClient.getText().matches("[a-zA-Z]{2}\\d{6}")) {
				compErr++;
				 err.append("the cin must be 2 numbers and 6 L");
			}
			if(!filledCin && !cinClient.getText().matches("[a-zA-Z]{2}\\d{7}")) {
				compErr++;
				 err.append("the Passport must be 2 numbers and 7 L\n");
			}
			
			errorsLabel.setText(err.toString());
			return compErr;
	 }
	 
	 public void loadpayscodes() {
		 	codepays=new ArrayList<CodePays>();
			ObjectMapper objectMapper = new ObjectMapper();
			  try {
		            InputStream inputStream = new FileInputStream(new File("C:/Users/adm/eclipse-workspace/MutuelleCentralisée/src/json/codepays.json"));
		            TypeReference<List<CodePays>> typeReference = new TypeReference<List<CodePays>>() {};
		            codepays = objectMapper.readValue(inputStream, typeReference);	          
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
			  
			  
			  cdpays.addAll(codepays);
			  
			  codepaysChoose.getItems().addAll(codepays);
		}
	 public void viderInputs(){
		 
		workBadgeNumberClient.clear();
		emailClient.clear();
		adsressClient.clear();
		cinClient.clear();
		phoneClient.clear();
		firstNameClient.clear();
		lastNameClient.clear();
		companyNameClient.clear();
		dateDebutClient.getEditor().clear();
		
	 }
	 
	 
	 public void changeCodePays() {
		//System.out.println("\n"+codepaysChoose.getValue());
		 phoneClient.setText(codepaysChoose.getValue().toString());
	 }
	 
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initList();
		workBadgeNumber.setCellValueFactory(new PropertyValueFactory<Client, String>("workBadgeNumber"));
		firstName.setCellValueFactory(new PropertyValueFactory<Client, String>("firstname"));
		lastName.setCellValueFactory(new PropertyValueFactory<Client, String>("lastname"));
		phone.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
		email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
		cin.setCellValueFactory(new PropertyValueFactory<Client, String>("cin"));
		address.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
		companyName.setCellValueFactory(new PropertyValueFactory<Client, String>("companyName"));
		tableClientList.setItems(data);
		loadpayscodes();
		cinRadioB.setSelected(true);
	}
	


	
	

	   
}
