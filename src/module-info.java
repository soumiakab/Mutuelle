module MutuelleCentraliseeApp {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	requires javafx.graphics;
	requires org.junit.jupiter.api;
	requires junit;
	requires jBCrypt;
	requires java.mail;
	
	opens com.mutuelle.app to javafx.graphics, javafx.fxml;
	opens com.mutuelle.app.views to javafx.graphics, javafx.fxml;
	exports com.mutuelle.app.views;
	exports com.mutuelle.models;
	
}
