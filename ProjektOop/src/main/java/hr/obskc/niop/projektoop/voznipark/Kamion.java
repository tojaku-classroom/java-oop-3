package hr.obskc.niop.projektoop.voznipark;

public class Kamion extends Vozilo {

    public Kamion(String ma, String mo, int gp, Stanje s) {
        super(ma, mo, gp, s);
    }

    @Override
    public String getTip() {
        return "Kamion";
    }

}
