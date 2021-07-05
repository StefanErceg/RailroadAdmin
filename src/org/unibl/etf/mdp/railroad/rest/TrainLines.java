package org.unibl.etf.mdp.railroad.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.unibl.etf.mdp.railroad.model.TrainLine;
import org.unibl.etf.mdp.railroad.view.Dashboard;

import com.google.gson.Gson;

public class TrainLines {
	public static final String base = Root.base + "/trainLines";
	
	public static ArrayList<TrainLine> getAllLines() {
		try {
			JSONArray array = Utils.readArray(base);
			Gson gson = new Gson();
			ArrayList<TrainLine> trainLines = new ArrayList<TrainLine>();
			if (array != null) {
				for (int index = 0; index < array.length(); index++) {
					trainLines.add(gson.fromJson(array.optJSONObject(index).toString(), TrainLine.class));
				}
			}
			return trainLines;
		} catch (JSONException | IOException e) {
			Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
			return null;
		}
	}

	public static ArrayList<TrainLine> getTrainLineByTrainStation(String trainStationId) {
		try {
			JSONArray array = Utils.readArray(base + "/byTrainStation/" + trainStationId);
			Gson gson = new Gson();
			ArrayList<TrainLine> trainLines = new ArrayList<TrainLine>();
			if (array != null) {
				for (int index = 0; index < array.length(); index++) {
					trainLines.add(gson.fromJson(array.optJSONObject(index).toString(), TrainLine.class));
				}
			}
			return trainLines;
		} catch (JSONException | IOException e) {
			Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
			return null;
		}
	}
	
	public static TrainLine markPassed(String trainLineId, String trainStationId) {
		try {
			URL url = new URL(base + "/" + trainLineId + "/" + trainStationId);
			Gson gson = new Gson();
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type", "application/json");
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new Exception("Error happened while updating train line");
			} else {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader((connection.getInputStream())))) {
					String line = null;
					String data = "";
					while ((line = reader.readLine()) != null) {
						data+=line;
					}
					TrainLine trainLine = gson.fromJson(new JSONObject(data).toString(),TrainLine.class);
					return trainLine;
				}
				catch (Exception e) { 
					Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
				}
			}
			
		} catch(Exception e) {
			Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
		}
		return null;
	}
	
	public static TrainLine create(TrainLine trainLine) {
		try {
			URL url = new URL(base);
			Gson gson = new Gson();
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			JSONObject body = new JSONObject(trainLine);
			OutputStream output = connection.getOutputStream();
			output.write(body.toString().getBytes());
			output.flush();
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new Exception("Error happened while creating train line");
			} else {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
					String line = null;
					String data = "";
					while ((line = reader.readLine()) != null) {
						data += line;
					}
					TrainLine response = gson.fromJson(new JSONObject(data).toString(), TrainLine.class);
					return response;
				} catch(Exception e) {
					Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
				}
			}
		
		} catch(Exception e) {
			Dashboard.errorLog.getLogger().log(Level.SEVERE, e.fillInStackTrace().toString());
		}
		return null;
	}
	
	public static boolean delete(String trainLineId) {
		try {
			URL url = new URL(base + "/" + trainLineId);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("DELETE");
			OutputStream os = conn.getOutputStream();
			os.flush();
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new Exception("Error happened while deleting train line");
			}
			os.close();
			conn.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
