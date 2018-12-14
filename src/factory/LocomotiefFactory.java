package factory;

import java.util.ArrayList;

import domain.Locomotief;

public class LocomotiefFactory {

	private ArrayList<Locomotief> alleLocomotieven = new ArrayList<>();
	private Locomotief locomotyfus = new Locomotief("test");
	boolean locomotiefExists = false;
	
	public void checkIfExists(String naam) {
		for (Locomotief loco : alleLocomotieven) {
			if (loco.getNaam().equals(naam)) {
				locomotiefExists = true;
			}
		}
	}
	
	public Locomotief makeLocomotief(String command)throws ArrayIndexOutOfBoundsException {
		if (command.startsWith("new")) {
		String[] splitted = command.split(" ");
		String naam = splitted[2];
		
		checkIfExists(naam);
		
		if (command.startsWith("new train")) {
		if(!locomotiefExists) {
			Locomotief locomotief = new Locomotief(naam);
			alleLocomotieven.add(locomotief);
			return locomotief;
		}
		
	}
}
		return null;

}
	
	public ArrayList<Locomotief> getList(){
		return alleLocomotieven;
			
	}
	
	public int getListIndex() {
		int index =  alleLocomotieven.size();
		return index;
	}
	
	
}
