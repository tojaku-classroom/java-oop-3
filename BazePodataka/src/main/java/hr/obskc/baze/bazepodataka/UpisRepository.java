package hr.obskc.baze.bazepodataka;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UpisRepository {

    public static List<Upis> dohvatiStudenteNaKolegiju(int kolegijId) throws SQLException {
        List<Upis> upisi = new ArrayList<>();
        String sql = """
                     SELECT u.id, s.ime, s.prezime, k.naziv
                     FROM upisi u
                     JOIN studenti s ON u.student_id = s.id
                     JOIN kolegiji k ON u.kolegij_id = k.id
                     WHERE u.kolegij_id = ?
                     ORDER BY s.prezime, s.ime
                     """;

        try (PreparedStatement stmt = BazaPodatakaSingleton.getVeza().prepareStatement(sql)) {
            stmt.setInt(1, kolegijId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                upisi.add(new Upis(
                        rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("naziv")));
            }
        }

        return upisi;
    }

    public static List<Upis> dohvatiSveUpise() throws SQLException {
        List<Upis> upisi = new ArrayList<>();
        String sql = """
                     SELECT u.id, s.ime, s.prezime, k.naziv
                     FROM upisi u
                     JOIN studenti s ON u.student_id = s.id
                     JOIN kolegiji k ON u.kolegij_id = k.id
                     ORDER BY k.naziv, s.prezime, s.ime
                     """;

        try (Statement stmt = BazaPodatakaSingleton.getVeza().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                upisi.add(new Upis(
                        rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("naziv")));
            }
        }

        return upisi;
    }

    public static void ispisiStatistiku() throws SQLException {
        String sql = """
                     SELECT k.id, k.naziv, k.slobodna_mjesta,
                     (SELECT COUNT(*) FROM upisi u WHERE u.kolegij_id = k.id) AS upisani
                     FROM kolegiji k
                     ORDER BY k.naziv
                     """;

        System.out.println("Statistika kolegija [id | naziv | slobodna mjesta | broj upisanih]");

        try (Statement stmt = BazaPodatakaSingleton.getVeza().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(String.format("- %d | %s | %d | %d",
                        rs.getInt("id"),
                        rs.getString("naziv"),
                        rs.getInt("slobodna_mjesta"),
                        rs.getInt("upisani")));
            }
        }
    }

}
