package hr.obskc.datoteke.liste;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TekstSpremanje {

    public static void spremi(List<Ucenik> ucenici, String naziv) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(naziv));

            for (Ucenik ucenik : ucenici) {
                String linija = ucenik.getId() + "," + ucenik.getIme() + ","
                        + ucenik.getStarost() + "," + ucenik.getProsjek(); // TODO potencijalni bug s delimiterom
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

    public static List<Ucenik> ucitaj(String naziv) {
        List<Ucenik> ucenici = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(naziv));
            String linija;
            while ((linija = br.readLine()) != null) {
                try {
                    String[] dijelovi = linija.split(",");

                    int id = Integer.parseInt(dijelovi[0]);
                    String ime = dijelovi[1];
                    int starost = Integer.parseInt(dijelovi[2]);
                    double prosjek = Double.parseDouble(dijelovi[3]);

                    ucenici.add(new Ucenik(id, ime, starost, prosjek));
                } catch (NumberFormatException e) {
                    System.err.println("Greška obrade linije: " + linija );
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

        return ucenici;
    }
}
