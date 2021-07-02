package org.unibl.etf.mdp.railroad.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertController {
	
	private Stage stage;
	@FXML
	private Label message;
	
	public void initialize(Stage stage, String message) {
		this.stage = stage;
		this.message.setText(message);
		
	}
	
	public void close() {
		stage.close();
	}

}
