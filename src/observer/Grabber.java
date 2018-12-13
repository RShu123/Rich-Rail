package observer;

import java.util.ArrayList;

import domain.Locomotief;

public class Grabber implements Subject{
	
	private ArrayList<Observer> allObservers;
	private Locomotief loco;
	
	public Grabber() {
		allObservers = new ArrayList<Observer>();
	}

	@Override
	public void register(Observer newObserver) {
		allObservers.add(newObserver);
	}

	@Override
	public void unregister(Observer deleteObserver) {
		
		int observerIndex = allObservers.indexOf(deleteObserver);
		System.out.println("Observer " + (observerIndex+1) + " deleted");
		allObservers.remove(observerIndex);
	}

	@Override
	public void notifyObserver() {
		for (Observer observer : allObservers) {
			observer.update(loco);
		}
	}
	
	public void setLocomotief(Locomotief newLocomotief) {
		this.loco = newLocomotief;
		notifyObserver(); 
	}

}
