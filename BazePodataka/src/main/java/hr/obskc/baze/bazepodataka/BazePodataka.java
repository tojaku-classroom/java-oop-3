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
            
        } catch (SQLException e) {
            System.err.println("Greška u radu s bazom podataka: " + e.getMessage());
            e.printStackTrace();
        } finally {
            BazaPodatakaSingleton.zatvoriVezu();
        }
    }
}
