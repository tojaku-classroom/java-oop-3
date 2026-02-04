package hr.obskc.datoteke.liste;

import java.util.ArrayList;
import java.util.List;

public class Demonstracija {

    public static void main(String[] args) {
        List<Ucenik> ucenici = pripremiTestnePodatke();
        
        System.out.println("=".repeat(60));
        System.out.println("Originalni podaci");
        System.out.println("=".repeat(60));
        
        ispisUcenika(ucenici);
        
        System.out.println("=".repeat(60));
        System.out.println("Metoda 1: tekst datoteka s parsiranjem");
        System.out.println("=".repeat(60));
        
        TekstSpremanje.spremi(ucenici, "ucenici.txt");
        List<Ucenik> ucitaniIzTekstne = TekstSpremanje.ucitaj("ucenici.txt");
        ispisUcenika(ucitaniIzTekstne);
        
        System.out.println("=".repeat(60));
        System.out.println("Metoda 2: serijalizacija");
        System.out.println("=".repeat(60));
        
        ObjektSpremanje.spremi(ucenici, "ucenici.ser");
        List<Ucenik> ucitaniIzSerijalizacije = ObjektSpremanje.ucitaj("ucenici.ser");
        ispisUcenika(ucitaniIzSerijalizacije);
        
        System.out.println("=".repeat(60));
        System.out.println("Kraj");
        System.out.println("=".repeat(60));
        
    }

    private static List<Ucenik> pripremiTestnePodatke() {
        List<Ucenik> testni = new ArrayList<>();
        testni.add(new Ucenik(1, "Pero", 15, 4.5));
        testni.add(new Ucenik(2, "Ivo", 14, 2.3));
        testni.add(new Ucenik(3, "Ana", 16, 3.4));
        testni.add(new Ucenik(4, "Ivana", 18, 4.7));
        testni.add(new Ucenik(5, "Đuro", 17, 5));
        testni.add(new Ucenik(6, "Kristijan", 15, 4.4));
        testni.add(new Ucenik(7, "Mirko", 17, 3.2));
        testni.add(new Ucenik(8, "Željko", 14, 1));
        testni.add(new Ucenik(9, "Dubravka", 19, 4.1));
        testni.add(new Ucenik(10, "Jagoda", 18, 3.5));
        return testni;
    }

    private static void ispisUcenika(List<Ucenik> ucenici) {
        if (!ucenici.isEmpty()) {
            for (Ucenik ucenik : ucenici) {
                System.out.println("- " + ucenik.toString());
            }
        } else {
            System.out.println("Nema učenika");
        }
    }

}
