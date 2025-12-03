package hr.obskc.niop.projektoop.placanja;

import java.util.Scanner;

public class Placanja {

    public static void main(String[] args) {
        Novcanik novcanik = new Novcanik();
        
        Gotovina mojaGotovina = new Gotovina();
        Kartica mojaKartica = new Kartica();
        IBAN mojIban = new IBAN();
        mojIban.setIban("HR1234567890123456789");
        mojIban.dodajNovac(1000);
        
        novcanik.dodajMetodu(mojaGotovina);
        novcanik.dodajMetodu(mojaKartica);
        novcanik.dodajMetodu(mojIban);
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Odaberite opciju:");
            System.out.println("1. Prikaži dostupne metode plaćanja");
            System.out.println("2. Plati");
            System.out.println("3. Izlaz");
            
            int opcija = scanner.nextInt();
            
            if (opcija == 3) {
                System.out.println("Doviđenja!");
                return;
            }
            
            switch (opcija) {
                case 1 -> novcanik.prikaziMetode();
                case 2 -> {
                    System.out.println("Odaberite metodu plaćanja (gotovina, kartica ili IBAN).");
                    scanner.nextLine();
                    String odabranaMetoda = scanner.nextLine();
                    System.out.print("Unesite iznos za plaćanje: ");
                    double iznos = scanner.nextDouble();
                    novcanik.obradiPlacanje(odabranaMetoda, iznos);
                    
                    if (odabranaMetoda.equalsIgnoreCase("iban")) {
                        System.out.println("Novo stanje na vašem računu je: " + mojIban.getStanje());
                    }
                }
                default -> System.out.println("Nepoznata opcija, odaberite ponovno.");
            }
        }
    }
}
