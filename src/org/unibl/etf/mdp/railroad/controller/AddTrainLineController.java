package org.unibl.etf.mdp.railroad.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.unibl.etf.mdp.railroad.model.TrainLine;
import org.unibl.etf.mdp.railroad.model.TrainStation;
import org.unibl.etf.mdp.railroad.model.TrainStop;
import org.unibl.etf.mdp.railroad.rest.TrainLines;
import org.unibl.etf.mdp.railroad.rest.TrainStations;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddTrainLineController {
	
	private Stage stage;
	private ArrayList<TrainStation> trainStations;
	
	@FXML
	private ComboBox<TrainStation> startComboBox, destinationComboBox, trainStopComboBox;
	
	@FXML
	private ComboBox<Integer> startHours, startMinutes, arrivalHours, arrivalMinutes, trainStopHours, trainStopMinutes;
	
	@FXML
	private DatePicker startDate, arrivalDate, trainStopDate;
	
	@FXML
	private VBox stopsVBox;
	
	private ArrayList<TrainStop> trainStops;
	
	public void initialize(Stage stage) {
		this.stage = stage;
		this.trainStations = TrainStations.getAllStations();
		startComboBox.getItems().addAll(trainStations);
		destinationComboBox.getItems().addAll(trainStations);
		trainStopComboBox.getItems().addAll(trainStations);
		
		List<Integer> hours  = Arrays.stream(IntStream.rangeClosed(0, 23).toArray()).boxed().collect( Collectors.toList());
		startHours.getItems().addAll(hours);
		arrivalHours.getItems().addAll(hours);
		trainStopHours.getItems().addAll(hours);
		
		List<Integer> minutes  = Arrays.stream(IntStream.rangeClosed(0, 59).toArray()).boxed().collect( Collectors.toList());
		startMinutes.getItems().addAll(minutes);
		arrivalMinutes.getItems().addAll(minutes);
		trainStopMinutes.getItems().addAll(minutes);
		
		trainStops = new ArrayList<TrainStop>();
	}
	
	public void addLine() {
		TrainStation start = startComboBox.getSelectionModel().getSelectedItem();
		TrainStation destination = destinationComboBox.getSelectionModel().getSelectedItem();
		LocalDate startLocalDate = startDate.getValue();
		Instant instant = Instant.from(startLocalDate.atStartOfDay(ZoneId.systemDefault()));
		String startTime = Date.from(instant).toString();
		LocalDate arrivalLocalDate = arrivalDate.getValue();
		Instant arrivalInstant = Instant.from(arrivalLocalDate.atStartOfDay(ZoneId.systemDefault()));
		String arrivalTime = Date.from(arrivalInstant).toString();
		if (start == null || destination == null || "".equals(startTime) || "".equals(arrivalTime)) return;
		ArrayList<TrainStop> stops = new ArrayList<>();
		stops.add(new TrainStop(start, startTime, false, null));
		stops.addAll(trainStops);
		stops.add(new TrainStop(destination, arrivalTime, false, null));
		TrainLine trainLine = new TrainLine(start, destination, startTime, arrivalTime, stops);
		TrainLine response = TrainLines.create(trainLine);
		if (response != null) {
			stage.close();
		}
	}
	
	public void addStop() {
		TrainStation trainStation = trainStopComboBox.getSelectionModel().getSelectedItem();
		LocalDate localDate = trainStopDate.getValue();
		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		Date date = Date.from(instant);
		Integer hours = trainStopHours.getSelectionModel().getSelectedItem();
		Integer minutes = trainStopMinutes.getSelectionModel().getSelectedItem();
		if (trainStation == null || date == null || hours == null || minutes == null) return;
		trainStops.add(new TrainStop(trainStation, createTime(date, hours, minutes), false, null));
		renderTrainStops();
	}
	
	public String createTime(Date date, Integer hours, Integer minutes) {
		Date result = date;
		result.setHours(hours);
		result.setMinutes(minutes);
		return result.toString();
	}
	
	private void renderTrainStops() {
		stopsVBox.getChildren().clear();
		trainStops.stream().forEach(trainStop -> stopsVBox.getChildren().add(createTrainStop(trainStop)));
	}
	
	private Label createTrainStop(TrainStop trainStop) {
		Label label = new Label();
		label.setFont(Font.font("Monospaced", 16.0));
		label.setText(String.format("%-100.100s %s", trainStop.getTrainStation().toString(), trainStop.getExpectedTime()));
		return label;
	}

}
