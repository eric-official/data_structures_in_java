// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package pvl6_group14;

public interface SchedulingTask {
    int createProcess(int arrivalTime, int executionTime, int priority);
    boolean deleteProcess(int pid);
    String execute();
}
