package factory;

import domain.Wagon;

import java.util.ArrayList;

public class WagonFactory {

    private ArrayList<Wagon> alleWagons = new ArrayList<>();
    boolean wagonExists = false;

    public void checkIfExists(String naam){
        for (Wagon w : alleWagons){
            if(w.getNaam().equals(naam)){
                wagonExists = true;
            }
        }
    }


}
