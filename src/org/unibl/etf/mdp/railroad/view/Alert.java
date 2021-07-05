package org.unibl.etf.mdp.railroad.view;

import java.util.logging.Level;

import org.unibl.etf.mdp.railroad.controller.AlertController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alert {
	
	public void display(String message) {
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/mdp/railroad/view/fxml/alert.fxml"));
	        Stage stage = new Stage();
	        stage.setTitle("Railroad");
	        Scene scene = new Scene(loader.load());
	        stage.setScene(scene);
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.setHeight(400);
	        stage.setWidth(600);
	        stage.setResizable(false);
	        AlertController controller = loader.getController();
	        controller.initialize(stage, message);
	        stage.showAndWait();
	    	} catch(Exception e) {
	    		Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
	    	}
	    }
	}

