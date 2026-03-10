package hr.obskc.datoteke.zadaci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TekstSpremanje {

    private static final String SEPARATOR = "|";

    public static void spremi(List<Zadatak> zadaci, String naziv) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(naziv));

            for (Zadatak zadatak : zadaci) {
                String linija = zadatak.getId() + SEPARATOR + zadatak.getOpis() + SEPARATOR
                        + zadatak.isGotovo() + SEPARATOR + zadatak.getPrioritet();
                bw.write(linija);
                bw.newLine();
            }

            System.out.println("Podaci su spremljeni");
        } catch (IOException e) {
            System.err.println("Greška pisanja: " + e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
            }
        }
    }

    public static List<Zadatak> ucitaj(String naziv) {
        List<Zadatak> zadaci = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(naziv));
            String linija;
            while ((linija = br.readLine()) != null) {
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

            System.out.println("Podaci učitani");
        } catch (FileNotFoundException e) {
            System.err.println("Datoteka ne postoji");
        } catch (IOException e) {
            System.err.println("Greška čitanja: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
            }
        }

        return zadaci;
    }
}
