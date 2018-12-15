package commandpattern;

import domain.Wagon;
import gui.RichRail;

import java.awt.*;

public class GetNumSeatsWagon implements Command{
	
	private Wagon wagon;

	Graphics output = RichRail.outputPanel.getGraphics();

	public GetNumSeatsWagon(Wagon newWagon) {
		wagon = newWagon;
	}

	@Override
	public void execute() {

		RichRail.currentNumberOfOutputs+=20;
		output.setColor(Color.white);
		output.drawString("number of seats in wagon " + wagon.getNaam() + ": " + String.valueOf(wagon.getStoel()),5,RichRail.currentNumberOfOutputs);
		
	}

}
