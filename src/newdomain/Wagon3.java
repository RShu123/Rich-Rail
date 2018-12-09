package newdomain;

public class Wagon3 implements newWagon {
	
	private String naam;
	private int aantalStoelen;
	private String type;
	private String color;
	
	public Wagon3(String nm, int stoel) {
		naam = nm;
		aantalStoelen = stoel;
		type = "wagon";
		color = "green";
	}

	@Override
	public int getStoel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getNaam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}
	
	public String toString() {
    	String s = naam + " met aantal stoelen: " + aantalStoelen;
    	
    	return s;
    }

}
