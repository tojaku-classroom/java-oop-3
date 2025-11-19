package hr.obskc.niop.projektoop.voznipark;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VozniPark {

    private List<Vozilo> vozila;

    public VozniPark() {
        this.vozila = new ArrayList<>();
    }

    public void dodajVozilo(Vozilo vozilo) {
        this.vozila.add(vozilo);
    }

    public void prikaziSvaVozila() {
        /*for (Vozilo vozilo : vozila) {
            System.out.println(vozilo.toString());
        }*/
        // vozila.forEach(vozilo -> System.out.println(vozilo));
        vozila.forEach(System.out::println);
    }

    public List<Vozilo> pretraziPoTipu(String tip) {
        /*ArrayList<Vozilo> pronadjena = new ArrayList<>();
        
        for (Vozilo vozilo : this.vozila) {
            if (vozilo.getTip().equalsIgnoreCase(tip)) {
                pronadjena.add(vozilo);
            }
        }
        return pronadjena;*/

        // filtriranje uz upotrebu tokova podataka
        return this.vozila.stream()
                .filter(vozilo -> vozilo.getTip().equalsIgnoreCase(tip))
                .collect(Collectors.toList());
    }

    public List<Vozilo> filtrirajPoStanju(Stanje stanje) {
        return this.vozila.stream()
                .filter(vozilo -> vozilo.getStanje() == stanje)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        VozniPark vozniPark = new VozniPark();

        vozniPark.dodajVozilo(new Automobil("Toyota", "Corolla", 2020, Stanje.NOVO));
        vozniPark.dodajVozilo(new Kamion("MAN", "TGX", 2018, Stanje.RABLJENO));
        vozniPark.dodajVozilo(new Motocikl("Yamaha", "R1", 2021, Stanje.NOVO));
        vozniPark.dodajVozilo(new Automobil("Ford", "Fiesta", 2015, Stanje.OSTECENO));

        System.out.println("Sva vozila u voznom parku:");
        vozniPark.prikaziSvaVozila();

        System.out.println("Pretraživanje po tipu");
        List<Vozilo> automobili = vozniPark.pretraziPoTipu("Automobil");
        automobili.forEach(System.out::println);
        
        System.out.println("Pretraživanje po stanju");
        List<Vozilo> automobili2 = vozniPark.filtrirajPoStanju(Stanje.NOVO);
        automobili2.forEach(System.out::println);
    }

}
