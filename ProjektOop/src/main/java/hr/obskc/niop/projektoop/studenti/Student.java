package hr.obskc.niop.projektoop.studenti;

public class Student extends Osoba {
    
    private int godinaStudija;
    private double prosjekOcjena;
    
    public Student(String ime, String prezime, int godinaStudija, double prosjekOcjena) throws Exception {
        super(ime, prezime);
        try {
            this.setGodinaStudija(godinaStudija);
            this.setProsjekOcjena(prosjekOcjena);
        } catch (Exception e) {
            throw new Exception("Neispravna inicijalizacija!");
        }
    }

    public int getGodinaStudija() {
        return godinaStudija;
    }

    public double getProsjekOcjena() {
        return prosjekOcjena;
    }

    public final void setGodinaStudija(int godinaStudija) throws Exception {
        if (godinaStudija < 1 || godinaStudija > 6) {
            throw new Exception("Neispravna godina studija!");
        }
        this.godinaStudija = godinaStudija;
    }

    public final void setProsjekOcjena(double prosjekOcjena) throws Exception {
        if (prosjekOcjena < 1.0 || prosjekOcjena > 5.0) {
            throw new Exception("Neispravan prosjek ocjena!");
        }
        this.prosjekOcjena = prosjekOcjena;
    }
    
    public void ispisiPodatke() {
        System.out.println("Ime: " + this.ime);
        System.out.println("Prezime: " + this.prezime);
        System.out.println("Godina studija: " + this.godinaStudija);
        System.out.println("Prosjek ocjena: " + this.prosjekOcjena);
    }

}
