// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package pvl6_group14;

public class ProcessImplementation implements Process {
    private int PID;
    private int arrivalTime;
    private int executionTime;
    private int priority;
    ProcessImplementation next;

    public void setPID(int PID) {
        this.PID = PID;
    }

    @Override
    public int getPID() {
        return this.PID;
    }

    @Override
    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ProcessImplementation(int PID, int arrivalTime, int executionTime, int priority){
        this.PID = PID;
        this.arrivalTime=arrivalTime;
        this.executionTime=executionTime;
        this.priority=priority;
        this.next=null;
    }

    public ProcessImplementation getNext() {
        return next;
    }

    public void setNext(ProcessImplementation next) {
        this.next = next;
    }
}
