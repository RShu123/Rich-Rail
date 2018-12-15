package commandpattern;

import domain.Wagon;
import factory.WagonFactory;
import gui.RichRail;

public class DeleteWagon implements Command {

    private Wagon wagon;
    private WagonFactory wagonFactory = new WagonFactory();

    public DeleteWagon(Wagon newWagon){
        wagon = newWagon;
    }

    @Override
    public void execute() {
        try {
            RichRail.wagonFactory.deleteWagon(wagon);
            System.out.println("gelukt");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
