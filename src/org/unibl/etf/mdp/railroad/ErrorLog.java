package org.unibl.etf.mdp.railroad;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ErrorLog {

private Logger logger = Logger.getLogger(this.getClass().getName());
	
	public ErrorLog() {
		try {
			Properties properties = Configuration.readParameters();
			String path = properties.getProperty("LOGS_PATH");
			
			FileHandler fileHandler = new FileHandler(path, true);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
		} catch (IOException e) {
			
		}
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}	
}
