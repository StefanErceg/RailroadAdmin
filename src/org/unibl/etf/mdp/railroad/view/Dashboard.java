package org.unibl.etf.mdp.railroad.view;

import java.util.logging.Level;

import org.unibl.etf.mdp.railroad.Configuration;
import org.unibl.etf.mdp.railroad.ErrorLog;
import org.unibl.etf.mdp.railroad.controller.DashboardController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Dashboard extends Application {
	
	public static ErrorLog errorLog = new ErrorLog();

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
			errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
		}
	}

    public static void main(String[] args) {
        try {
        	if (!Configuration.checkIfFileExists()) {
        		Configuration.writeConfiguration();
        	}
            launch(args);

        } catch (Exception e) {
           errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
        }
    }
}
