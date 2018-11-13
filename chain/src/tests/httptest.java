package tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import election.position;

public class httptest {
	public static void main(String[] args) throws IOException{
		URL url = new URL("http://localhost:8080/test?voter=1");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		int response_Code = conn.getResponseCode();
		System.out.println("Response code :: " + response_Code);
		if(response_Code == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			JsonParser parser = new JsonParser();
			JsonObject jsonObject = (JsonObject) parser.parse(response.toString());
			if(true) {
				System.out.println("Public Key ::" + jsonObject.get("publicKey").getAsString());
				System.out.println("Private Key ::" + jsonObject.get("privateKey").getAsString());
				JsonArray arr = parser.parse(jsonObject.get("positions").getAsString()).getAsJsonArray();
				System.out.println(arr.toString());
				System.out.println("Test Success!");
				Iterator<JsonElement> it = arr.iterator();
				
				while(it.hasNext()) {
					position pos = position.fromJSON(it.next().getAsString());
					
				}
			}else {
				System.out.println("Test Failed!");
			}
		}else {
			System.out.println("HTTP Failure");
		}
	}
}