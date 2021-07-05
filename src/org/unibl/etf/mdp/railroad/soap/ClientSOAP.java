package org.unibl.etf.mdp.railroad.soap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

import org.unibl.etf.mdp.railroad.model.User;
import org.unibl.etf.mdp.railroad.view.Dashboard;

public class ClientSOAP {
	
	public static boolean createUser(User user) {
		UserServiceLocator locator = new UserServiceLocator();
		try {
			org.unibl.etf.mdp.railroad.soap.User userService = locator.getUser();
			return userService.createUser(user);
		} catch (Exception e) {
			Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
			return false;
		}
	}
	
	public static boolean deactivate(String username) {
		UserServiceLocator locator = new UserServiceLocator();
		try {
			org.unibl.etf.mdp.railroad.soap.User userService = locator.getUser();
			return userService.deactivate(username);
		} catch(Exception e) {
			Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
			return false;
		}
	}
	
	public static ArrayList<User> getUsers() {
		UserServiceLocator locator = new UserServiceLocator();
		try {
			org.unibl.etf.mdp.railroad.soap.User user = locator.getUser();
			return new ArrayList<>(Arrays.asList(user.getUsers()));
		} catch(Exception e) {
			Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
			return null;
		}
	}

}
