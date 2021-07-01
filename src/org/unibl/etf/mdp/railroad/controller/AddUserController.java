package org.unibl.etf.mdp.railroad.controller;

import java.util.ArrayList;

import org.unibl.etf.mdp.railroad.model.TrainStation;
import org.unibl.etf.mdp.railroad.model.User;
import org.unibl.etf.mdp.railroad.rest.TrainStations;
import org.unibl.etf.mdp.railroad.soap.ClientSOAP;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddUserController {
	
	private Stage stage;
	private ArrayList<TrainStation> trainStations;
	@FXML
	private TextField firstNameField, lastNameField, usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private ComboBox<TrainStation> trainStationComboBox;
	
	public void initialize(Stage stage) {
		this.stage = stage;
		this.trainStations = TrainStations.getAllStations();
		trainStationComboBox.getItems().addAll(trainStations);
		
	}
	
	public void cancel() {
		stage.close();
	}
	
	public void add() {
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String username = usernameField.getText();
		String password = passwordField.getText();
		TrainStation selectedStation = trainStationComboBox.getSelectionModel().getSelectedItem();
		if ("".equals(firstName) || "".equals(lastName) || "".equals(username) || selectedStation == null) return;
		if (ClientSOAP.createUser(new User(1, firstName, lastName, selectedStation.getId(), password, username))) {
			stage.close();
		}
		
	}
	
	

}
