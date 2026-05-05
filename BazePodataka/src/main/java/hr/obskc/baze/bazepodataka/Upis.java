package hr.obskc.baze.bazepodataka;

public class Upis {

    private int id;
    private String imeStudenta;
    private String prezimeStudenta;
    private String nazivKolegija;

    public Upis(int id, String imeStudenta, String prezimeStudenta, String nazivKolegija) {
        this.id = id;
        this.imeStudenta = imeStudenta;
        this.prezimeStudenta = prezimeStudenta;
        this.nazivKolegija = nazivKolegija;
    }

    public int getId() {
        return id;
    }

    public String getImeStudenta() {
        return imeStudenta;
    }

    public String getPrezimeStudenta() {
        return prezimeStudenta;
    }

    public String getNazivKolegija() {
        return nazivKolegija;
    }

    @Override
    public String toString() {
        return String.format("Upis [ID=%d, Student=%s %s, Kolegij=%s]", id, imeStudenta, prezimeStudenta, nazivKolegija);
    }
}
