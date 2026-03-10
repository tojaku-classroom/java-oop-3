package hr.obskc.datoteke.zadaci;

import java.io.Serializable;

public class Zadatak implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String opis;
    private boolean gotovo;
    private int prioritet; // 1 = nizak, 2 = srednji, 3 = visok

    public Zadatak(int id, String opis, boolean gotovo, int prioritet) {
        this.id = id;
        this.opis = opis;
        this.gotovo = gotovo;
        this.prioritet = prioritet;
    }

    public int getId() {
        return id;
    }

    public String getOpis() {
        return opis;
    }

    public int getPrioritet() {
        return prioritet;
    }

    public boolean isGotovo() {
        return gotovo;
    }

    public void setGotovo(boolean gotovo) {
        this.gotovo = gotovo;
    }

    @Override
    public String toString() {
        String status = gotovo ? "[x]" : "[ ]";
        String prio = switch (prioritet) {
            case 3 ->
                "!!!";
            case 2 ->
                "!! ";
            default ->
                "!  ";
        };
        return String.format("%s %s [%d] %s", status, prio, id, opis);
    }

}
