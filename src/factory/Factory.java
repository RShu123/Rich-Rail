package factory;

import domain.Locomotief;
import domain.Wagon;

public class Factory {

    public Wagon makeWagon(String command){
        String[] splitted = command.split(" ");
        String naam = splitted[2];

        if (command.startsWith("new wagon") && !command.contains("numseats")){
            return new Wagon(naam);
        }

        if (command.startsWith("new wagon") && command.contains("numseats")){
            int aantalStoelen = Integer.parseInt(splitted[4]);

                return new Wagon(naam, aantalStoelen);
            }
        else{return null;}
    }

    public Locomotief makeLocomotief(String command){
        String[] splitted = command.split(" ");
        String naam = splitted[2];

        if (command.startsWith("new locomotief")){
            return new Locomotief(naam);
        }
        else{return null;}
    }
}
