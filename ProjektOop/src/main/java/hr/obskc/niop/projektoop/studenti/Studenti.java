package hr.obskc.niop.projektoop.studenti;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Studenti {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> studenti = new ArrayList<>();

        System.out.println("Koliko studenata želite unijeti?");
        int brojStudenata = 0;

        try {
            brojStudenata = scanner.nextInt();
            scanner.nextLine();

            if (brojStudenata < 1) {
                throw new InputMismatchException("Negativni brojevi nisu dozvoljeni!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Pogrešan unos! Dopušten je unos cijelih pozitivnih brojeva!");
            return;
        }

        for (int i = 0; i < brojStudenata; i++) {
            System.out.println("Unesite podatke za studenta " + (i + 1));
            System.out.print("Ime: ");
            String ime = scanner.nextLine();
            System.out.print("Prezime: ");
            String prezime = scanner.nextLine();
            int godinaStudija;
            double prosjekOcjena;

            try {
                System.out.print("Godina studija: ");
                godinaStudija = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Prosjek ocjena: ");
                prosjekOcjena = scanner.nextDouble();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Pogrešan unos! Dopušten je unos brojeva!");
                scanner.nextLine();
                i--;
                continue;
            }

            try {
                Student student = new Student(ime, prezime, godinaStudija, prosjekOcjena);
                studenti.add(student);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Pogrešan unos! Godina studija ili prosjek ocjena moraju biti u ispravnom obliku!");
                scanner.nextLine();
                i--;
                // continue;
            }
        }

        System.out.println("Podaci o unesenim studentima");
        for (Student novi : studenti) {
            novi.ispisiPodatke();
            System.out.println(" ");
        }
    }
}
