package org.unibl.etf.mdp.railroad.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DecimalFormat;
import java.util.stream.Collectors;

import org.unibl.etf.mdp.railroad.archive.ArchiveInterface;
import org.unibl.etf.mdp.railroad.model.Meta;
import org.unibl.etf.mdp.railroad.model.Report;
import org.unibl.etf.mdp.railroad.notifications.Notification;
import org.unibl.etf.mdp.railroad.view.AddUser;
import org.unibl.etf.mdp.railroad.view.Alert;
import org.unibl.etf.mdp.railroad.view.Timetable;
import org.unibl.etf.mdp.railroad.view.Users;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class DashboardController {
	
	private Stage stage;
	private ArchiveInterface archive;
	
	@FXML
	private VBox reportsVBox;
	
	public static final String DIRECTORY =  System.getProperty("user.home") + File.separator + "Railroad" + File.separator + "Archive";
	
	public void initialize(Stage stage) {
		this.stage = stage;
		System.setProperty("java.security.policy", DIRECTORY + File.separator + "policy" + File.separator + "client_policyfile.txt");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			Registry registry = LocateRegistry.getRegistry(1099);
			archive = (ArchiveInterface) registry.lookup("Archive");
			if (archive != null) {
				reportsVBox.getChildren().addAll(archive.list().stream().map(meta -> createRow(meta)).collect(Collectors.toList()));
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		Notification.initialize();

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
	
	private HBox createRow(Meta meta) {
		HBox hbox = new HBox();
		hbox.setStyle("-fx-border-color: #aaa;");
		hbox.setPadding(new Insets(10));
		hbox.setAlignment(Pos.CENTER_LEFT);
		File file = new File(System.getProperty("user.dir") + File.separator +"assets" + File.separator + "icons" + File.separator + "pdf.png");
	    Image img = new Image(file.toURI().toString());
		ImageView image = new ImageView();
		image.setImage(img);
		image.setFitWidth(30);
		image.setFitHeight(30);
		HBox.setMargin(image, new Insets(10));
		Label nameLabel = new Label(meta.getName());
		nameLabel.setFont(Font.font("Monospaced", 16.0));
		Label timeLabel = new Label(meta.getTime());
		timeLabel.setPrefWidth(300);
		timeLabel.setFont(Font.font("Monospaced", 14.0));
		Label sizeLabel = new Label(readableFileSize(meta.getSize()));
		sizeLabel.setPrefWidth(150);
		sizeLabel.setFont(Font.font("Monospaced", 14.0));
		Label userLabel = new Label(meta.getUser());
		userLabel.setPrefWidth(150);
		userLabel.setFont(Font.font("Monospaced", 14.0));
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		Label downloadLabel = new Label("Download");
		downloadLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 16.0));
		downloadLabel.setStyle("-fx-border-color:  #000000cc; -fx-border-radius: 8;");
		downloadLabel.setPadding(new Insets(4));
		downloadLabel.setOnMouseClicked((new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					Report report = archive.download(meta.getId());
					DirectoryChooser chooser = new DirectoryChooser();
					File file = chooser.showDialog(stage);
					try (FileOutputStream output = new FileOutputStream(file + File.separator + report.getMeta().getName())) {
						output.write(report.getData());
						new Alert().display("Report successfully downloaded!");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}

				
			}
		}));
		hbox.getChildren().addAll(image, nameLabel,spacer, timeLabel, sizeLabel, userLabel,  downloadLabel);
		
		return hbox;
	}
	
	public static String readableFileSize(long size) {
	    if(size <= 0) return "0";
	    final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
	    int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
	    return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
}
