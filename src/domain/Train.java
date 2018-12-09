package domain;

import java.util.ArrayList;


public class Train {

    private String naam;
    private int aantalStoelen;
    private String type;
    private ArrayList<Wagon> connectedWagons;
    
    public Train(String nm){
        naam = nm;
        aantalStoelen = 120;
        type = "train";
        this.connectedWagons = new ArrayList();
    }
    

    public void delete(){};
    
    public int getAantalStoelen() {
    	return aantalStoelen;
    }
    
    public void addWagon(Wagon wagon) {
    	this.connectedWagons.add(wagon);
    }
    
    public void removeWagon(Wagon wagon) {
    	this.connectedWagons.forEach((wagonItem) ->{
    		if(wagonItem.getNaam() == wagon.getNaam()) {
    			this.connectedWagons.remove(this.connectedWagons.indexOf(wagonItem));
    		}
    	});
    }
    
    public String getNaam() {
    	return naam;
    }
    
    public String toString() {
    	String s = "Trein: " + naam + " met aantal stoelen: " + aantalStoelen;
    	

    	return s;
    }
}

