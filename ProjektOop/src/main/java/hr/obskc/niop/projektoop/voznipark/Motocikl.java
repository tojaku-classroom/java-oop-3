package hr.obskc.niop.projektoop.voznipark;

public class Motocikl extends Vozilo {

    public Motocikl(String ma, String mo, int gp, Stanje s) {
        super(ma, mo, gp, s);
    }

    @Override
    public String getTip() {
        return "Motocikl";
    }

}
