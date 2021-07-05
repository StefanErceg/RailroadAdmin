package org.unibl.etf.mdp.railroad.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

import org.json.JSONArray;
import org.json.JSONException;
import org.unibl.etf.mdp.railroad.model.TrainStation;
import org.unibl.etf.mdp.railroad.view.Dashboard;

import com.google.gson.Gson;

public class TrainStations {
	
	public static final String base = Root.base + "/trainStations";
	
	public static ArrayList<TrainStation> getAllStations() {
		try {
			JSONArray array = Utils.readArray(base);
			Gson gson = new Gson();
			ArrayList<TrainStation> trainStations = new ArrayList<TrainStation>();
			if (array != null) {
				for (int index = 0; index < array.length(); index++) {
					trainStations.add(gson.fromJson(array.optJSONObject(index).toString(), TrainStation.class));
				}
			}
			return trainStations;
		} catch (JSONException | IOException e) {
			Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
			return null;
		}
	}

}
