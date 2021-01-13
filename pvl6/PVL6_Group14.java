// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package pvl6_group14;

public class PVL6_Group14 implements SchedulingTask {
    ProcessImplementation front;
    private int kernelNumber;

    public PVL6_Group14(int kernelNumber) {
        this.kernelNumber = kernelNumber;
        this.front = null;
    }

    public ProcessImplementation getFront() {
        return front;
    }

    public void setFront(ProcessImplementation front) {
        this.front = front;
    }

    public int getKernelNumber() {
        return kernelNumber;
    }

    public void setKernelNumber(int kernelNumber) {
        this.kernelNumber = kernelNumber;
    }

    @Override
    public int createProcess(int arrivalTime, int executionTime, int priority) {
        Integer setPID = 1;
        ProcessImplementation current = this.front;
        while (setPID <= 256) {
            current = this.front;
            while (current != null && current.getPID() != setPID) {
                current = current.getNext();
            }
            if (current == null) {
                ProcessImplementation n = new ProcessImplementation(setPID, arrivalTime, executionTime, priority);
                this.setFront(sortedInsert(this.front, n));
                return setPID;
            }
            setPID++;
        }
        return -1;
    }

    public void remove(int dataValue) {
        ProcessImplementation it=this.front;
        if(it.getPID()==dataValue){
            this.setFront(front.getNext());
            return;
        }
        while(it.getNext().getPID()!=dataValue){
            it=it.getNext();
        }
        it.setNext(it.getNext().getNext());
    }

    @Override
    public boolean deleteProcess(int pid) {
        ProcessImplementation it=this.front;
        while(it!=null){
            if(it.getPID()==pid){
                remove(pid);
                return true;
            }
            it=it.getNext();
        }
        return false;
    }

    @Override
    public String execute() {

        PVL6_Group14 schedule_list=new PVL6_Group14(this.kernelNumber);
        ProcessImplementation it=this.front;

        while(it!=null){
            ProcessImplementation n=new ProcessImplementation(it.getPID(), it.getArrivalTime(), it.getExecutionTime(), it.getPriority());
            schedule_list.setFront(schedule_list.sortedInsertSchedule(schedule_list.front, n));
            it=it.getNext();
        }

        int periode=0;
        it=schedule_list.front;
        String res="";
        KernelClass[] kernelArray= new KernelClass[this.kernelNumber];
        for(int i=0;i<this.kernelNumber;i++){
            kernelArray[i]=new KernelClass();
        }

        while(schedule_list.front!=null){

            for(int i=0;i<this.kernelNumber;i++){ //Schleife zum Kernel befüllen
                if(kernelArray[i].getPid()==0 && it!=null && it.getArrivalTime()<=periode){
                    kernelArray[i].setPid(it.getPID());
                    kernelArray[i].setExecutionTime(it.getExecutionTime());
                    it=it.getNext();
                }
            }

            res+=periode + ": ";
            for(int i=0;i<this.kernelNumber;i++){ //Schleife für res
                res+=kernelArray[i].getPid() + " ";
            }

            for(int i=0;i<this.kernelNumber;i++){ //Schleife zum löschen
                kernelArray[i].setExecutionTime(kernelArray[i].getExecutionTime()-1);
                if(kernelArray[i].getExecutionTime()==0){
                    schedule_list.deleteProcess(kernelArray[i].getPid());
                    kernelArray[i].setPid(0);
                }
            }
            res+="\n";
            periode++;
        }
        return res;
    }

    public ProcessImplementation sortedInsert(ProcessImplementation head, ProcessImplementation newNode) {
        ProcessImplementation current;

        if (head == null)
            head = newNode;

        else if (head.getPriority() >= newNode.getPriority()) {
            newNode.setNext(head);
            head = newNode;
        } else {
            current = head;

            while (current.getNext() != null && current.getNext().getPriority() < newNode.getPriority()) {
                current = current.getNext();
            }

            newNode.setNext(current.getNext());

            current.setNext(newNode);

        }
        return head;
    }

    public ProcessImplementation sortedInsertSchedule(ProcessImplementation head, ProcessImplementation newNode) {
        ProcessImplementation current;

        if (head == null)
            head = newNode;

        else if (head.getArrivalTime() > newNode.getArrivalTime() || (head.getArrivalTime() == newNode.getArrivalTime() && head.getPriority() < newNode.getPriority())) {
            newNode.setNext(head);
            head = newNode;

        } else {
            current = head;

            while (current.getNext() != null && current.getNext().getArrivalTime() <= newNode.getArrivalTime()) {
                if(current.getNext().getPriority() < newNode.getPriority() && current.getNext().getArrivalTime() == newNode.getArrivalTime()){
                    break;
                }
                current = current.getNext();
            }

            newNode.setNext(current.getNext());

            current.setNext(newNode);

        }
        return head;
    }

    public void printSchedule() {
        ProcessImplementation it = this.front;
        while (it != null) {
            it = it.getNext();
        }
    }

    public static void main(String[] args) {
        PVL6_Group14 scheduler=new PVL6_Group14(2);
        scheduler.createProcess(0,3,10);
        scheduler.createProcess(0,2,10);
        scheduler.createProcess(2,1,10);
        scheduler.createProcess(5,4,10);
        scheduler.createProcess(5,3,90);
        scheduler.createProcess(9,1,10);
        scheduler.deleteProcess(6);
        scheduler.createProcess(5,1,80);
        System.out.println(scheduler.execute());
    }
}