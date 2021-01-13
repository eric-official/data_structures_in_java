// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package PVL5_Group14;

public class genericNode<T> {
    private T data;
    private genericNode next;

    public genericNode(T datavalue){
        data=datavalue;
        next=null;
    }

    public String toString(){
        return "[" + data + "]";
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public genericNode getNext() {
        return next;
    }

    public void setNext(genericNode next) {
        this.next = next;
    }

    public boolean hasNext(){
        return this.getNext()!=null;
    }
}