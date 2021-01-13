package pvl8_neu;

public class ArrayQueue {
    private int[] elements;
    private int front;
    private int last;
    private int size;

    public ArrayQueue(int capacity){
        elements=new int[capacity];
        size=0;
        front=0;
        last=0;
    }

    public boolean isEmpty(){
        if(size==0){
            return true;
        } else {
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void offer(int item){
        elements[last]=item;
        last=(last+1)%elements.length;
        size++;
    }

    public int peek(){
        if(size>0){
            return elements[front];
        } else {
            System.out.println("Queue ist leer");
            return -1;
        }
    }

    public int poll(){
        if(size>0){
            int res=elements[front];
            front=(front+1)%elements.length;
            size--;
            return res;
        } else {
            System.out.println("Queue ist leer");
            return -1;
        }
    }



    public int getSize() {
        return size;
    }

    public static void main(String[] args){
        ArrayQueue queue=new ArrayQueue(5);
        queue.offer(5);
        queue.offer(8);
        queue.offer(131);
        System.out.println(queue.getSize());
        System.out.println(queue.peek());
        queue.poll();
        System.out.println(queue.getSize());
        queue.offer(245);
        System.out.println(queue.getSize());
        System.out.println(queue.peek());
    }
}