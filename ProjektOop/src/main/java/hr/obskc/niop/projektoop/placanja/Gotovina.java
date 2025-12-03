package hr.obskc.niop.projektoop.placanja;

public class Gotovina implements MetodaPlacanja {
    private double placeniIznos = 0;

    @Override
    public void izvrsiPlacanje(double iznos) {
        this.placeniIznos += iznos;
        System.out.println("Plaćanje u gotovini izvršeno. Iznos: " + iznos + " EUR.");
    }

    @Override
    public String opisMetode() {
        return "Gotovina";
    }

    public double getPlaceniIznos() {
        return placeniIznos;
    }
}
