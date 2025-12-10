package hr.obskc.niop.projektoop.zivotinje;

public class Pas extends Zivotinja implements Pokretno {

    public Pas(String ime, int starost) {
        super(ime, starost);
    }

    @Override
    public void glasajSe() {
        System.out.println(getIme() + " kaže Vau!");
    }

    @Override
    public void kreciSe() {
        System.out.println(getIme() + " trči.");
    }
    
}
