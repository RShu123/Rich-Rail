package newdomain;

public class Wagon1 implements newWagon {
	
	private String naam;
	private int aantalStoelen;
	private String type;
	private String color;
	
	public Wagon1(String nm) {
		naam = nm;
		aantalStoelen = 20;
		type = "wagon";
		color = "orange";
	}
	
	@Override
	public int getStoel() {
		return aantalStoelen;
	}

	@Override
	public String getNaam() {
		return naam;
	}

	@Override
	public void delete() {
	}
	
	public String toString() {
    	String s = naam + " met aantal stoelen: " + aantalStoelen;
    	
    	return s;
    }

}
