package hr.obskc.niop.projektoop.zivotinje;

abstract class Zivotinja {
    private String ime;
    private int starost;

    public Zivotinja(String ime, int starost) {
        this.ime = ime;
        this.starost = starost;
    }

    public String getIme() {
        return ime;
    }

    public int getStarost() {
        return starost;
    }
    
    public abstract void glasajSe();
}
