package hr.obskc.datoteke.liste;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class NioDemonstracija {

    private static final String DELIMITER = "|";

    public static void main(String[] args) {

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
        
    }

}
