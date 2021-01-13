//Abgabe Eric Näser (Matrikel: 524319)
package PVL2_Group14;

public class ProcessImplementation {
    private ProcessImplementation prev;
    private Integer matrikel;
    private String firstName;
    private String lastName;
    private Lehrveranstaltung front;
    private ProcessImplementation next;

    public ProcessImplementation(String firstName, String lastName, Integer matrikel){
        this.prev=null;
        this.next=null;
        this.matrikel=matrikel;
        this.firstName=firstName;
        this.lastName=lastName;
        this.front=null;
    }

    public Integer getPriority() {
        return matrikel;
    }

    public void setMatrikel(Integer matrikel) {
        this.matrikel = matrikel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Lehrveranstaltung getFront() {
        return front;
    }

    public void setFront(Lehrveranstaltung front) {
        this.front = front;
    }

    public ProcessImplementation getPrev() {
        return prev;
    }

    public void setPrev(ProcessImplementation prev) {
        this.prev = prev;
    }

    public ProcessImplementation getNext() {
        return next;
    }

    public void setNext(ProcessImplementation next) {
        this.next = next;
    }

    public void LVaddFront(String name, Boolean bestanden) {
        Lehrveranstaltung n = new Lehrveranstaltung(name, bestanden);
        n.setNext(getFront());
        setFront(n);
    }
}
