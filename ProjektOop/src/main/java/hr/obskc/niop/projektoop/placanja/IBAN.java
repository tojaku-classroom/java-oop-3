package hr.obskc.niop.projektoop.placanja;

public class IBAN implements MetodaPlacanja {
    public String iban;
    private double stanje = 0;

    @Override
    public void izvrsiPlacanje(double iznos) {
        if (this.iban == null) {
            System.err.println("Morate definirati IBAN!");
        } else if (iznos > this.stanje) {
            System.err.println("Nemate dovoljno sredstava!");
        } else {
            this.stanje -= iznos;
            System.out.println("Plaćanje je izvršeno s IBANa: " + iban + ". Naplaćeni iznos: " + iznos + " EUR.");
        }
    }

    @Override
    public String opisMetode() {
        return "IBAN";
    }
    
    public void dodajNovac(double iznos) {
        stanje += iznos;
    }

    public double getStanje() {
        return stanje;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

}
