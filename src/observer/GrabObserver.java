package observer;

import domain.Locomotief;

public class GrabObserver implements Observer {
	
	private Locomotief locomotief;
	
	private static int observerIDTracker = 0;
	
	private int observerID;
	
	private Subject grabber;
	
	public GrabObserver(Subject newGrabber) {
		this.grabber = newGrabber;
		this.observerID = ++observerIDTracker;
		
		System.out.println("new observer" + this.observerID);
		
		grabber.register(this);
	}

	@Override
	public void update(Locomotief loco) {
		this.locomotief = loco;
		
	}

}
