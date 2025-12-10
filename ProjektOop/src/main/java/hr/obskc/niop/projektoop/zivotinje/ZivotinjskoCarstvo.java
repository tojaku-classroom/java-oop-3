package hr.obskc.niop.projektoop.zivotinje;

import java.util.ArrayList;

public class ZivotinjskoCarstvo {
    
    private ArrayList<Zivotinja> zivotinje;

    public ZivotinjskoCarstvo() {
        zivotinje = new ArrayList<>();
    }
    
    public void dodajZivotinju(Zivotinja zivotinja) {
        zivotinje.add(zivotinja);
    }
    
    public void prikaziSveZivotinje() {
        if (zivotinje.isEmpty()) {
            System.out.println("Nema niti jedne životinje");
            return;
        }
        
        for (Zivotinja zivotinja : zivotinje) {
            System.out.println("Ime: " + zivotinja.getIme() + ", Starost: " + zivotinja.getStarost());
            zivotinja.glasajSe();
        }
    }
    
    public Zivotinja traziZivotinjuPoImenu(String ime) throws Exception {
        for (Zivotinja zivotinja : zivotinje) {
            if (zivotinja.getIme().equalsIgnoreCase(ime)) {
                return zivotinja;
            }
        }
        
        throw new Exception("Životinja s imenom " + ime + " ne postoji.");
    }
    
}
