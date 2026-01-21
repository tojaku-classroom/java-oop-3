package hr.obskc.datoteke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Datoteke {

    public static void main(String[] args) {
        System.out.println("Datoteke");
        // pisanjeTekstnihDatoteka();
        // citanjeTekstnihDatoteka();
        // pisanjeBinarnihDatoteka();
        citanjeBinarneDatoteke();
    }

    public static void pisanjeTekstnihDatoteka() {
        // Metoda 1: Upotreba FileWriter klase (jednostavan pristup)
        try (FileWriter fw = new FileWriter("metoda1.txt"/*, true*/)) { // true za dodavanje bez brisanja
            fw.write("Dobar dan, svijete!\n");
            fw.write("Ovo je primjer pisanja u datoteku pomoću klase FileWriter.");
            System.out.println("Uspješno zapisano u datoteku metodom 1");
        } catch (IOException ex) {
            System.err.println("Greška pisanja datoteke: " + ex.getMessage());
        }

        // Metoda 2: Upotreba BufferedWriter klase (bolje performanse)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("metoda2.txt"))) {
            bw.write("Upotreba BufferedWriter-a");
            bw.newLine();
            bw.write("Nova linija");
            System.out.println("Uspješno zapisano u datoteku metodom 2");
        } catch (IOException ex) {
            System.err.println("Greška pisanja datoteke: " + ex.getMessage());
        }

        // Metoda 3: Java NIO (New Input/Output)
        try {
            String sadrzaj = "Ovo je sadržaj koji ćemo upisati\n";
            Files.writeString(Path.of("metoda3.txt"), sadrzaj, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Uspješno zapisano u datoteku metodom 3");
        } catch (IOException ex) {
            System.err.println("Greška pisanja datoteke: " + ex.getMessage());
        }
    }

    public static void citanjeTekstnihDatoteka() {
        // Metoda 1: Upotreba BufferedReader klase
        try (BufferedReader br = new BufferedReader(new FileReader("citanje.txt"))) {
            String linija;
            while ((linija = br.readLine()) != null) {
                System.out.println("Nova linija teksta: " + linija);
            }
        } catch (IOException ex) {
            System.err.println("Greška čitanja datoteke: " + ex.getMessage());
        }

        // Metoda 2: NIO - čitanje linije po linije
        try {
            System.out.println("Čitanje linija pomoću NIO:");
            List<String> linije = Files.readAllLines(Path.of("citanje.txt"));
            linije.forEach(System.out::println);
        } catch (IOException ex) {
            System.err.println("Greška čitanja datoteke: " + ex.getMessage());
        }

        // Metoda 3: NIO - čitanje kompletne datoteke
        try {
            System.out.println("Čitanje cijele datoteke pomoću NIO:");
            String sadrzaj = Files.readString(Path.of("citanje.txt"));
            System.out.println(sadrzaj);
        } catch (IOException ex) {
            System.err.println("Greška čitanja datoteke: " + ex.getMessage());
        }
    }

    public static void pisanjeBinarnihDatoteka() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("binarna.bin"))) {
            dos.writeInt(42);
            dos.writeDouble(3.141592);
            dos.writeBoolean(true);
            dos.writeUTF("Ovo je tekst");
            System.out.println("Podaci u binarnu datoteku zapisani");
        } catch (IOException ex) {
            System.err.println("Greška pisanje binarne datoteke: " + ex.getMessage());
        }
    }

    public static void citanjeBinarneDatoteke() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("binarna.bin"))) {
            int cijeliBroj = dis.readInt();
            double realanBroj = dis.readDouble();
            boolean istinaIliLaz = dis.readBoolean();
            String znakovniNiz = dis.readUTF();
            System.out.println("Podaci: " + cijeliBroj + " " + realanBroj
                    + " " + istinaIliLaz + " " + znakovniNiz);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Greška čitanja binarne datoteke: " + ex.getMessage());
        }
    }
}
