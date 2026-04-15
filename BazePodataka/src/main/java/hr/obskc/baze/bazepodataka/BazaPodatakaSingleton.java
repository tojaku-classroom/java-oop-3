package hr.obskc.baze.bazepodataka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton klasa za upravljanje vezom prema bazi podataka. Osigurava da
 * postoji samo jedna instanca **veze** prema bazi.
 */
public class BazaPodatakaSingleton {

    private static Connection veza; // 1. resurs koji singleton kontrolira -> **veza** -> private i static!

    private BazaPodatakaSingleton() {
    } // 2. privatan konstruktor -> samo izvorna klasa može stvoriti objekt, nitko drugi izvana

    // 3. funkcija koja daje **jedinu** **kontroliranu** vezu prema bazi podataka na korištenje svima koji je trebaju
    public static Connection getVeza() throws SQLException {
        if (veza == null || veza.isClosed()) {
            String url = "jdbc:sqlite:studenti.db"; // "ključ" za otvaranje veze prema bazi podataka
            veza = DriverManager.getConnection(url);
            System.out.println("Veza prema bazi otvorena");
        }
        return veza;
    }

    public static void zatvoriVezu() {
        if (veza != null) {
            try {
                if (!veza.isClosed()) {
                    veza.close();
                    System.out.println("Veza prema bazi podataka je zatvorena");
                }
            } catch (SQLException e) {
                System.err.println("Problem sa zatvaranjem veze prema bazi podataka");
            }
        }
    }

}
