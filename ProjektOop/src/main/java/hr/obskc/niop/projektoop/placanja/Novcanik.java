package hr.obskc.niop.projektoop.placanja;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Novcanik {
    private List<MetodaPlacanja> metode;

    public Novcanik() {
        this.metode = new ArrayList<>();
    }
    
    public void dodajMetodu(MetodaPlacanja metoda) {
        metode.add(metoda);
    }
    
    public void prikaziMetode() {
        if (metode.isEmpty()) {
            System.out.println("Nema dostupnih metoda plaćanja.");
        } else {
            System.out.println("Dostupne metode plaćanja:");
            metode.forEach(metoda -> System.out.println(" - " + metoda.opisMetode()));
        }
    }
    
    public void obradiPlacanje(String odabranaMetoda, double iznos) {
        Optional<MetodaPlacanja> pronadjenaMetoda = metode.stream()
                .filter(m -> m.opisMetode().equalsIgnoreCase(odabranaMetoda))
                .findFirst();
        
        if (pronadjenaMetoda.isPresent()) {
            pronadjenaMetoda.get().izvrsiPlacanje(iznos);
        } else {
            System.err.println("Tražena metoda plaćanja ne postoji!");
        }
    }
}
 