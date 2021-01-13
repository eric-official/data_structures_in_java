//Abgabe Eric Näser (Matrikel: 524319)
package PVL1_Group14;

public class Uebergang {
    private Zustand prev;
    private int data;
    private Zustand next;

    public Uebergang(Zustand prev, int data, Zustand next){
        this.prev=prev;
        this.data=data;
        this.next=next;
    }

    public int get_data(){
        return data;
    }

    public int get_prev(){
        return prev.get_data();
    }

    public int get_next(){
        return next.get_data();
    }
}
