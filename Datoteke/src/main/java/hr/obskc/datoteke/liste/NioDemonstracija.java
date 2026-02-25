package hr.obskc.datoteke.liste;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class NioDemonstracija {

    private static final String DELIMITER = "|";

    public static void main(String[] args) {
        List<Ucenik> ucenici = pripremiTestnePodatke();
        
        Path datoteka = Path.of("ucenici_nio.txt");
        Path arhiva = Path.of("arhiva", "2026");
        
        System.out.println("=".repeat(60));
        System.out.println("1. Pisanje datoteke pomoću Files.writeString()");
        System.out.println("=".repeat(60));
        spremi(ucenici, datoteka);
        
        System.out.println("=".repeat(60));
        System.out.println("2. Čitanje datoteke pomoću Files.readAllLines()");
        System.out.println("=".repeat(60));
        List<Ucenik> ucitani = ucitaj(datoteka);
        ispisUcenika(ucitani);
        
        System.out.println("=".repeat(60));
        System.out.println("3. Dodavanje novog učenike");
        System.out.println("=".repeat(60));
        Ucenik novi = new Ucenik(11, "Marija", 16, 4.9);
        dodaj(novi, datoteka);
        List<Ucenik> nakon = ucitaj(datoteka);
        ispisUcenika(nakon);
        
        System.out.println("=".repeat(60));
        System.out.println("4. Informacije o datoteci");
        System.out.println("=".repeat(60));
        informacije(datoteka);
        
        System.out.println("=".repeat(60));
        System.out.println("5. Kreiranje direktorija i arhiviranje");
        System.out.println("=".repeat(60));
        arhiviraj(ucenici, arhiva);
        
        System.out.println("=".repeat(60));
        System.out.println("6. Pregled popisa tekstnih datototeka");
        System.out.println("=".repeat(60));
        popisTxtDatoteka(Path.of("."));
        
        System.out.println("=".repeat(60));
        System.out.println("Kraj");
        System.out.println("=".repeat(60));
    }

    public static void spremi(List<Ucenik> ucenici, Path putanja) {
        StringBuilder sb = new StringBuilder();
        for (Ucenik u : ucenici) {
            sb.append(String.join(DELIMITER,
                    String.valueOf(u.getId()),
                    u.getIme(),
                    String.valueOf(u.getStarost()),
                    String.valueOf(u.getProsjek())
            ));
            sb.append(System.lineSeparator());
        }

        try {
            Files.writeString(putanja, sb.toString());
            System.out.println("Datoteka spremljena");
        } catch (IOException e) {
            System.err.println("Greška spremanja: " + e.getMessage());
        }
    }

    public static List<Ucenik> ucitaj(Path putanja) {
        List<Ucenik> ucenici = new ArrayList<>();

        if (!Files.exists(putanja)) {
            System.err.println("Datoteka ne postoji: " + putanja);
            return ucenici;
        }

        try {
            List<String> linije = Files.readAllLines(putanja);

            for (String linija : linije) {
                if (linija.isBlank()) {
                    continue;
                }

                try {
                    String[] dijelovi = linija.split("\\" + DELIMITER);
                    int id = Integer.parseInt(dijelovi[0]);
                    String ime = dijelovi[1];
                    int starost = Integer.parseInt(dijelovi[2]);
                    double prosjek = Double.parseDouble(dijelovi[3]);

                    ucenici.add(new Ucenik(id, ime, starost, prosjek));
                } catch (NumberFormatException e) {
                    System.err.println("Greška obrade linije: " + linija);
                }
            }
            System.out.println("Učitano " + ucenici.size() + " učenika iz " + putanja);
        } catch (IOException e) {
            System.err.println("Greška čitanja: " + e.getMessage());
        }

        return ucenici;
    }

    public static void dodaj(Ucenik ucenik, Path putanja) {
        String linija = String.join(DELIMITER,
                String.valueOf(ucenik.getId()),
                ucenik.getIme(),
                String.valueOf(ucenik.getStarost()),
                String.valueOf(ucenik.getProsjek())
        );

        try {
            Files.writeString(putanja, linija, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Učenik je dodan: " + ucenik);
        } catch (IOException e) {
            System.err.println("Greška dodavanja: " + e.getMessage());
        }
    }

    public static void informacije(Path putanja) {
        System.out.println("Putanja: " + putanja.toAbsolutePath());
        System.out.println("Postoji: " + Files.exists(putanja));
        System.out.println("Čitljiva: " + Files.isReadable(putanja));
        System.out.println("Zapisiva: " + Files.isWritable(putanja));

        try {
            System.out.println("Veličina: " + Files.size(putanja) + " bajtova");
        } catch (IOException e) {
            System.err.println("Greška provjere veličine: " + e.getMessage());
        }
    }

    public static void arhiviraj(List<Ucenik> ucenici, Path mapa) {
        try {
            Files.createDirectories(mapa);
            System.out.println("Mapa je stvorena: " + mapa.toAbsolutePath());

            Path arhiva = mapa.resolve("arhiva.txt");
            spremi(ucenici, arhiva);
        } catch (IOException e) {
            System.err.println("Greška stvaranja arhive: " + e.getMessage());
        }
    }

    public static void popisTxtDatoteka(Path mapa) {
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(mapa, "*.txt")) {
            System.out.println("Tekstne datoteke u: " + mapa.toAbsolutePath());
            boolean postoji = false;
            for (Path p : ds) {
                System.out.println(" - " + p.getFileName());
                postoji = true;
            }
            if (!postoji) {
                System.out.println(" (nema tekstnih datoteka)");
            }
        } catch (IOException e) {
            System.err.println("Greška provjere: " + e.getMessage());
        }
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
                System.out.println(" - " + ucenik.toString());
            }
        } else {
            System.out.println("Nema učenika");
        }
    }

}
