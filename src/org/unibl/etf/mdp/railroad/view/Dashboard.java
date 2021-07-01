package org.unibl.etf.mdp.railroad.view;

import org.unibl.etf.mdp.railroad.controller.DashboardController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Dashboard extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		try {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/unibl/etf/mdp/railroad/view/fxml/central-main.fxml"));
        stage.setTitle("Railroad admin");
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        DashboardController controller = loader.getController();
        controller.initialize(stage);
        stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

    public static void main(String[] args) {
        try {
            launch(args);

        } catch (Exception ex) {
           System.out.println(ex.getMessage());
          	ex.printStackTrace();
        }
    }
}
