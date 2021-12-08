module MutuelleCentralisée {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.core;
	requires javafx.base;
	requires java.desktop;
	requires javafx.graphics;
	
	opens com.mutuelle.app to javafx.graphics, javafx.fxml;
	exports com.mutuelle.models;
}
