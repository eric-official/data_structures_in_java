package pvl8_neu;

import java.util.Iterator;


public class List<T> implements Iterable<T> {
    private Node<T> front;

    public List() {
        setFront(null);
    }

    public boolean isEmpty() {
        return getFront() == null;
    }

    public void printContent() {
        for (Node<T> it = getFront(); it != null; it = it.getNext()) {
            System.out.println(it.toString());
        }
    }

    public void traverse() {
        traverse(front);
    }

    private void traverse(Node<T> n) {
        if (n == null)
            return;
        System.out.print(n.toString());
        traverse(n.getNext());
    }

    public void reversePrint() {
        reversePrint(front);
    }

    private void reversePrint(Node<T> n) {
        if (n == null)
            return;
        else {
            reversePrint(n.getNext());
            System.out.print(n.toString());
        }
    }

    public void addFront(T dataValue) {
        Node<T> n = new Node<T>(dataValue);
        n.setNext(getFront());
        setFront(n);
    }

    public void append(T data) {
        front =  append(front, data);
    }

    private Node append(Node head2, T data) {
        if(head2 == null) {
            head2 = new Node(data);
            return head2;
        } else {
            head2.setNext(append(head2.getNext(), data));
            return head2;
        }
    }

    private void add(Node<T> it, T dataValue) {
        if (it == null) {
            System.out.println("Cannot insert after null");
            return;
        }
        Node<T> n = new Node<T>(dataValue);
        n.setNext(it.getNext());
        it.setNext(n);
    }

    public void addAtEnd(T data) {
        front=addAtEnd(front, data);
    }

    private Node<T> addAtEnd(Node<T> node, T data) {
        if (node == null)
            return new Node<>(data);
        else {
            node.setNext(addAtEnd(node.getNext(), data));
            return node;
        }

    }

    public void delete(Node<T> del){
        Node<T> le = this.getFront();
        if(le.getData().equals(del.getData())){
            if(le.getNext()!=null){
                this.front=le.getNext();
            }else{
                this.front=null;
            }
        } else {
            while (le.getNext() != null && !le.getData().equals(del.getData())) {
                if (le.getNext().getData().equals(del.getData())) {
                    if (le.getNext().getNext() != null) {
                        le.setNext(le.getNext().getNext());
                    } else {
                        le.setNext(null);
                        break;
                    }
                }
                le = le.getNext();
            }
        }
    }

    public void remove(T dataValue) {
        Node<T> it=this.front;
        while(it!=null){
            if(it.getData().equals(dataValue)){
                delete(it);
            }
            it=it.getNext();
        }
    }

    public void reverse() {
        Node<T> previous = null;
        Node<T> current = front;
        Node<T> next = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        front = previous;
    }

    public T getFirst() {
        if(front != null) {
            Node<T> helper = front;
            front = front.getNext();

            return helper.getData();
        }
        else return null;
    }

    public T showFirst(){
        return front.getData();
    }

    public Node<T> getFront() {
        return front;
    }

    public void setFront(Node<T> front) {
        this.front = front;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = front;

            @Override
            public boolean hasNext() {
                if (node == null)
                    return false;
                else
                    return true;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T data = node.getData();
                    node = node.getNext();
                    return data;
                }
                return null;
            }

        };
    }

    public void printList(){
        Node<T> it=this.front;
        while(it!=null){
            System.out.print(it.toString());
            it=it.getNext();
        }
    }

    public static void main(String[] args){
        List<String> list=new List<String>();
        list.addFront("Eins");
        list.addAtEnd("Zwei");
        list.addFront("Polizei");
        list.printList();
        list.remove("Polizei");
        System.out.println("---");
        list.printList();
        list.remove("Eins");
        System.out.println("---");
        list.printList();
        list.remove("Zwei");
        System.out.println("---");
        list.printList();
    }
}