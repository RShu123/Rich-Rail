package persistence;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import domain.Train;
import domain.Wagon;
import model.RichRail;

public class JsonConvert {
	
	private RichRail controller = new RichRail();
		
	public ArrayList<String> createJsonTrainArray() throws JSONException, ParseException{
		Gson json = new Gson();
		JSONParser parser = new JSONParser();
		JSONObject jsonTrainObj = new JSONObject();
		JSONArray jTrainArray = new JSONArray();
		
		for (Train trein : controller.Treinen) {
			String treinObject = json.toJson(trein);
			jsonTrainObj = (JSONObject)parser.parse(treinObject);
			jTrainArray.add(jsonTrainObj);
		}

		return jTrainArray;
		
	}
	
	public ArrayList<String> createJsonWagonArray() throws JSONException, ParseException{
		Gson json = new Gson();	
		JSONParser parser = new JSONParser();
		JSONObject jsonWagonObj = new JSONObject();
		JSONArray jWagonArray = new JSONArray();
		
		for (Wagon wagon : controller.Wagons) {
			String wagonObject = json.toJson(wagon);
			jsonWagonObj = (JSONObject)parser.parse(wagonObject);
			jWagonArray.add(jsonWagonObj);			
		}

		return jWagonArray;
	}

}
