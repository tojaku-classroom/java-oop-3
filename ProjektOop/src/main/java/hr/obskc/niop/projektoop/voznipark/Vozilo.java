package hr.obskc.niop.projektoop.voznipark;

// enumeracija
enum Stanje {
    NOVO,
    RABLJENO,
    OSTECENO
}

// apstrakna klasa znači da je ne možemo koristiti za stvanje objekata,
// već samo za proširenje (nasljeđivanje), kao osnova za druge klase
abstract class Vozilo {

    // podatkovni članovi klase
    private String marka;
    private String model;
    private int godinaProizvodnje;
    private Stanje stanje;

    // konstruktor klase
    public Vozilo(String ma, String mo, int gp, Stanje s) {
        this.marka = ma;
        this.model = mo;
        this.godinaProizvodnje = gp;
        this.stanje = s;
    }

    // getter
    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public int getGodinaProizvodnje() {
        return godinaProizvodnje;
    }

    public Stanje getStanje() {
        return stanje;
    }

    // apstraktna metoda prisiljava sve "korisnike" klase Vozilo
    // da imaju metodu getTip
    public abstract String getTip();

    // nadjačavanje metoda nadklase
    // nadjačavamo metodu toString bazne klase (nadklase) Object
    @Override
    public String toString() {
        return String.format("Tip: %s, Marka: %s, Model: %s, Godinu: %s, Stanje: %s", getTip(), this.marka,
                this.model, this.godinaProizvodnje, this.stanje);
    }
}
