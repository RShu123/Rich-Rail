package commandpattern;

import domain.Locomotief;

public class GetNumSeatsLocomotief implements Command {
	
	Locomotief locomotief;
	
	public GetNumSeatsLocomotief(Locomotief newLoco) {
		locomotief = newLoco;
	}

	@Override
	public void execute() {
		
		System.out.println(locomotief.getAantalStoelen());
		
	}

}
