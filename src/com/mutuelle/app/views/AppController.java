package com.mutuelle.app.views;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InaccessibleObjectException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutuelle.Impl.ClientImpl;
import com.mutuelle.app.dao.ClientDao;
import com.mutuelle.helpers.AlertHelper;
import com.mutuelle.helpers.ValidateInputs;
import com.mutuelle.models.Client;
import com.mutuelle.models.CodePays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;


public class AppController implements Initializable {
	
	
	
	@FXML
	private Button AddClient;
	
	@FXML
	private Button filterButton;
	
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
	private TextArea adsressClient;
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
	
	 @FXML
		private ComboBox<CodePays> codepaysChoose;
	 
	 @FXML
		private ComboBox<String> companiesNames;
	 	List<CodePays> codepays; 
		 boolean filledCin=true;
		 
	 	//filter textFiled
	 	@FXML
		private TextField cinInput;
	 	
	 	@FXML
		private TextField emailInput;
	 	
	 	@FXML
		private TextField firstnameInput;
	 	
	 	@FXML
		private TextField lastNamEInput;
	 	
		
 	 ClientImpl clientImp;
	 ObservableList<Client> data = FXCollections.<Client>observableArrayList();
	 ObservableList<String> cNames = FXCollections.<String>observableArrayList();
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
	 
	
	 //************************************** B add to list**************//
	 
	 @FXML
	 public void addToList(ActionEvent event) {
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		 Date date = new Date();
		 int errcmp=validateInputs();
		 if(errcmp==0) {
			 clientImp.setClient(workBadgeNumberClient.getText().toString(),companyNameClient.getText().toString(),adsressClient.getText().toString(),firstNameClient.getText().toString(),lastNameClient.getText().toString(),codepaysChoose.getValue().getDial_code()+phoneClient.getText().toString(),emailClient.getText().toString(),cinClient.getText().toString(),dateDebutClient.getEditor().getText(),dateFormat.format(date));
			if(clientImp.addClient()) {
			 fillList();
			 initUI();
			 viderInputs();
			 errorsLabel.setTextFill(Color.GREEN);
			 errorsLabel.setText("Inserer");
			 AlertHelper.SuccessAlert("Insert Client", "Done");
			 }
			else {
				AlertHelper.ErrorAlert("info", "message");
				 errorsLabel.setTextFill(Color.RED);
				 errorsLabel.setText("l email /cin /badgeNumber deja exist ");
			}
		 }
		 //tableClientList.setItems(data);
	 }
	 
	 
	 //************************************** E add to list **************//
	 

	 
	 //**************************************B validate inputs**************//
	 public Integer validateInputs() {
		 
		 errorsLabel.setTextFill(Color.RED);
		 int compErr=0;
		 StringBuilder err = new StringBuilder();
		 
			if(workBadgeNumberClient.getText().trim().isBlank() == true || emailClient.getText().trim().isBlank() == true || adsressClient.getText().trim().isBlank() == true || cinClient.getText().trim().isBlank() == true || phoneClient.getText().trim().isBlank() == true || firstNameClient.getText().trim().isBlank() == true || lastNameClient.getText().trim().isBlank() == true || companyNameClient.getText().trim().isBlank() == true || dateDebutClient.getValue().toString().trim().isBlank() == true) {
				  err.append("\nplease fill all inputs \n");
				compErr++;
			}
			else {
				
				
				if(!ValidateInputs.verifyMaxLength( workBadgeNumberClient.getText(), 10)  || !ValidateInputs.verifyMinLength( workBadgeNumberClient.getText(), 10)) {
					  err.append("the workBadge Number must contains 10 Letters\n");				
					compErr++;
				}
				
				if(!ValidateInputs.verifyMaxLength(companyNameClient.getText(),50)) {
					
					compErr++;
					 err.append("the company name can not have more then 50 L\n");
				}
				
				if(!ValidateInputs.verifyMaxLength(firstNameClient.getText(), 50)) {
					compErr++;
					 err.append("the firstname can not have more then 50 L\n");
				}
				
				if(!ValidateInputs.verifyMaxLength(lastNameClient.getText(), 50)) {
					compErr++;
					 err.append("the lastname can not have more then 50 L\n");
				}
				
				
				if(!ValidateInputs.verifyPhone(phoneClient.getText())) {					
					 err.append("the phone number is invalid\n");
					 compErr++;
				}
				
				if(! ValidateInputs.verifyEmail(emailClient.getText())) {
		            compErr++;
		            err.append("email not correct");
		        }
				
				if(filledCin && !ValidateInputs.verifyMaxLength(cinClient.getText(), 8)) {
					
					compErr++;
					 err.append("the cin number can not have more then 8 N\n");
					
				}
			
				if(filledCin && ! ValidateInputs.verifyCin( cinClient.getText())) {
					compErr++;
					 err.append("the cin must be 2 L and 6 numbers");
				}
				if(!filledCin && ! ValidateInputs.verifyPassport( cinClient.getText())) {
					
					compErr++;
					 err.append("the Passport must be 2 L and 7 numbers\n");
				}
			}
			errorsLabel.setText(err.toString());
			return compErr;
	 }
	 
	 //**************************************E validate inputs**************//
	 
	 
	 //*************B reset inputs*****//
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
	 
	//*************E reset inputs*****//
	 
	//*************B load paysCodes*****//
	 
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
	//*************E load paysCodes*****//
	 
	 
	 
	 
	 //***********B change selected code pays*********//
	 
	 public void changeCodePays() {
			//System.out.println("\n"+codepaysChoose.getValue());
			 phoneClient.setText(codepaysChoose.getValue().getDial_code());
	}
	 
	 //***********end change selected company**********//
	 
	 
	 
	 
	 
	 //***********B load and change selected company**********//
	 
	 public void loadComapiesNames() {
		 cNames.clear();
		 cNames.addAll(clientImp.companies());
		 companiesNames.getItems().add("All");
		 companiesNames.getItems().addAll(cNames);
	 }
	 
	 
	
	 
	 public void changeCompanyName() {
		// clientImp.filterByCompanyName(companiesNames.getValue().toString());
		//fillList();
		 filtrer();
	}
	 

//***********end load and change selected company**********//
	 
	 
	 
	 //**********B init and fill list ********************************** //
	 
	 public void fillList() {
		 data.clear();
		 data.addAll(clientImp.clients());
	 
		// ClientDao ClientDao= new ClientDao();
		 //data.addAll(ClientDao.findAll());
	 }
	 
	 
	 public void initList() {
			clientImp= new ClientImpl();
			 clientImp.clientInit();
			 fillList();
     }
	 //************and init ************//
		
	 
	
	 
	 //******************B Filter*************//
	 @FXML
	 public void filtrer() {
		 StringBuilder sb = new StringBuilder();
		 int cp=0;
		 if( !companiesNames.getSelectionModel().isEmpty() &&!companiesNames.getValue().toString().equalsIgnoreCase("All")) {
			 sb.append(" where ");
			 sb.append(" companyName LIKE '%"+companiesNames.getValue().toString().trim()+"%' ");
			 cp++;
		 }
		 if(!cinInput.getText().trim().isBlank()) {
			 if(cp>0) {
				 sb.append(" and ");
			 }
			 else{
			     sb.append(" where ");
			 }
			 sb.append(" identity LIKE '%"+cinInput.getText().trim()+"%' ");
			 cp++;
		 }
		 if(!emailInput.getText().trim().isBlank()) {
			 if(cp>0) {
				 sb.append(" and ");
			 }
			 else {
				 sb.append(" where ");
			 }
			 sb.append(" email LIKE '%"+emailInput.getText().trim()+"%' ");
			 cp++;
		 }
		 if(!firstnameInput.getText().trim().isBlank()) {
			 if(cp>0) {
				 sb.append(" and ");
			 }
			 else {
				 sb.append(" where ");
			 }
			 sb.append(" firstname LIKE '%"+firstnameInput.getText().trim()+"%' ");
			 cp++;
		 }
		 if(!lastNamEInput.getText().trim().isBlank()) {
			 if(cp>0) {
				 sb.append(" and ");
			 }
			 else {
				 sb.append(" where ");
			 }
			 sb.append(" lastname LIKE '%"+lastNamEInput.getText().trim()+"%' ");
			 
		 }
		 System.out.println(sb.toString());
		clientImp.filterClientList(sb.toString());
		 
		 fillList();
	}
	 
	//**********chartTab
	 
	 ClientDao clientdao = new ClientDao();
	 
	 @FXML
	 private CategoryAxis registerDate;
	 
	@FXML
    private  NumberAxis  numberClients;
	
	@FXML
	private BarChart<String, Integer> clientsChart;
	
	 private void initUI() {

		 clientsChart.getData().clear();
	        
		 registerDate.setLabel("Date");
		 
	    clientsChart.setTitle("Clients In Day");

	        var data = new XYChart.Series<String,Integer>();
	        
	        for(Map<String,Integer> elemt:clientdao.clientsInDay()) {
	        	
	        	 int r=(int) elemt.values().toArray()[0];
	        	 data.getData().add(new XYChart.Data<>(elemt.keySet().toString(),r));
	        }


	        clientsChart.getData().add(data);
	        clientsChart.setLegendVisible(false);

	        

	    }
	 //******end chart
	 
	 
	 
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
		loadComapiesNames();
		dateDebutClient.getEditor().setDisable(true);
		dateDebutClient.getEditor().setOpacity(1);
		dateDebutClient.setValue(LocalDate.now());
		codepaysChoose.getSelectionModel().selectFirst();
		initUI();
		cinRadioB.setSelected(true);
	}
	


	
	

	   
}
