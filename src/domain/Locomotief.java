package domain;

public class Locomotief {

    private String naam;
    private int aantalStoelen;

    public Locomotief(String nm){
        naam = nm;
        aantalStoelen = 180;
    }

    public int getAantalStoelen() {
        return aantalStoelen;
    }

    public String getNaam() {
        return naam;
    }

}
