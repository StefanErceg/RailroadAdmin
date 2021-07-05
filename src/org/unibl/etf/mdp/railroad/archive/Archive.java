package org.unibl.etf.mdp.railroad.archive;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

import org.json.JSONObject;
import org.unibl.etf.mdp.railroad.model.Meta;
import org.unibl.etf.mdp.railroad.model.Report;
import org.unibl.etf.mdp.railroad.view.Dashboard;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Archive implements ArchiveInterface {
	
	public static final String DIRECTORY =  System.getProperty("user.home") + File.separator + "Railroad" + File.separator + "Archive";

	@Override
	public boolean upload(byte[] data, String name, String user) throws RemoteException {
		File output = new File(DIRECTORY + File.separator + name);
		try {
		output.createNewFile();
		} catch(Exception e) {
			Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
			return false;
		}
		try (FileOutputStream dataStream = new FileOutputStream(output); FileOutputStream metaStream = new FileOutputStream(new File(output.getAbsolutePath() + "-meta.json"))) {
		    dataStream.write(data);
		    Meta meta = new Meta(name, user, new Date().toString(), data.length);
		    metaStream.write(new JSONObject(meta).toString().getBytes());
		    return true;
		} catch (IOException e) {
			Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
		}
		return false;
	}

	@Override
	public ArrayList<Meta> list() throws RemoteException {
		File directory = new File(DIRECTORY);
		if (!directory.isDirectory()) return null;
		File[] files = directory.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith("-meta.json");
			}
		});
		Gson gson = new Gson();
		ArrayList<Meta> metaData = new ArrayList<Meta>();
		for (File file : files) {
			try {
				byte[] fileBytes = Files.readAllBytes(file.toPath());
				Meta meta = gson.fromJson(new JSONObject(new String(fileBytes)).toString(), Meta.class);
				metaData.add(meta);
			} catch (IOException e) {
				Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
			}
		} 
		return metaData;
	}

	@Override
	public Report download(String id) throws RemoteException {
		ArrayList<Meta> metaData = list();
		Meta meta = metaData.stream().filter(element -> element.getId().equals(id)).findFirst().orElse(null);
		File file = new File(DIRECTORY + File.separator + meta.getName());
		try {
			byte[] data = Files.readAllBytes(file.toPath());
			return new Report(data, meta);
		} catch (IOException e) {
			Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
		}
		return null;
		
		
	}
	

}
