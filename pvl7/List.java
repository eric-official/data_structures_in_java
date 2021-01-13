// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package pvl7_group14;

public class List<T> {
    private Node<T> front;
    int size;

    public List() {
        setFront(null);
        size=0;
    }

    public Node<T> getFront() {
        return front;
    }

    public void setFront(Node<T> front) {
        this.front = front;
    }

    public void printContent() {
        Node<T> it=this.front;
        while(it!=null){
            System.out.print(it.getData() + " ");
            it=it.getNext();
        }
        System.out.println(" ");
    }

    public boolean isInList(T data){
        Node<T> it=this.front;
        while(it!=null){
            if(it.getData().equals(data)){
                return true;
            }
            it=it.getNext();
        }
        return false;
    }

    public void append(T data){
        //if(!this.isInList(data)) {
            front = append(front, data);
            size++;
       // }
    }

    private Node<T> append(Node<T> head2, T data){
        if(head2==null){
            head2=new Node(data);
            return head2;
        } else {
            head2.setNext(append(head2.getNext(), data));
            return head2;
        }
    }

    List deepCopy(){
        List result=new List();
        deepCopy(result, front);
        return result;
    }

    private void deepCopy(List<T> result, Node<T> head2){
        if(head2==null) return;
        T data=head2.getData();
        result.append(data);
        deepCopy(result, head2.getNext());
    }

    public boolean contains(T data) {
        Node it = front;
        while(it != null) {
            if(it.getData() == data) {
                return true;
            }
            it = it.getNext();
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
