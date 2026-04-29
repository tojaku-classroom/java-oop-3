package hr.obskc.baze.bazepodataka;

import java.sql.SQLException;
import java.util.List;

public class BazePodataka {

    public static void main(String[] args) {
        try {
            StudentRepository.stvoriTablicu();
            
            /*StudentRepository.spremi("Pero", "Perić", 1);
            StudentRepository.spremi("Ana", "Anić", 1);
            StudentRepository.spremi("Ivo", "Ivić", 4);
            StudentRepository.spremi("Jozo", "Jozić", 3);
            StudentRepository.spremi("Đuro", "Đurić", 2);*/
            
            System.out.println("Popis studenata u bazi podataka:");
            List<Student> studenti = StudentRepository.dohvatiSve();
            for(Student s : studenti) {
                System.out.println(s.toString());
            }
            
            StudentRepository.promijeni(4, "Joško", "Jozić", 3);
            StudentRepository.brisi(3);
            
            System.out.println("Popis studenata u bazi podataka:");
            studenti = StudentRepository.dohvatiSve();
            for(Student s : studenti) {
                System.out.println(s.toString());
            }
            
            // Demonstracija transakcija
            KolegijRepository.stvoriTablice();
            
            KolegijRepository.spremi("Matematika", 2);
            KolegijRepository.spremi("Fizika", 1);
            
            System.out.println("\nPopis kolegija:");
            for (Kolegij k : KolegijRepository.dohvatiSve()) {
                System.out.println(k.toString());
            }
            
            KolegijRepository.upisNaKolegij(1, 1); // Pero na Matematiku
            KolegijRepository.upisNaKolegij(2, 1); // Ana na Matematiku
            KolegijRepository.upisNaKolegij(5, 2); // Đuro na Fiziku - uspjeh!
            KolegijRepository.upisNaKolegij(4, 2); // Joško na Fiziku - nema mjesta!
            
            System.out.println("\nPopis kolegija:");
            for (Kolegij k : KolegijRepository.dohvatiSve()) {
                System.out.println(k.toString());
            }
        } catch (SQLException e) {
            System.err.println("Greška u radu s bazom podataka: " + e.getMessage());
            e.printStackTrace();
        } finally {
            BazaPodatakaSingleton.zatvoriVezu();
        }
    }
}
