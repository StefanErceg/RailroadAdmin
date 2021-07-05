package org.unibl.etf.mdp.railroad.controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.unibl.etf.mdp.railroad.model.TrainStation;
import org.unibl.etf.mdp.railroad.model.User;
import org.unibl.etf.mdp.railroad.rest.TrainStations;
import org.unibl.etf.mdp.railroad.soap.ClientSOAP;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UsersController {
	
	private Stage stage;
	private ArrayList<User> users;
	private ArrayList<TrainStation> trainStations;
	
	@FXML
	private VBox usersVBox;
	
	public void initialize(Stage stage) {
		this.stage = stage;
		this.users = ClientSOAP.getUsers();
		this.trainStations = TrainStations.getAllStations();
		renderUsers();
		
	}
	
	public void close() {
		stage.close();
	}
	
	private void renderUsers() {
		usersVBox.getChildren().clear();
		this.users.forEach((user) -> usersVBox.getChildren().add(createRow(user)));
	}
	
	private HBox createRow (User user) {
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER_LEFT);
		hbox.setSpacing(50);
		hbox.setPadding(new Insets(5));
		Button deactivateButton = new Button("Deactivate");
		deactivateButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				boolean success = ClientSOAP.deactivate(user.getUsername());
				if (success) {
					users = new ArrayList<User>(users.stream().filter((element) -> !(element.getUsername().equals(user.getUsername()))).collect(Collectors.toList()));
					renderUsers();
				}
			}
		});
		hbox.getChildren().add(deactivateButton);
		TrainStation trainStation = trainStations.stream().filter(ts -> ts.getId().equals(user.getLocationId())).findFirst().orElse(null);
		hbox.getChildren().add(new Label(String.format("%40.40s %40.40s %40.40s %s",user.getUsername(), user.getFirstName(), user.getLastName(), trainStation)));
		return hbox;
	}

}
