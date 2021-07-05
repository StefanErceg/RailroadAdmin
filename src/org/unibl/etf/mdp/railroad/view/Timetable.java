package org.unibl.etf.mdp.railroad.view;

import java.util.logging.Level;

import org.unibl.etf.mdp.railroad.controller.TimetableController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Timetable {
	
	public void display() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/mdp/railroad/view/fxml/timetable.fxml"));
			Stage stage = new Stage();
	        stage.setTitle("Railroad admin - timetable");
	        stage.initModality(Modality.APPLICATION_MODAL);
			Scene scene = new Scene(loader.load());
			stage.setScene(scene);
			TimetableController controller = loader.getController();
			controller.initialize(stage);
			stage.showAndWait();
		} catch(Exception e) {
			Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
		}
	}

}
