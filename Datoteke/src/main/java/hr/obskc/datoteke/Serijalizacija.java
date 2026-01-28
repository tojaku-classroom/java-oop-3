package hr.obskc.datoteke;

import java.io.*;

public class Serijalizacija {

    public static void main(String[] args) {
        Osoba pero = new Osoba("Pero Perić", 25);
        
        // Spremanje objekta na disk
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("osoba.ser"))) {
            oos.writeObject(pero);
            System.out.println("Pero je spremljen");
        } catch(IOException ex) {
            System.err.println("Dogodila se greška kod serijalizacije!");
        }
        
        // Čitanje objekta s diska
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("osoba.ser"))) {
            Osoba kopijaPere = (Osoba) ois.readObject();
            System.out.println(kopijaPere.toString());
        } catch(IOException | ClassNotFoundException ex) {
            System.err.println("Dogodila se greška kod deserijalizacije!");
        }
    }
    
}
