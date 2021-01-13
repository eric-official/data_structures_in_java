// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package calculator_group_14;

public class doubleNode {
    private String data;
    private doubleNode next;
    private doubleNode previous;
    private char ZeichenTyp;

    public doubleNode(String datavalue, char typ){
        this.data=datavalue;
        this.next=null;
        this.previous=null;
        this.ZeichenTyp = typ;
    }

    public String toString(){
        return "[" + data + "]";
    }

    public char getZeichenTyp() {
        return ZeichenTyp;
    }

    public void setZeichenTyp(char zeichenTyp) {
        ZeichenTyp = zeichenTyp;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public doubleNode getNext() { return next; }

    public void setNext(doubleNode next) {
        this.next = next;
    }

    public doubleNode getPrevious() {
        return previous;
    }

    public void setPrevious(doubleNode previous) {
        this.previous = previous;
    }
}
