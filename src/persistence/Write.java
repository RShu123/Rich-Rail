package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import domain.Train;
import domain.Wagon;
import gui.RichRail;

public class Write {
	
	private JsonConvert converter = new JsonConvert();	

	public void writeToFile(JSONObject trainObject, JSONObject wagonObject, ArrayList<Train> Treinen, ArrayList<Wagon> Wagons) throws IOException, JSONException, ParseException{
		File file = new File("save.txt");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		JSONArray finalArray = new JSONArray();
				
		trainObject.put("trains", converter.createJsonTrainArray(Treinen));
		wagonObject.put("wagons", converter.createJsonWagonArray(Wagons));
		
		finalArray.add(trainObject);
		finalArray.add(wagonObject);
		
		
		System.out.println();
		System.out.println();
		System.out.println(finalArray);
		pw.println(finalArray);
		
		pw.close();
	}
	
}
