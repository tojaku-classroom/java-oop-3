package hr.obskc.datoteke;

import java.io.Serializable;

public class Osoba implements Serializable { // 1. uvjet za serijalizaciju -> implements Serializable
    private static final long serialVersionUID = 1L; // 2. uvjet za serijalizaciju
    
    private String ime;
    private int starost;

    public Osoba(String ime, int starost) {
        this.ime = ime;
        this.starost = starost;
    }

    @Override
    public String toString() {
        return "Zovem se " + ime + " i imam " + starost + " godina";
    }
}