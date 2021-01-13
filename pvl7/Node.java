// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package pvl7_group14;

public class Node<T> {
    private T data;
    private Node next;

    public Node(T datavalue){
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

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public boolean hasNext(){
        return this.getNext()!=null;
    }
}
