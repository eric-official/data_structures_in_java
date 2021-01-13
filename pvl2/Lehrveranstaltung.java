//Abgabe Eric Näser (Matrikel: 524319)
package PVL2_Group14;

public class Lehrveranstaltung {
    private String name;
    private Integer NumberOfTries;
    private Boolean bestanden;
    private Lehrveranstaltung next;

    public Lehrveranstaltung(String name, Boolean bestanden){
        this.name=name;
        this.NumberOfTries=1;
        this.bestanden=bestanden;
        this.next=null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getBestanden() {
        return bestanden;
    }

    public void setBestanden(Boolean bestanden) {
        this.bestanden = bestanden;
    }

    public Lehrveranstaltung getNext() {
        return next;
    }

    public void setNext(Lehrveranstaltung next) {
        this.next = next;
    }

    public Integer getNumberOfTries() {
        return NumberOfTries;
    }

    public void setNumberOfTries(Integer numberOfTries) {
        NumberOfTries = numberOfTries;
    }
}
