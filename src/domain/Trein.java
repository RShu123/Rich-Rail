package domain;

import java.util.ArrayList;

public class Trein {

    private Locomotief locomotief;
    private ArrayList<Wagon> connectedWagons = new ArrayList<>();

    public Trein(Locomotief newLoco, Wagon newWagon){
        locomotief = newLoco;
        connectedWagons.add(newWagon);
    }

    public void addWagon(Wagon newWagon){
        connectedWagons.add(newWagon);
    }

    public Locomotief getLocomotief() {
        return locomotief;
    }

    public ArrayList<Wagon> getConnectedWagons() {
        return connectedWagons;
    }
}
