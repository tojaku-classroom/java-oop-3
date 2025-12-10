package hr.obskc.niop.projektoop.zivotinje;

public class Skoljka extends Zivotinja {

    public Skoljka(String ime, int starost) {
        super(ime, starost);
    }

    @Override
    public void glasajSe() {
        System.out.println(getIme() + " kaže Blup!");
    }
    
}
