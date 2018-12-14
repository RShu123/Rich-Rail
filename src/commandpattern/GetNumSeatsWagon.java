package commandpattern;

import domain.Wagon;

public class GetNumSeatsWagon implements Command{
	
	private Wagon wagon;
	
	public GetNumSeatsWagon(Wagon newWagon) {
		wagon = newWagon;
	}

	@Override
	public void execute() {
		System.out.println(wagon.getStoel());
		
	}

}
