package org.unibl.etf.mdp.railroad.controller;

import org.unibl.etf.mdp.railroad.view.AddUser;
import org.unibl.etf.mdp.railroad.view.Timetable;
import org.unibl.etf.mdp.railroad.view.Users;

import javafx.stage.Stage;

public class DashboardController {
	
	private Stage stage;
	
	public void initialize(Stage stage) {
		this.stage = stage;
	}
	
	public void close() {
		stage.close();
	}
	
	public void openTimetable() {
		new Timetable().display();
	}
	
	public void showUsers() {
		new Users().display();
	}

	public void addUser() {
		new AddUser().display();
	}
}
