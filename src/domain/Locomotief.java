package domain;

import java.util.ArrayList;

public class Locomotief {

    private String naam;
    private int aantalStoelen;
    private ArrayList<Wagon> connectedWagons;

    public Locomotief(String nm){
        naam = nm;
        aantalStoelen = 180;
        this.connectedWagons = new ArrayList<>();
    }

    public int getAantalStoelen() {
        return aantalStoelen;
    }

    public void addWagon(Wagon newWagon){
        connectedWagons.add(newWagon);
    }

    public String getNaam() {
        return naam;
    }

    public String toString(){
        String s ="";
        s += naam + " met aantal treinstoelen " + aantalStoelen;
        return s;
    }

}
