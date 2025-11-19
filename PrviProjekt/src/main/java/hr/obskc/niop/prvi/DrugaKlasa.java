package hr.obskc.niop.prvi;

import java.util.Scanner;
import java.util.ArrayList;

public class DrugaKlasa {

    public static void main(String[] args) {
        int mojaPrvaVarijabla = 10;
        double mojaPrvaRealnaVarijabla = 10.5;
        char znak = 'a';
        String znakovniNiz = "Dobar dan!";

        //znakovniNizovi();
        //nizovi();
        //unosPodataka();
        //slucajniBrojevi();
        //liste();
        iznimke();
    }

    public static void znakovniNizovi() {
        String test1 = "Dobar dan";
        String test2 = new String("Dobar dan");

        if (test1.equals(test2)) {
            System.out.println("Nizovi su jednaki!");
        } else {
            System.out.println("Nizovi nisu jednaki!");
        }

        String izreka = "Tko rani rani, umoran je cijeli dan.";
        System.out.println("Izreka je duga: " + izreka.length());
        System.out.println("Riječ umoran nalazi se na: " + izreka.indexOf("umoran"));
        System.out.println("Na indeksu 10 nalazi se znak: " + izreka.charAt(10));
        System.out.println("Između indeksa 5 i 10 nalaze se znakovi: " + izreka.substring(5, 10));
        System.out.println("Nova izreka: " + izreka.replace("umoran", "sretan"));
    }

    public static void nizovi() {
        int[] brojevi;
        brojevi = new int[20];
        brojevi[0] = 10;
        int brojevi2[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println("Niz brojeva 2 je velik: " + brojevi2.length);
        for (int i = 0; i < brojevi2.length; i++) {
            System.out.println("brojevi2[" + i + "]: " + brojevi2[i]);
        }
        for (int br : brojevi2) {
            System.out.println("Novi broj: " + br);
        }
    }

    public static void unosPodataka() {
        Scanner s = new Scanner(System.in);
        int suma = 0;
        System.out.println("Kako se zoveš?");
        String ime = s.nextLine();
        String pitanje = String.format("%s, unesi cijeli broj, kada želiš završiti unesi -1.", ime);
        System.out.println(pitanje);
        int broj = s.nextInt();
        while (broj != -1) {
            suma += broj;
            broj = s.nextInt();
        }
        String odgovor = String.format("%s, tvoja suma je %d", ime, suma);
        System.out.println(odgovor);
    }

    public static void slucajniBrojevi() {
        Scanner s = new Scanner(System.in);
        System.out.println("Koliko puta želiš baciti kockicu?");
        int broj = s.nextInt();
        for (int i = 0; i < broj; i++) {
            // double slucajni = Math.random();
            int kockica = 1 + (int) (Math.random() * 6);
            String odgovor = String.format("%d. broj je %d", i + 1, kockica);
            System.out.println(odgovor);
        }
    }

    public static void liste() {
        ArrayList lista = new ArrayList();
        Scanner s = new Scanner(System.in);
        System.out.println("Unesite nekoliko riječi, za kraj unesite -1.");
        String rijec = s.nextLine();
        while (!rijec.equalsIgnoreCase("-1")) {
            lista.add(rijec);
            rijec = s.nextLine();
        }
        System.out.println(String.format("Unijeli ste ukupno %d riječi.", lista.size()));
        System.out.print("Vaše riječi: ");
        for (Object r : lista) {
            System.out.print((String) r + " ");
        }

        System.out.println("\nKoja te riječ zanima?");
        String trazi = s.nextLine();
        int indeksTrazeneRijeci = lista.indexOf(trazi);
        System.out.println(String.format("Tvoja riječ se nalazi na %d. mjestu", indeksTrazeneRijeci + 1));

        System.out.println("Želiš li izbrisati tu riječ? (da/ne)");
        String brisanje = s.nextLine();
        if (brisanje.equalsIgnoreCase("da")) {
            lista.remove(indeksTrazeneRijeci);
            System.out.println("Uspješno obrisano!");
            System.out.print("Vaša nova lista glasi: ");
            System.out.println(lista.toString());
        }
    }

    public static void iznimke() {
        try {
            int polje[] = new int[3];
            polje[0] = 1;
            polje[1] = 2;
            polje[2] = 3;
            polje[5] = 10;
            int a = 10 / 0;
        } catch (ArrayIndexOutOfBoundsException greska) {
            System.out.println("Dogodila se greška");
            System.out.println("Vrsta greške: " + greska.getMessage());
        } catch (ArithmeticException greska) {
            System.out.println("Dijeliš s nulom? To se ne smije...");
        } finally {
            System.out.println("Sve smo napravili, sada možemo dalje...");
        }

        System.out.println("Idemo dalje...");

        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Unesite broj manji od 10.");
            int broj = s.nextInt();
            if (broj >= 10) {
                throw new Exception("Pazi što radiš...");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        System.out.println("Idemo dalje...");
    }
}
