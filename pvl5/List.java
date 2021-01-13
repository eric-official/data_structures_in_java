// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package PVL5_Group14;

public class List<T> {
    genericNode<Integer> front;

    public List(){
        this.front=null;
    }

    public void addFront(int element) {
        genericNode n = new genericNode(element);
        n.setNext(getFront());
        setFront(n);
    }

    public boolean ListContains(Integer element){
        genericNode<Integer> it=this.front;
        while(it!=null){
            if(it.getData()==element) return true;
            it=it.getNext();
        }
        return false;
    }

    public void remove(Integer dataValue) {
        if ((getFront() != null) && (getFront().getData() == dataValue)) {
            setFront(getFront().getNext());
        } else if (front == null) {
            if (getFront() != null) {
                genericNode<Integer> it = getFront(); // Here: it.data != dataValue
                while (it.getNext() != null) { // Run through the list
                    genericNode<Integer> n = it.getNext();
                    if (n.getData() == dataValue) {
                        it.setNext(n.getNext());
                        break;
                    } else {
                        it = it.getNext(); // Schleifenz�hler weitersetzen
                    }
                }
            }
        }
    }

    public void printList(){
        genericNode<Integer> it=this.getFront();
        while(it!=null){
            System.out.println(it.getData());
            it=it.getNext();
        }
    }

    public genericNode<Integer> getFront() {
        return front;
    }

    public void setFront(genericNode<Integer> front) {
        this.front = front;
    }

}
