package commandpattern;

import domain.Locomotief;
import domain.Wagon;
import factory.LocomotiefFactory;
import factory.WagonFactory;

import java.util.ArrayList;

public class AddWagonToTrain implements Command{

    private LocomotiefFactory locoFactory = new LocomotiefFactory();
    private WagonFactory wagonFactory = new WagonFactory();

    private ArrayList<Locomotief> locomotieven;
    private ArrayList<Wagon> wagons;
    private Locomotief locomotief;
    private Wagon wagon;
    boolean locomotiefExists = false;
    boolean wagonExists = false;

    public AddWagonToTrain(Locomotief newLoco, Wagon newWagon){
        locomotief = newLoco;
        wagon = newWagon;
    };

    @Override
    public void execute() {
            locomotief.addWagon(wagon);
            System.out.println("gelukt");
        }
    }
