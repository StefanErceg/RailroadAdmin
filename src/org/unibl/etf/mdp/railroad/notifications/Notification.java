package org.unibl.etf.mdp.railroad.notifications;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;

import org.unibl.etf.mdp.railroad.view.Alert;
import org.unibl.etf.mdp.railroad.view.Dashboard;

import javafx.application.Platform;

public class Notification {
	
	private static final int PORT = 20000;
	private static final String HOST = "224.0.0.11";
	public static final String DELIMITER = "###";
	
	private static MulticastSocket socket = null;
	private static boolean active = false;
	private static InetAddress address = null;
	
	public static void initialize() {
		try {
		socket = new MulticastSocket(PORT);
        address = InetAddress.getByName(HOST);
        socket.joinGroup(address);
        active = true;
        listen();
		} catch(Exception e) {
			Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
		}
	}
	
	private static void listen() {
		byte[] buffer = new byte[1024];
		Thread listener = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (active) {
					DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
	                try {
						socket.receive(packet);
					} catch (IOException e) {
						Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
					}
	                String received = new String(packet.getData(), 0, packet.getLength());
	                String[] parsed = received.split(DELIMITER);
	                Platform.runLater(new Runnable() {
						@Override
						public void run() {
							new Alert().display(parsed[0] + ": " + parsed[1]);
						}
					}); 
				}
				
			}
		});
		listener.setDaemon(true);
		listener.start();
	}

}
