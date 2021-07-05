package org.unibl.etf.mdp.railroad.rest;

import java.io.IOException;
import java.util.logging.Level;

import org.unibl.etf.mdp.railroad.Configuration;
import org.unibl.etf.mdp.railroad.view.Dashboard;

public class Root {
	public static String base; 
	
	static {
		try {
			base = Configuration.readParameters().getProperty("REST_API");
		} catch (IOException e) {
			Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
		}
	}

}
