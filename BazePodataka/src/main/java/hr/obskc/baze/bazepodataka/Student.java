package hr.obskc.baze.bazepodataka;

public class Student {

    private int id;
    private String ime;
    private String prezime;
    private int godinaStudija;

    public Student(int id, String ime, String prezime, int godinaStudija) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.godinaStudija = godinaStudija;
    }

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public int getGodinaStudija() {
        return godinaStudija;
    }

    @Override
    public String toString() {
        return String.format("Student [ID=%d, Ime=%s, Prezime=%s, Godina Studija=%d]", id, ime, prezime, godinaStudija);
    }
}
