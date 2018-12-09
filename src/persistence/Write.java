package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.JSONException;

import com.google.gson.Gson;

import domain.Train;
import domain.Wagon;
import model.RichRail;

public class Write {
/*	
	public ArrayList createJsonTrainArray() throws JSONException{
		Gson json = new Gson();
		
		ArrayList<String> jsonTreinen = new ArrayList();
		
		for (Train trein : RichRail.Treinen) {
			String treinObject = json.toJson(trein);
			jsonTreinen.add(treinObject);
		}
		
	
		return jsonTreinen;
		
	}
	
	public ArrayList createJsonWagonArray() throws JSONException{
		Gson json = new Gson();
		
		ArrayList<String> jsonWagons = new ArrayList();
		
		for (Wagon wagon : RichRail.Wagons) {
			String wagonObject = json.toJson(wagon);
			jsonWagons.add(wagonObject);
		}
		
		return jsonWagons;
	}
	
	public void writeToFile() throws IOException, JSONException{
		File file = new File("test.txt");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
				
		pw.println(createJsonTrainArray());
		pw.println(createJsonWagonArray());
		pw.println();
		
		pw.close();
	}
*/
}
