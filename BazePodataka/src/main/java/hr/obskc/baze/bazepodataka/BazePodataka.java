package hr.obskc.baze.bazepodataka;

import java.sql.SQLException;
import java.util.List;

public class BazePodataka {

    public static void main(String[] args) {
        try {
            StudentRepository.stvoriTablicu();

            // StudentRepository.spremi("Pero", "Perić", 1);
            // StudentRepository.spremi("Ana", "Anić", 1);
            // StudentRepository.spremi("Ivo", "Ivić", 4);
            // StudentRepository.spremi("Jozo", "Jozić", 3);
            // StudentRepository.spremi("Đuro", "Đurić", 2);
            // StudentRepository.promijeni(4, "Joško", "Jozić", 3);
            // StudentRepository.brisi(3);
            System.out.println("Popis studenata u bazi podataka:");
            List<Student> studenti = StudentRepository.dohvatiSve();
            for (Student s : studenti) {
                System.out.println(s.toString());
            }

            KolegijRepository.stvoriTablice();

            // KolegijRepository.spremi("Matematika", 2);
            // KolegijRepository.spremi("Fizika", 1);
            // KolegijRepository.upisNaKolegij(1, 1); // Pero na Matematiku
            // KolegijRepository.upisNaKolegij(2, 1); // Ana na Matematiku
            // KolegijRepository.upisNaKolegij(5, 2); // Đuro na Fiziku
            // KolegijRepository.upisNaKolegij(4, 2); // Joško na Fiziku
            System.out.println("\nPopis kolegija:");
            for (Kolegij k : KolegijRepository.dohvatiSve()) {
                System.out.println(k.toString());
            }

            System.out.println("Demo izvještaja na temelju upisa");
            System.out.println("Studenti upisani na Matematiku:");
            for (Upis u : UpisRepository.dohvatiStudenteNaKolegiju(1)) {
                System.out.println("- " + u);
            }
            System.out.println("Studenti upisani na Fiziku:");
            for (Upis u : UpisRepository.dohvatiStudenteNaKolegiju(2)) {
                System.out.println("- " + u);
            }
            System.out.println("Svi upisani studenti:");
            for (Upis u : UpisRepository.dohvatiSveUpise()) {
                System.out.println("- " + u);
            }
            System.out.println("Statistika:");
            UpisRepository.ispisiStatistiku();
        } catch (SQLException e) {
            System.err.println("Greška u radu s bazom podataka: " + e.getMessage());
            e.printStackTrace();
        } finally {
            BazaPodatakaSingleton.zatvoriVezu();
        }
    }
}
