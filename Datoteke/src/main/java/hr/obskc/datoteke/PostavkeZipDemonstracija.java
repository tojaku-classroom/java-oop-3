package hr.obskc.datoteke;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class PostavkeZipDemonstracija {

    private static final Path POSTAVKE = Path.of("postavke.properties");
    private static final Path ARHIVA = Path.of("arhiva.zip");

    public static void main(String[] args) {
        
        System.out.println("=".repeat(60));
        System.out.println("1. Kreiranje postavki");
        System.out.println("=".repeat(60));
        spremiPostavke(POSTAVKE);
        
        System.out.println("=".repeat(60));
        System.out.println("2. Arhiviranje postavki");
        System.out.println("=".repeat(60));
        arhiviraj(POSTAVKE, ARHIVA);
        
        System.out.println("=".repeat(60));
        System.out.println("3. Brisanje originalne datoteke postavki");
        System.out.println("=".repeat(60));
        brisi(POSTAVKE);
        
        System.out.println("=".repeat(60));
        System.out.println("4. Odarhiviranje datoteke");
        System.out.println("=".repeat(60));
        odarhiviraj(ARHIVA, Path.of("."));
        
        System.out.println("=".repeat(60));
        System.out.println("5. Čitanje i prikaz postavki");
        System.out.println("=".repeat(60));
        prikaziPostavke(POSTAVKE);
        
        System.out.println("=".repeat(60));
        System.out.println("6. Dohvat pojedinih postavki");
        System.out.println("=".repeat(60));
        dohvatiPostavke(POSTAVKE);
        
        System.out.println("=".repeat(60));
        System.out.println("Kraj");
        System.out.println("=".repeat(60));
    }

    public static void spremiPostavke(Path putanja) {
        Properties postavke = new Properties();

        postavke.setProperty("baza.url", "localhost");
        postavke.setProperty("baza.port", "5432");
        postavke.setProperty("apl.naziv", "Sustav za upravljanje datotekama");
        postavke.setProperty("apl.verzija", "2.1.0");
        postavke.setProperty("apl.maksBrojUcenika", "1000");

        try (OutputStream os = Files.newOutputStream(putanja)) {
            postavke.store(os, "Konfiguracija aplikacije");
            System.out.println("Datoteka postavki kreirana: " + putanja.toAbsolutePath());
            System.out.println("Broj postavki: " + postavke.size());
        } catch (IOException e) {
            System.err.println("Greška spremanja postavki: " + e.getMessage());
        }
    }

    public static void arhiviraj(Path izvor, Path odrediste) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(odrediste.toFile()))) {
            ZipEntry unos = new ZipEntry(izvor.getFileName().toString());
            zos.putNextEntry(unos);
            Files.copy(izvor, zos);
            zos.closeEntry(); // Obavezno !
            System.out.println("Arhiva stvorena: " + odrediste.toAbsolutePath());
            System.out.println("Veličina arhive: " + Files.size(odrediste) + " bajtova");
        } catch (IOException e) {
            System.err.println("Greška arhiviranja: " + e.getMessage());
        }
    }

    public static void brisi(Path putanja) {
        try {
            boolean obrisano = Files.deleteIfExists(putanja);
            if (obrisano) {
                System.out.println("Datoteka je obrisana: " + putanja);
            } else {
                System.out.println("Datoteka ne postoji: " + putanja);
            }
        } catch (IOException e) {
            System.err.println("Greška brisanja: " + e.getMessage());
        }
    }

    public static void odarhiviraj(Path izvor, Path odrediste) {
        if (!Files.exists(izvor)) {
            System.err.println("ZIP datoteka ne postoji");
            return;
        }

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(izvor.toFile()))) {
            ZipEntry unos;
            while ((unos = zis.getNextEntry()) != null) {
                Path cilj = odrediste.resolve(unos.getName());
                Files.copy(zis, cilj, StandardCopyOption.REPLACE_EXISTING);
                zis.closeEntry(); // Obavezno !
                System.out.println("Datoteka raspakirana: " + cilj.toAbsolutePath());
            }

        } catch (IOException e) {
            System.err.println("Greška odarhiviranja: " + e.getMessage());
        }
    }

    public static void prikaziPostavke(Path putanja) {
        Properties postavke = new Properties();

        try (InputStream is = Files.newInputStream(putanja)) {
            postavke.load(is);
            System.out.println("Učitano postavki: " + postavke.size());
            postavke.forEach((kljuc, vrijednost)
                    -> System.out.println("- " + kljuc + " = " + vrijednost));
        } catch (IOException e) {
            System.err.println("Greška čitanja postavki: " + e.getMessage());
        }
    }
    
    public static void dohvatiPostavke(Path putanja) {
        Properties postavke = new Properties();

        try (InputStream is = Files.newInputStream(putanja)) {
            postavke.load(is);
            
            String naziv = postavke.getProperty("apl.naziv");
            String verzija = postavke.getProperty("apl.verzija");
            String debug = postavke.getProperty("apl.debug", "false"); // Primjer postavke koja ne postoji i upotrebe 'backup' vrijednosti
            int maksBrojUcenika = Integer.parseInt(postavke.getProperty("apl.maksBrojUcenika", "100"));
            
            System.out.println("Naziv aplikacije: " + naziv);
            System.out.println("Verzija: " + verzija);
            System.out.println("Debug mod: " + debug);
            System.out.println("Maksimalan broj učenika: " + maksBrojUcenika);
        } catch (IOException e) {
            System.err.println("Greška dohvaćanja postavki: " + e.getMessage());
        }
    }

}
