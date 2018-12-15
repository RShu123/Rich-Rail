package commandpattern;

import domain.Locomotief;
import factory.LocomotiefFactory;
import gui.RichRail;


public class DeleteLocomotief implements Command{

    private LocomotiefFactory locomotiefFactory = new LocomotiefFactory();
    private Locomotief locomotief;

    public DeleteLocomotief(Locomotief newLoco){

        locomotief = newLoco;

    }

    @Override
    public void execute() {
        RichRail.locomotiefFactory.deleteLocomotief(locomotief);
        System.out.println("gelukt");
    }
}
