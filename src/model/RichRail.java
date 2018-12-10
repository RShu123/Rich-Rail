package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonException;
import javax.json.JsonObjectBuilder;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import org.json.JSONArray;
//import org.json.JSONObject;
import org.json.JSONException;

import com.google.gson.Gson;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.sun.javafx.scene.paint.GradientUtils.Parser;

import domain.Train;
import domain.Wagon;

public class RichRail extends JFrame {

	private JPanel contentPane;
	private JTextField inputField;
	private JButton executeBtn;
	private JPanel drawPanel;
	private JPanel commandPanel;
	private JPanel outputPanel;
	private int currentNumberOfCommands;
	private String treinNaam;
	private String wagonNaam;
	private String commandType;
	private int currentNumberOfOutputs;
	private int aantalStoelen;
	private int aantalTreinStoelen;
	//private Write writer = new Write();
	private ArrayList<Train> Treinen = new ArrayList();
	private ArrayList<Wagon> Wagons = new ArrayList();
	//private JSONArray jarVehicle = new JSONArray();
	private JSONObject jobTrain = new JSONObject();
	private JSONObject jobWagon = new JSONObject();
	

	private JsonObjectBuilder job = Json.createObjectBuilder();
	private JsonArrayBuilder jab = Json.createArrayBuilder();
	
	
	ArrayList<String> jsonTreinen = new ArrayList();
	
	ArrayList<String> jsonWagons = new ArrayList();
	
	public RichRail() {
		setResizable(false);
		setTitle("RichRail");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		drawPanel = new JPanel();
		drawPanel.setBackground(Color.WHITE);
		contentPane.add(drawPanel, BorderLayout.NORTH);
		GridBagLayout gbl_drawPanel = new GridBagLayout();
		gbl_drawPanel.columnWidths = new int[] {20};
		gbl_drawPanel.rowHeights = new int[] {200};
		gbl_drawPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_drawPanel.rowWeights = new double[]{Double.MIN_VALUE};
		drawPanel.setLayout(gbl_drawPanel);
		
		outputPanel = new JPanel();
		outputPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		outputPanel.setBackground(Color.BLACK);
		contentPane.add(outputPanel, BorderLayout.EAST);
		outputPanel.setLayout(new GridLayout(0, 20, 15, 0));
		
		commandPanel = new JPanel();
		commandPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		commandPanel.setBackground(Color.WHITE);
		contentPane.add(commandPanel, BorderLayout.WEST);
		commandPanel.setLayout(new GridLayout(0, -20, -14, 0));
		
		JLabel lblOutput = DefaultComponentFactory.getInstance().createLabel("output:");
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblOutput, BorderLayout.CENTER);
		
		JPanel lowPanel = new JPanel();
		FlowLayout fl_lowPanel = (FlowLayout) lowPanel.getLayout();
		fl_lowPanel.setAlignment(FlowLayout.LEFT);
		contentPane.add(lowPanel, BorderLayout.SOUTH);
		
		JLabel lblCommand = new JLabel("command:");
		lowPanel.add(lblCommand);
		
		inputField = new JTextField();
		inputField.setHorizontalAlignment(SwingConstants.LEFT);
		lowPanel.add(inputField);
		inputField.setColumns(10);
		
		executeBtn = new JButton("execute");
		executeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == executeBtn) {
					String input = inputField.getText();
					currentNumberOfCommands += 20;
					try {
						printCommand(input);
					} catch (JSONException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
			}
		});
		lowPanel.add(executeBtn);
	}
	
	
	public void printCommand(String command) throws JSONException, ParseException {
		if (command != "") {
			Graphics g = commandPanel.getGraphics();
			g.drawString(command, 5, currentNumberOfCommands);
			String[] splitted = command.split(" ");
			//System.out.println(command);

			
			if (command.startsWith("new train")) {
				
				boolean trainExists = false;
				
				
				treinNaam = splitted[2];
				commandType = splitted[1];
				
				if (!Treinen.isEmpty()) {
					
					for (Train trein : Treinen) {
						if (trein.getNaam().equals(treinNaam)) {
							trainExists = true;
						}
					}
					
				}
				
				if (trainExists) {
					printOutput("bestaat", "train");
				}
				else {
					newTrain(treinNaam);
					printOutput(command , commandType);
				}
				
			}
				
				
				if (command.startsWith("new wagon") && !command.contains("numseats")) {
				//System.out.println("test");
				wagonNaam = splitted[2];
				commandType = splitted[1];
				
				
				boolean wagonExists = false;
				if(!Wagons.isEmpty()) {
					
					for (Wagon wagon: Wagons) {
						
						if(wagon.getNaam().equals(wagonNaam)) {
							wagonExists = true;
						}	
				}
				}
				
					if(wagonExists) {
						printOutput("bestaat", "wagon");
					}
					else {
						newWagon(wagonNaam);
						printOutput(command, commandType);
					}
			}
			
				if (command.startsWith("new wagon") && command.contains("numseats")) {
				wagonNaam = splitted[2];
				commandType = splitted[1];
				aantalStoelen = Integer.parseInt(splitted[4]);
				
				boolean wagonExists = false;
				if(!Wagons.isEmpty()) {
					
					for (Wagon wagon: Wagons) {
						
						if(wagon.getNaam().equals(wagonNaam)) {
							wagonExists = true;
						}	
				}
			}
				
					if(wagonExists) {
						printOutput("bestaat", "wagon");
					}
					else {
						newWagonWithSeats(wagonNaam,aantalStoelen);
						printOutput(command, commandType);
					}
				
				
			}
			
			
			if (command.startsWith("getnumseats train")) {
				treinNaam = splitted[2];
				
				if (!Treinen.isEmpty()) {
					
				for (Train trein : Treinen) {
					if (trein.getNaam().equals(treinNaam)) {
						aantalTreinStoelen = trein.getAantalStoelen();
						printOutput(String.valueOf(aantalTreinStoelen), "treinStoel");
						
						}
					}
				}
			}
			
			if (command.startsWith("getnumseats wagon")) {
				wagonNaam = splitted[2];
				
				if (!Wagons.isEmpty()) {
				for (Wagon w : Wagons) {
					if (w.toString().contains(wagonNaam)) {
						aantalStoelen = w.getStoel();
						printOutput(String.valueOf(aantalStoelen), "wagonStoel");
						
						}
					}
				}
			}
			
			if (command.startsWith("add")) {
				//System.out.println("add functie aangeroepen");
				boolean wagonExists = false;
				boolean trainExists = false;
				wagonNaam = splitted[1];
				treinNaam = splitted[3];
				System.out.println(treinNaam);
				
				for (Wagon wagon : Wagons) {
					//System.out.println(wagon.getNaam());
					if (wagon.getNaam().equals(wagonNaam)) {
						wagonExists = true;
						System.out.println("wagon bestaat");
					}
					else {
						System.out.println("wagon bestaat niet");
					}
				}
				
				for (Train train : Treinen) {
					if (train.getNaam().equals(treinNaam)) {
						trainExists = true;
						System.out.println("trein bestaat");
					}
					else {
						System.out.println("trein bestaat niet");
					}
				}
				
				if (wagonExists && trainExists) {
					for (Train trein : Treinen) {
						if (trein.getNaam().equals(treinNaam)) {
							for (Wagon w : Wagons) {
								if (w.getNaam().equals(wagonNaam)) {
									trein.addWagon(w);
									System.out.println("wagon toegevoegd aan trein");
								}
							}
						}
					}
				}
			}
			
			if (command.trim().equals("save")) {
				System.out.println(Wagons + "\n" + Treinen);
				try {
					writeToFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if  (command.trim().equals("load")) {
				try {
					loadSave();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		}
	
	public void printOutput(String output, String type) {
		Graphics o = outputPanel.getGraphics();
		currentNumberOfOutputs += 20;
		o.setColor(Color.WHITE);
		
		if (type.equals("treinStoel")) {
			o.drawString(treinNaam + " has " + aantalTreinStoelen + " seats", 5, currentNumberOfOutputs);
		}
		
		if (type.equals("wagonStoel")) {
			o.drawString(wagonNaam + " has " + aantalStoelen + " seats", 5, currentNumberOfOutputs);
		}
		
		if (output != "bestaat" && type.equals("train")) {
			if (treinNaam != null) {
			o.drawString("train " + treinNaam + " created", 5, currentNumberOfOutputs);
			}
			else {
				o.drawString("train " + output + " created", 5, currentNumberOfOutputs);
			}
		}
		
		if (output != "bestaat" && type.equals("wagon")) {
			if (wagonNaam != null) {
			o.drawString("wagon " + wagonNaam + " created", 5, currentNumberOfOutputs);
			}
			else {
				o.drawString("wagon " + output + " created", 5, currentNumberOfOutputs);
			}
		}
		
		else if (type.equals("train") && output == "bestaat"){
			o.drawString("train already exists!", 5, currentNumberOfOutputs);
		}
		
		else if (type.equals("wagon") && output == "bestaat") {
			o.drawString("wagon already exists", 5, currentNumberOfOutputs);
		}
	}
	
	public void newTrain(String naam) throws JSONException, ParseException {
		Train trein = new Train(naam);
		Treinen.add(trein);
		System.out.println("Trein aangemaakt");
		createJsonTrainArray();
	}
	
	public void newWagon(String naam) throws JSONException, ParseException {
		Wagon wagon = new Wagon(naam);
		Wagons.add(wagon);
		System.out.println("Wagon aangemaakt");
		createJsonWagonArray();
	}
	
	public void newWagonWithSeats(String naam, int aantStoel) throws JSONException, ParseException {
		Wagon wagon = new Wagon(naam, aantStoel);
		Wagons.add(wagon);
		System.out.println("Wagon aangemaakt met: " + aantStoel + " stoelen");
		createJsonWagonArray();
	}
	
	public void loadSave() throws IOException, ParseException {
		FileReader fr = new FileReader("save.txt");
		BufferedReader br = new BufferedReader(fr);
		Scanner sc = new Scanner(fr);
		Gson json = new Gson();
		
		while (sc.hasNext()) {
			
			String jsonArray = sc.next();
			
			System.out.println(jsonArray);
			
			jsonArray = jsonArray.replaceAll("\\\\", "");
			//jsonArray = jsonArray.substring(1, jsonArray.length()-1);
			
			System.out.println(jsonArray);
			
			JSONObject jObj = new JSONObject();
			JSONArray jArr = new JSONArray();
			JSONArray arraytesttrain = new JSONArray();
			JSONArray arraytestwagon = new JSONArray();
			JSONArray connectedWagons = new JSONArray();
			JSONParser parser = new JSONParser();
			
			boolean heeftWagons = false;
			
			jArr = (JSONArray)parser.parse(jsonArray);
			System.out.println("PASSED LINE 422 " + jArr);
			
			
			
			try {
				//jArr = new JSONArray();
				//jArr.add(jsonArray);
				
				for (Object obj: jArr) {
					System.out.println("dit is een onderdeel "+obj);
					jObj = (JSONObject) obj;
					System.out.println("dit is het jsonobject: " + jObj);
					System.out.println("dit is een get: " + jObj.get("trains"));
					arraytesttrain = (JSONArray) jObj.get("trains");
					arraytestwagon = (JSONArray) jObj.get("wagons");
					System.out.println("dit is de trein lijst: " + arraytesttrain);
					System.out.println("dit is de wagon lijst: " + arraytestwagon);
					for (Object train: arraytesttrain) {
						jObj = (JSONObject)train;
						String naam = (String)jObj.get("naam");
						long stoel = (long)jObj.get("aantalStoelen");
						String type = (String)jObj.get("type");
						if (!jObj.get("connectedWagons").equals("[]")) {
							connectedWagons = (JSONArray)jObj.get("connectedWagons");
							heeftWagons = true;
							}
							
							if (type.equals("train")) {
							newTrain(naam);
							printOutput(naam, type);
							}
					}
				}
				
				//for (int i = 0; i < jArr.length(); i++) {
				//jObj = jArr.getJSONObject(i);
				//}
				
				
				
				String naam = (String)jObj.get("naam");
				int stoel = (int)jObj.get("aantalStoelen");
				String type = (String)jObj.get("type");
				
				if (!jObj.get("connectedWagons").equals("[]")) {
				connectedWagons = (JSONArray)jObj.get("connectedWagons");
				heeftWagons = true;
				}
				
				if (type.equals("train")) {
				newTrain(naam);
				printOutput(naam, type);
				}
				if (type.equals("wagon")) {
				newWagonWithSeats(naam, stoel);
				printOutput(naam, type);
				}
				
				if (heeftWagons = true) {
				for (int i = 0; i < connectedWagons.size(); i++) {
					JSONObject wagon = (JSONObject)connectedWagons.get(i);
					String name = (String) wagon.get("naam");
					int aantStoel = (int)wagon.get("aantalStoelen");
					String soort = (String)wagon.get("type");
					newWagonWithSeats(name, aantStoel);
					printOutput(name,soort);
				}
				}


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	public ArrayList<String> createJsonTrainArray() throws JSONException, ParseException{
		Gson json = new Gson();
		JSONParser parser = new JSONParser();
		JSONObject jsonTrainObj = new JSONObject();
		JSONArray jTrainArray = new JSONArray();
		
		for (Train trein : Treinen) {
			String treinObject = json.toJson(trein);
			jsonTrainObj = (JSONObject)parser.parse(treinObject);
			//System.out.println(jsonObj);
			jTrainArray.add(jsonTrainObj);
			//jab.add(jsonObj);
			//System.out.println(treinObject);
			//System.out.println("JAB TEST " + jab.build());
			//jsonTreinen.add(treinObject);
		}
		
		//System.out.println(jsonTreinen);

		//System.out.println(jarVehicle);

		return jTrainArray;
		
	}
	
	public ArrayList<String> createJsonWagonArray() throws JSONException, ParseException{
		Gson json = new Gson();	
		JSONParser parser = new JSONParser();
		JSONObject jsonWagonObj = new JSONObject();
		JSONArray jWagonArray = new JSONArray();
		
		for (Wagon wagon : Wagons) {
			String wagonObject = json.toJson(wagon);
			jsonWagonObj = (JSONObject)parser.parse(wagonObject);
			jWagonArray.add(jsonWagonObj);			
			//jsonWagons.add(wagonObject);
		}
		//System.out.println(jsonWagons);

		return jWagonArray;
	}
	
	
	public void writeToFile() throws IOException, JSONException, ParseException{
		File file = new File("save.txt");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		JSONArray finalArray = new JSONArray();
		
		System.out.println("toString: " + jsonTreinen.toString());
		
		//job.add("trains", jArray);
		jobTrain.put("trains", createJsonTrainArray());
		jobWagon.put("wagons", createJsonWagonArray());
		
		finalArray.add(jobTrain);
		finalArray.add(jobWagon);
		
		
		//jobTrain.put("trains", jsonTreinen.toString());
		//jobWagon.put("wagons", jsonWagons.toString());
		//jarVehicle.add(jobTrain);
		//jarVehicle.add(jobWagon);
		
		System.out.println();
		System.out.println();
		//System.out.println(jobTrain);
		//System.out.println(jobWagon);
		//System.out.println(jarVehicle);
		//System.out.println(job.build());
		//System.out.println(jsonTreinen);
		System.out.println(finalArray);
		//pw.println(createJsonTrainArray());
		//pw.println(createJsonWagonArray());
		pw.println(finalArray);
		//pw.println(jarVehicle);
		
		pw.close();
	}

}
