package persistence;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import domain.Locomotief;
import domain.Train;
import domain.Wagon;
import gui.RichRail;

public class JsonConvert {
	
		
	public ArrayList<String> createJsonTrainArray(ArrayList<Locomotief> locomotieven) throws JSONException, ParseException{
		Gson json = new Gson();
		JSONParser parser = new JSONParser();
		JSONObject jsonTrainObj = new JSONObject();
		JSONArray jTrainArray = new JSONArray();
		
		for (Locomotief loco : locomotieven) {
			String treinObject = json.toJson(loco);
			jsonTrainObj = (JSONObject)parser.parse(treinObject);
			jTrainArray.add(jsonTrainObj);
		}
		System.out.println(jTrainArray);
		return jTrainArray;
		
	}
	
	public ArrayList<String> createJsonWagonArray(ArrayList<Wagon> Wagons) throws JSONException, ParseException{
		Gson json = new Gson();	
		JSONParser parser = new JSONParser();
		JSONObject jsonWagonObj = new JSONObject();
		JSONArray jWagonArray = new JSONArray();
		
		for (Wagon wagon : Wagons) {
			String wagonObject = json.toJson(wagon);
			jsonWagonObj = (JSONObject)parser.parse(wagonObject);
			jWagonArray.add(jsonWagonObj);			
		}
		System.out.println(jWagonArray);
		return jWagonArray;
	}

}
