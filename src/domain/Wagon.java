package domain;

public class Wagon {

    private String naam;
    private int aantalStoelen;
    private String type;
    
    public Wagon(String nm) {
    	naam = nm;
    	aantalStoelen = 20;
    	type = "wagon";
    }

    public Wagon(String nm, int aantStoel){
        naam = nm;
        aantalStoelen = aantStoel;
        type = "wagon";
    }

    public int getStoel(){
        return aantalStoelen;
    }
    
    public String getNaam() {
    	return naam;
    }

    public void delete(){};
    
    public String toString() {
    	String s = naam + " met aantal stoelen: " + aantalStoelen;
    	
    	return s;
    }

}
