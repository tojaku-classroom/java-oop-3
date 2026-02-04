package hr.obskc.datoteke.liste;

import java.io.Serializable;

public class Ucenik implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String ime;
    private int starost;
    private double prosjek;

    public Ucenik(int id, String ime, int starost, double prosjek) {
        this.id = id;
        this.ime = ime;
        this.starost = starost;
        this.prosjek = prosjek;
    }

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public double getProsjek() {
        return prosjek;
    }

    public int getStarost() {
        return starost;
    }

    @Override
    public String toString() {
        return String.format("Student: [%d] %s (%d) %.2f", id, ime, starost, prosjek);
    }
}
