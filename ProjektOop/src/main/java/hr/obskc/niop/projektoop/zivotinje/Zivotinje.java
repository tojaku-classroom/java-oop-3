package hr.obskc.niop.projektoop.zivotinje;

public class Zivotinje {

    public static void main(String[] args) {
        ZivotinjskoCarstvo carstvo = new ZivotinjskoCarstvo();
        
        carstvo.dodajZivotinju(new Pas("Pero", 3));
        carstvo.dodajZivotinju(new Pas("Luna", 7));
        carstvo.dodajZivotinju(new Macka("Miki", 8));
        carstvo.dodajZivotinju(new Macka("Mugi", 4));
        carstvo.dodajZivotinju(new Skoljka("Bojler", 1));
        carstvo.dodajZivotinju(new Skoljka("Ružica", 2));
        
        carstvo.prikaziSveZivotinje();
        
        try {
            Zivotinja z = carstvo.traziZivotinjuPoImenu("Pero");
            System.out.println("Životinja je nađena: " + z.getIme());
            z.glasajSe();
            
            if (z instanceof Pokretno) {
                ((Pokretno) z).kreciSe();
            }
        } catch (Exception e) {
            System.err.println("Greška: " +  e.getMessage());
        }
    }
    
}
