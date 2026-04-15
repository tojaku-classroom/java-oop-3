package hr.obskc.baze.bazepodataka;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    
    public static void stvoriTablicu() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS studenti ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "ime TEXT NOT NULL,"
                + "prezime TEXT NOT NULL,"
                + "godina_studija INTEGER"
                + ");";
        
        try (Statement stmt = BazaPodatakaSingleton.getVeza().createStatement()) {
            stmt.execute(sql);
            System.out.println("Tablica je spremna");
        }
    }
    
    public static void spremi(String ime, String prezime, int godinaStudija) throws SQLException {
        String sql = "INSERT INTO studenti (ime, prezime, godina_studija) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = BazaPodatakaSingleton.getVeza().prepareStatement(sql)) {
            stmt.setString(1, ime);
            stmt.setString(2, prezime);
            stmt.setInt(3, godinaStudija);
            stmt.executeUpdate();
            System.out.println("Student je spremljen u bazu podataka");
        }
    }
    
    public static void promijeni(int id, String ime, String prezime, int godinaStudija) throws SQLException {
        String sql = "UPDATE studenti SET ime = ?, prezime = ?, godina_studija = ? WHERE id = ?";
        
        try (PreparedStatement stmt = BazaPodatakaSingleton.getVeza().prepareStatement(sql)) {
            stmt.setString(1, ime);
            stmt.setString(2, prezime);
            stmt.setInt(3, godinaStudija);
            stmt.setInt(4, id);
            int brojPromjena = stmt.executeUpdate();
            
            if (brojPromjena > 0) {
                System.out.println("Podaci studenta su uspješno promijenjeni");
            } else {
                System.out.println("Student nije pronađen");
            }
        }
    }
    
    public static void brisi(int id) throws SQLException {
        String sql = "DELETE FROM studenti WHERE id = ?";
        
        try (PreparedStatement stmt = BazaPodatakaSingleton.getVeza().prepareStatement(sql)) {
            stmt.setInt(1, id);
            int brojPromjena = stmt.executeUpdate();
            
            if (brojPromjena > 0) {
                System.out.println("Student je obrisan");
            } else {
                System.out.println("Student nije pronađen");
            }
        }
    }
    
    public static List<Student> dohvatiSve() throws SQLException {
        List<Student> studenti = new ArrayList<>();
        String sql = "SELECT id, ime, prezime, godina_studija FROM studenti";
        
        try (Statement stmt = BazaPodatakaSingleton.getVeza().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getInt("godina_studija")
                );
                studenti.add(student);
            }
        }
        
        return studenti;
    }
}
