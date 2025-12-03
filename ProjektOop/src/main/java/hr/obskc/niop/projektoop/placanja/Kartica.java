package hr.obskc.niop.projektoop.placanja;

public class Kartica implements MetodaPlacanja {
    private double maksimalniIznos = 300;
    private double potrosnja = 0;

    @Override
    public void izvrsiPlacanje(double iznos) {
        if (iznos > this.maksimalniIznos) {
            System.err.println("Plaćanje odbijeno!");
        } else {
            this.potrosnja += iznos;
            System.out.println("Kartica je terećena za iznos " + iznos + " EUR.");
        }
    }

    @Override
    public String opisMetode() {
        return "Kartica";
    }

    public double getPotrosnja() {
        return potrosnja;
    }
}
