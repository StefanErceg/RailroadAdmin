package org.unibl.etf.mdp.railroad.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	
	public static JSONObject read(String url) throws IOException, JSONException {
		InputStream inputStream = new URL(url).openStream();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			String rawData = readAll(reader);
			JSONObject json = new JSONObject(rawData);
			return json;
		} finally {
			inputStream.close();
		}
	}
	
	public static JSONArray readArray(String url) throws IOException, JSONException {
		InputStream inputStream = new URL(url).openStream();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			String rawData = readAll(reader);
			JSONArray array = new JSONArray(rawData);
			return array;
		} finally {
			inputStream.close();
		}
	}
}
