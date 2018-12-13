package persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import domain.Train;
import domain.Wagon;
import model.RichRail;

public class LoadSave {
	
	private String treinNaam;
	private String wagonNaam;
	
	private Write writer = new Write();
	private JsonConvert converter = new JsonConvert();
	
	private RichRail controller = new RichRail();
	
	
	public void loadEverything() throws IOException, ParseException {
		FileReader fr = new FileReader("save.txt");
		BufferedReader br = new BufferedReader(fr);
		Scanner sc = new Scanner(fr);
		Gson json = new Gson();
		
		while (sc.hasNext()) {
			
			String jsonArray = sc.next();
						
			jsonArray = jsonArray.replaceAll("\\\\", "");
			
			JSONObject jObj = new JSONObject();
			JSONArray jArr = new JSONArray();
			JSONArray arraytesttrain = new JSONArray();
			JSONArray arraytestwagon = new JSONArray();
			JSONArray connectedWagons = new JSONArray();
			JSONParser parser = new JSONParser();
			
			boolean heeftWagons = false;
			
			jArr = (JSONArray)parser.parse(jsonArray);
			
			
			
			try {
				
				for (Object obj: jArr) {
					jObj = (JSONObject) obj;
					arraytesttrain = (JSONArray) jObj.get("trains");
					arraytestwagon = (JSONArray) jObj.get("wagons");
					
					if (arraytesttrain != null) {
						for (Object train: arraytesttrain) {
							
							jObj = (JSONObject)train;
							
							treinNaam = (String)jObj.get("naam");
							String type = (String)jObj.get("type");
							
							controller.newTrain(treinNaam);
							controller.printOutput(treinNaam, type);
							
							
							if (!jObj.get("connectedWagons").equals("[]")) {
								connectedWagons = (JSONArray)jObj.get("connectedWagons");
								heeftWagons = true;
								}
							
							
							if (heeftWagons = true) {
								for (int i = 0; i < connectedWagons.size(); i++) {
									
									JSONObject wagon = (JSONObject)connectedWagons.get(i);
									
									wagonNaam = (String) wagon.get("naam");
									long aantStoel = (long)wagon.get("aantalStoelen");
									String soort = (String)wagon.get("type");
									
									controller.newWagonWithSeats(wagonNaam, aantStoel);
									controller.printOutput(wagonNaam,soort);
									
									for (Train t : controller.Treinen) {
										if (t.getNaam().equals(treinNaam)) {
											for (Wagon w : controller.Wagons) {
												if (w.getNaam().equals(wagonNaam)) {
													t.addWagon(w);
													controller.printOutput(wagonNaam,"add");
												}
											}
										}
									}
								}
							}
						}
					}
					
					if (arraytestwagon != null) {
						
						for (Object wagon : arraytestwagon) {
							
							jObj = (JSONObject)wagon;
							wagonNaam = (String) jObj.get("naam");
							long aantStoel = (long) jObj.get("aantalStoelen");
							String type = (String) jObj.get("type");
							
							for (Wagon w : controller.Wagons) {
								if (!w.getNaam().equals(wagonNaam)) {
									controller.newWagonWithSeats("wagonNaam", aantStoel);
									controller.printOutput(wagonNaam, type);
							}
								else {
									controller.printOutput("bestaat", type);
							}
						}
					}
				}		
			}

			} catch (JSONException | ConcurrentModificationException c1) {
				if(c1 instanceof ConcurrentModificationException) {
					System.out.println("Modificatie voordat iteratie is beeindigd");
				}
				else {
					c1.printStackTrace();
				}
			}
			
		}
		
	}
}
