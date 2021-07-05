package org.unibl.etf.mdp.railroad.controller;

import java.util.ArrayList;

import org.unibl.etf.mdp.railroad.model.TrainLine;
import org.unibl.etf.mdp.railroad.rest.TrainLines;
import org.unibl.etf.mdp.railroad.view.AddTrainLine;
import org.unibl.etf.mdp.railroad.view.Alert;

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

public class TimetableController {
	
	@FXML
	private VBox trainLinesVBox;
	
	private Stage stage;
	private ArrayList<TrainLine> trainLines;
	
	public void initialize(Stage stage) {
		this.stage = stage;
		this.trainLines = TrainLines.getAllLines();
		renderTrainLines();
		
	}

	
	public void close() {
		stage.close();
	}
	
	public void addLine() {
		new AddTrainLine().display();
	}
	
	private void renderTrainLines() {
		trainLinesVBox.getChildren().clear();
		this.trainLines.forEach((trainLine) -> trainLinesVBox.getChildren().add(createRow(trainLine)));
	}
	
	private void deleteTrainLine(HBox hbox) {
		this.trainLinesVBox.getChildren().remove(hbox);
		new Alert().display("Train line successfully deleted!");
	}
	
	private HBox createRow (TrainLine trainLine) {
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER_LEFT);
		hbox.setSpacing(50);
		hbox.setPadding(new Insets(5));
		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (TrainLines.delete(trainLine.getId())) {
					deleteTrainLine(hbox);
				}
				
			}
		});
		hbox.getChildren().add(deleteButton);
		trainLine.getStops().stream().forEach((stop) -> hbox.getChildren().add
				(new Label(stop.getTrainStation().getName() + ", " + stop.getTrainStation().getCity().getName() + "  (" 
							+ stop.getExpectedTime() + ", " + ( stop.getActualTime() != null ? stop.getActualTime() : " *") + ")")));
		return hbox;
	}
	

}
