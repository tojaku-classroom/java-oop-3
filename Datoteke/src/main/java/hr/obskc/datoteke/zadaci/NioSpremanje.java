package hr.obskc.datoteke.zadaci;

import hr.obskc.datoteke.liste.Ucenik;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class NioSpremanje {

    private static final String SEPARATOR = "|";

    public static void spremi(List<Zadatak> zadaci, Path putanja) {
        StringBuilder sb = new StringBuilder();
        for (Zadatak zadatak : zadaci) {
            sb.append(String.join(SEPARATOR,
                    String.valueOf(zadatak.getId()),
                    zadatak.getOpis(),
                    String.valueOf(zadatak.isGotovo()),
                    String.valueOf(zadatak.getPrioritet())
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

    public static List<Zadatak> ucitaj(Path putanja) {
        List<Zadatak> zadaci = new ArrayList<>();

        if (!Files.exists(putanja)) {
            System.err.println("Datoteka ne postoji: " + putanja);
            return zadaci;
        }

        try {
            List<String> linije = Files.readAllLines(putanja);

            for (String linija : linije) {
                if (linija.isBlank()) {
                    continue;
                }

                try {
                    String[] dijelovi = linija.split("\\" + SEPARATOR);
                    int id = Integer.parseInt(dijelovi[0]);
                    String opis = dijelovi[1];
                    boolean gotovo = Boolean.parseBoolean(dijelovi[2]);
                    int prioritet = Integer.parseInt(dijelovi[3]);

                    zadaci.add(new Zadatak(id, opis, gotovo, prioritet));
                } catch (NumberFormatException e) {
                    System.err.println("Greška obrade linije: " + linija);
                }
            }
            System.out.println("Učitano " + zadaci.size() + " zadataka iz " + putanja);
        } catch (IOException e) {
            System.err.println("Greška čitanja: " + e.getMessage());
        }

        return zadaci;
    }

    public static void dodaj(Zadatak zadatak, Path putanja) {
        String linija = String.join(SEPARATOR,
                String.valueOf(zadatak.getId()),
                zadatak.getOpis(),
                String.valueOf(zadatak.isGotovo()),
                String.valueOf(zadatak.getPrioritet())
        ) + System.lineSeparator();

        try {
            Files.writeString(putanja, linija, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Zadatak je dodan: " + zadatak);
        } catch (IOException e) {
            System.err.println("Greška dodavanja: " + e.getMessage());
        }
    }
}
