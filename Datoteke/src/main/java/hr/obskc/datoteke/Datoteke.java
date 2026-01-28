package hr.obskc.datoteke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Datoteke {

    public static void main(String[] args) {
        System.out.println("Datoteke");
        // pisanjeTekstnihDatoteka();
        // citanjeTekstnihDatoteka();
        // pisanjeBinarnihDatoteka();
        // citanjeBinarnihDatoteka();
        // upravljanjeDatotekama();
        // upravljanjeMapama();
        kopiranjeIPremjestanjeDatoteka();
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

    public static void citanjeBinarnihDatoteka() {
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
    
    public static void upravljanjeDatotekama() {
        File file = new File("operacije.txt");
        
        // Provjera
        if (file.exists()) {
            System.out.println("Datoteka postoji!");
        } else {
            System.out.println("Datoteka NE postoji!");
        }
        
        // Stvaranje
        try {
            boolean stvorena = file.createNewFile();
            if (stvorena) {
                System.out.println("Datoteka stvorena: " + file.getName());
            } else {
                System.out.println("Datoteka već postoji!");
            }
        } catch (IOException ex) {
            System.err.println("Dogodila se greška prilikom stvaranja datoteke.");
        }
        
        // Provjera metapodataka
        System.out.println("Apsolutna putanja: " + file.getAbsolutePath());
        System.out.println("Možemo li pisati: " + file.canWrite());
        System.out.println("Možemo li čitati: " + file.canRead());
        System.out.println("Veličina: " + file.length());
        System.out.println("Je li mapa: " + file.isDirectory());
        System.out.println("Je li datoteka: " + file.isFile());
        
        // Brisanje
        if (file.delete()) {
            System.out.println("Datoteka uspješno obrisana.");
        } else {
            System.out.println("Datoteka nije obrisana!");
        }
    }
    
    public static void upravljanjeMapama() {
        // Stvaranje
        File dir = new File("mojamapa");
        if (dir.mkdir()) {
            System.out.println("Mapa je uspješno stvorena.");
        }
        
        // Ugnježđene mape
        File ndir = new File("maparoditelj/mapadijete/mapaunuce");
        if (ndir.mkdirs()) {
            System.out.println("Mape su uspješno stvorene.");
        }
        
        // Listanje sadržaja mape
        File radna = new File(".");
        String[] popis = radna.list();
        System.out.println("U mapi se nalazi sljedeće:");
        if (popis != null) {
            for (String objekt : popis) {
                System.out.println("- " + objekt);
            }
        }
        
        // Listanje sadržaja mape kao objekti
        File[] popis2 = radna.listFiles();
        System.out.println("U mapi se nalaze sljedeći OBJEKTI:");
        if (popis2 != null) {
            for (File objekt2 : popis2) {
                System.out.println("- " + objekt2.getName() + 
                        " " + (objekt2.isDirectory() ? "mapa" : "datoteka") + "");
            }
        }
        
        // Brisanje mapa
        dir.delete();
        ndir.delete(); // <-- neće izbrisati jer nije prazna
    }
    
    public static void kopiranjeIPremjestanjeDatoteka() {
        try {
            // Definiranje izvora i odredišta
            Path izvor = Paths.get("izvor.txt");
            Path odrediste = Paths.get("odrediste.txt");
            
            // Stvaranje i pisanje u datoteku izvora
            Files.writeString(izvor, "Ovo je sadržaj datoteke izvora");
            
            // Kopiranje
            Files.copy(izvor, odrediste, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Kopiranje izvršeno");
            
            // Premještanje
            Path novaLokacija = Paths.get("./maparoditelj/premjesteno.txt");
            Files.move(izvor, novaLokacija, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Premještanje izvršeno");
        } catch (IOException ex) {
            System.err.println("Dogodila se greška!");
        }
    }
}
