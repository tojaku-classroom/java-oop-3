package hr.obskc.datoteke.liste;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ObjektSpremanje {

    public static void spremi(List<Ucenik> ucenici, String naziv) {
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(naziv));
            oos.writeObject(ucenici);
            System.out.println("Podaci spremljeni");
        } catch (IOException e) {
            System.err.println("Greška pisanja: " + e.getMessage());
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static List<Ucenik> ucitaj(String naziv) {
        List<Ucenik> ucenici = new ArrayList<>();
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(naziv));
            ucenici = (List<Ucenik>) ois.readObject();
            System.out.println("Podaci učitani");
        } catch (FileNotFoundException e) {
            System.err.println("Datoteka ne postoji");
        } catch (IOException e) {
            System.err.println("Greška čitanja: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Klasa ne postoji");
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
            }
        }

        return ucenici;
    }

}
