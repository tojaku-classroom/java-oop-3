package hr.obskc.baze.bazepodataka;

public class Kolegij {

    private int id;
    private String naziv;
    private int slobodnaMjesta;

    public Kolegij(int id, String naziv, int slobodnaMjesta) {
        this.id = id;
        this.naziv = naziv;
        this.slobodnaMjesta = slobodnaMjesta;
    }

    public int getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getSlobodnaMjesta() {
        return slobodnaMjesta;
    }

    @Override
    public String toString() {
        return String.format("Kolegij [ID=%d, Naziv=%s, SlobodnaMjesta=%d]", id, naziv, slobodnaMjesta);
    }

}
