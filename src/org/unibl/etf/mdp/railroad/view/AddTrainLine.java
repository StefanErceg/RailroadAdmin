package org.unibl.etf.mdp.railroad.view;
import org.unibl.etf.mdp.railroad.controller.AddTrainLineController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddTrainLine {

	public void display() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/mdp/railroad/view/fxml/add-train-line.fxml"));
			Stage stage = new Stage();
	        stage.setTitle("Railroad admin - add train line");
	        stage.initModality(Modality.APPLICATION_MODAL);
			Scene scene = new Scene(loader.load());
			stage.setScene(scene);
			AddTrainLineController controller = loader.getController();
			controller.initialize(stage);
			stage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
