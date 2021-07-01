package org.unibl.etf.mdp.railroad.soap;

import java.util.ArrayList;
import java.util.Arrays;

import org.unibl.etf.mdp.railroad.model.User;

public class ClientSOAP {
	
	public static boolean createUser(User user) {
		UserServiceLocator locator = new UserServiceLocator();
		try {
			org.unibl.etf.mdp.railroad.soap.User userService = locator.getUser();
			return userService.createUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean deactivate(String username) {
		UserServiceLocator locator = new UserServiceLocator();
		try {
			org.unibl.etf.mdp.railroad.soap.User userService = locator.getUser();
			return userService.deactivate(username);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<User> getUsers() {
		UserServiceLocator locator = new UserServiceLocator();
		try {
			org.unibl.etf.mdp.railroad.soap.User user = locator.getUser();
			return new ArrayList<>(Arrays.asList(user.getUsers()));
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
