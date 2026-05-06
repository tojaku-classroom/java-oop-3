package hr.obskc.baze.bazepodataka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KolegijRepository {

    public static void stvoriTablice() throws SQLException {
        String sqlKolegiji = "CREATE TABLE IF NOT EXISTS kolegiji ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "naziv TEXT NOT NULL,"
                + "slobodna_mjesta INTEGER NOT NULL"
                + ");";

        String sqlUpisi = "CREATE TABLE IF NOT EXISTS upisi ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "student_id INTEGER NOT NULL,"
                + "kolegij_id INTEGER NOT NULL,"
                + "FOREIGN KEY (student_id) REFERENCES studenti(id),"
                + "FOREIGN KEY (kolegij_id) REFERENCES kolegiji(id)"
                + ");";

        try (Statement stmt = BazaPodatakaSingleton.getVeza().createStatement()) {
            stmt.execute(sqlKolegiji);
            stmt.execute(sqlUpisi);
            System.out.println("Tablice su spremne");
        }
    }

    public static void spremi(String naziv, int slobodnaMjesta) throws SQLException {
        String sql = "INSERT INTO kolegiji (naziv, slobodna_mjesta) VALUES (?, ?)";

        try (PreparedStatement stmt = BazaPodatakaSingleton.getVeza().prepareStatement(sql)) {
            stmt.setString(1, naziv);
            stmt.setInt(2, slobodnaMjesta);
            stmt.executeUpdate();
            System.out.println("Kolegij je spremljen u bazu podataka");
        }
    }

    public static List<Kolegij> dohvatiSve() throws SQLException {
        List<Kolegij> kolegiji = new ArrayList<>();
        String sql = "SELECT id, naziv, slobodna_mjesta FROM kolegiji";

        try (Statement stmt = BazaPodatakaSingleton.getVeza().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Kolegij kolegij = new Kolegij(
                        rs.getInt("id"),
                        rs.getString("naziv"),
                        rs.getInt("slobodna_mjesta")
                );
                kolegiji.add(kolegij);
            }
        }

        return kolegiji;
    }

    // Prikaz transakcija u bazi podataka
    // Trasakcija = niz odvojenih koraka promjena u bazi podataka koji se moraju izvršiti
    // po principu "sve ili ništa"
    public static void upisNaKolegij(int studentId, int kolegijId) throws SQLException {
        Connection veza = BazaPodatakaSingleton.getVeza();

        // Provjera je li student već upisan na kolegij
        String provjeriUpis = "SELECT COUNT(*) FROM upisi WHERE student_id = ? AND kolegij_id = ?";
        try (PreparedStatement stmt = veza.prepareStatement(provjeriUpis)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, kolegijId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new SQLException("Student je već upisan na kolegij");
            }
        }

        veza.setAutoCommit(false); // Način rada za transakcije

        try {
            // Operacija 1 - smanjivanje slobodnih mjesta (samo ako ih ima)
            String smanjiBroj = "UPDATE kolegiji SET slobodna_mjesta = slobodna_mjesta - 1 "
                    + "WHERE id = ? AND slobodna_mjesta > 0";
            try (PreparedStatement stmt = veza.prepareStatement(smanjiBroj)) {
                stmt.setInt(1, kolegijId);
                int promjena = stmt.executeUpdate();
                if (promjena == 0) {
                    throw new SQLException("Nema slobodnih mjesta na kolegiju " + kolegijId);
                }
                System.out.println("Korak 1: slobodna mjesta smanjena za 1");
            }

            // Operacija 2 - upis studenta na kolegij
            String dodajUpis = "INSERT INTO upisi (student_id, kolegij_id) VALUES (?, ?)";
            try (PreparedStatement stmt = veza.prepareStatement(dodajUpis)) {
                stmt.setInt(1, studentId);
                stmt.setInt(2, kolegijId);
                stmt.executeUpdate();
                System.out.println("Korak 2: Student " + studentId + " je upisan na kolegij " + kolegijId);
            }

            veza.commit(); // Transakcija je prošla bez problema, možemo je potvrditi
            System.out.println("Transakcija je uspjela");
        } catch (SQLException e) {
            veza.rollback(); // Vraćanje baze podataka u originalno stanje
            System.err.println("Greška prilikom izvršenja transakcije; baza podataka vraćena u originalno stanje: " + e.getMessage());
            throw e;
        } finally {
            veza.setAutoCommit(true); // Isključujemo transkcijski način rada
        }

    }

}
