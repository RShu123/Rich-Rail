package persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

import domain.Locomotief;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import domain.Train;
import domain.Wagon;
import factory.LocomotiefFactory;
import factory.WagonFactory;
import gui.RichRail;

public class LoadSave {
	
	private String treinNaam;
	private String wagonNaam;
	
	private JsonConvert converter = new JsonConvert();
	private LocomotiefFactory locomotiefFactory = new LocomotiefFactory(); 
	private WagonFactory wagonFactory = new WagonFactory();
	
	
	public void loadEverything(ArrayList<Wagon> Wagons, ArrayList<Locomotief> Locomotieven) throws IOException, ParseException {
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
			
			boolean heeftGeenWagons = false;
			
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
							
							locomotiefFactory.makeLocomotief("new train " + treinNaam);
							System.out.println("locomotief aangemaakt");
							//RichRail.printOutput(treinNaam, type);
							
							
							if (!jObj.get("connectedWagons").equals("[]")) {
								connectedWagons = (JSONArray)jObj.get("connectedWagons");
								heeftGeenWagons = true;
								}
							
							
							if (heeftGeenWagons) {
								for (int i = 0; i < connectedWagons.size(); i++) {
									
									JSONObject wagon = (JSONObject)connectedWagons.get(i);
									
									wagonNaam = (String) wagon.get("naam");
									long aantStoel = (long)wagon.get("aantalStoelen");
									
									wagonFactory.makeWagon("new wagon " + wagonNaam + " numseats " + aantStoel);
									System.out.println("aangemaakt door wagonfactory");
									
									for (Locomotief loco : Locomotieven) {
										if (loco.getNaam().equals(treinNaam)) {
											for (Wagon w : Wagons) {
												if (w.getNaam().equals(wagonNaam)) {
													loco.addWagon(w);
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

							wagonFactory.makeWagon("new wagon " + wagonNaam + " numseats " + aantStoel);
							}
						}
					}

			} catch (ConcurrentModificationException c1) {
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
