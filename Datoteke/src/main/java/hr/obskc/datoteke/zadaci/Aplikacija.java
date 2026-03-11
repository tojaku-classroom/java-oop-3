package hr.obskc.datoteke.zadaci;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Aplikacija {

    private static final String TEKST_DAT = "zadaci.txt";
    private static final String BIN_DAT = "zadaci.ser";
    private static final Path NIO_DAT = Path.of("zadaci_nio.txt");
    private static final Path POSTAVKE = Path.of("zadaci_postavke.properties");
    private static final Path ARHIVA = Path.of("zadaci_backup.zip");

    private static List<Zadatak> zadaci = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        ucitajPostavke();
        Scanner sc = new Scanner(System.in);
        boolean radi = true;

        while (radi) {
            ispisiIzbornik();
            String unos = sc.nextLine().trim();
            System.out.println("-".repeat(50));

            switch (unos) {
                case "1" ->
                    dodajZadatak(sc);
                case "2" ->
                    oznaciGotovim(sc);
                case "3" ->
                    ispisiZadatke();
                case "4" ->
                    spremiSve();
                case "5" ->
                    ucitajSve();
                case "6" ->
                    napraviSigurnosnuKopiju();
                case "0" ->
                    radi = false;
                default ->
                    System.out.println("Nepoznata opcija!");
            }
        }

        System.out.println("Doviđenja!");
        sc.close();
    }

    private static void ispisiIzbornik() {
        System.out.println("\n===== UPRAVITELJ ZADACIMA =====");
        System.out.println("1. Dodaj zadatak");
        System.out.println("2. Označi zadatak gotovim");
        System.out.println("3. Prikaži sve zadatke");
        System.out.println("4. Spremi (tekst, binarno, NIO)");
        System.out.println("5. Učitaj iz datoteke");
        System.out.println("6. Napravi sigurnosnu kopiju (ZIP)");
        System.out.println("0 = IZLAZ");
        System.out.print("> ");
    }

    private static void ucitajPostavke() {
        if (!Files.exists(POSTAVKE)) {
            spremiPocetnePostavke();
        }

        Properties p = new Properties();
        try (InputStream is = Files.newInputStream(POSTAVKE)) {
            p.load(is);
            System.out.println("=".repeat(50));
            System.out.println(" " + p.getProperty("apl.naziv", "nepoznato"));
            System.out.println(" " + p.getProperty("apl.verzija", "nepoznato"));
            System.out.println(" " + p.getProperty("apl.maks_znakova", "250"));
            System.out.println("=".repeat(50));
        } catch (IOException e) {
            System.err.println("Greška čitanja postavki");
        }
    }

    private static void spremiPocetnePostavke() {
        Properties p = new Properties();
        p.setProperty("apl.naziv", "Upravitelj zadacima");
        p.setProperty("apl.verzija", "1.0 BETA");
        p.setProperty("apl.maks_znakova", "250");
        try (OutputStream os = Files.newOutputStream(POSTAVKE)) {
            p.store(os, "Postavke aplikacije");
        } catch (IOException e) {
            System.err.println("Greška spremanja postavki");
        }
    }

    private static void dodajZadatak(Scanner sc) {
        System.out.print("Opis zadatka: ");
        String opis = sc.nextLine().trim();
        System.out.print("Prioritet (1 = nizak; 2 = srednji; 3 = visok): ");
        int prioritet = 2;
        try {
            prioritet = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
        }

        Zadatak novi = new Zadatak(nextId++, opis, false, prioritet);
        zadaci.add(novi);
        System.out.println("Dodan " + novi);
    }

    private static void oznaciGotovim(Scanner sc) {
        System.out.println("ID zadatka: ");
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            zadaci.stream()
                    .filter(z -> z.getId() == id)
                    .findFirst()
                    .ifPresentOrElse(z -> {
                        z.setGotovo(true);
                        System.out.println("Zadatak gotov: " + z);
                    }, () -> System.out.println("Zadatak nije pronađen"));
        } catch (NumberFormatException e) {
            System.err.println("Neispravan ID");
        }
    }

    private static void ispisiZadatke() {
        if (zadaci.isEmpty()) {
            System.out.println("Nema zadataka");
            return;
        }

        System.out.println("Ukupno zadataka " + zadaci.size());
        zadaci.forEach(System.out::println);
    }

    private static void spremiSve() {
        TekstSpremanje.spremi(zadaci, TEKST_DAT);
        ObjektSpremanje.spremi(zadaci, BIN_DAT);
        NioSpremanje.spremi(zadaci, NIO_DAT);
        System.out.println("Zadaci spremljeni u sve datoteke");
    }

    private static void ucitajSve() {
        List<Zadatak> izTekst = TekstSpremanje.ucitaj(TEKST_DAT);
        List<Zadatak> izBin = ObjektSpremanje.ucitaj(BIN_DAT);
        List<Zadatak> izNio = NioSpremanje.ucitaj(NIO_DAT);

        if (!izNio.isEmpty()) {
            zadaci = new ArrayList<>(izNio);
            nextId = zadaci.stream().mapToInt(Zadatak::getId).max().orElse(0) + 1;
        }
    }

    private static void napraviSigurnosnuKopiju() {
        if (!Files.exists(NIO_DAT)) {
            System.out.println("Nemamo što pohraniti");
            return;
        }

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(ARHIVA.toFile()))) {
            ZipEntry unos = new ZipEntry(NIO_DAT.toFile().getName());
            zos.putNextEntry(unos);
            Files.copy(NIO_DAT, zos);
            zos.closeEntry();
            System.out.println("Sigurnosna kopija napravljena");
        } catch (IOException e) {
            System.err.println("Greška stvaranja sigurnosne kopije");
        }
    }
}
