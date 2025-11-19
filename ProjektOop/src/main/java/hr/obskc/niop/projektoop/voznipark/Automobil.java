package hr.obskc.niop.projektoop.voznipark;

public class Automobil extends Vozilo {

    public Automobil(String ma, String mo, int gp, Stanje s) {
        super(ma, mo, gp, s);
    }

    @Override
    public String getTip() {
        return "Automobil";
    }
    
}
