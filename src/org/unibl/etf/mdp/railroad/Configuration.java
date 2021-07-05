package org.unibl.etf.mdp.railroad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
public static String CONFIG_PATH = "config" + File.separator + "config.properties";
	
	public static void writeConfiguration() throws IOException {
		File folder = new File("config");
		if(!folder.exists()) {
			folder.mkdir();
		}
		
		File file = new File(CONFIG_PATH);
		file.createNewFile();
		
		Properties properties = new Properties();
		properties.setProperty("DIRECTORY", System.getProperty("user.home") + File.separator + "Railroad" + File.separator + "Archive");
		properties.setProperty("CLIENT_POLICY", System.getProperty("user.home") + File.separator + "Railroad" + File.separator + "Archive" + File.separator + "policy" + File.separator + "client_policyfile.txt");
		properties.setProperty("FILE_PORT", "1099");
		properties.setProperty("REST_API", "http://localhost:8080/RailroadCentral/api");
		properties.setProperty("LOGS_PATH", System.getProperty("user.home") + File.separator + "Railroad" + File.separator + "Admin" + File.separator + "error.log");
		
		FileOutputStream out = new FileOutputStream(file);
		properties.store(out, null);
		
		out.close();
	}
	
	public static Properties readParameters() throws IOException {	
		Properties properties = new Properties();
		FileInputStream in = new FileInputStream(new File(CONFIG_PATH));
		properties.load(in);
		in.close();		
		
		return properties;
	}
	
	public static boolean checkIfFileExists() {
		return new File(CONFIG_PATH).exists();
	}
	
}
