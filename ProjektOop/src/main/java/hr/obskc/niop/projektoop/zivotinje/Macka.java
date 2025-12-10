package hr.obskc.niop.projektoop.zivotinje;

public class Macka extends Zivotinja implements Pokretno {

    public Macka(String ime, int starost) {
        super(ime, starost);
    }

    @Override
    public void glasajSe() {
        System.out.println(getIme() + " kaže Mijau!");
    }

    @Override
    public void kreciSe() {
        System.out.println(getIme() + " skače.");
    }
    
}
