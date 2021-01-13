// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)

package pvl10_group14;

public class DFSVertex {
    private int color;
    private int predecessor;

    public DFSVertex(int color, int predecessor) {
        super();
        this.color = color;
        this.predecessor = predecessor;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(int predecessor) {
        this.predecessor = predecessor;
    }

    public int getStartProcessing() {
        return startProcessing;
    }

    public void setStartProcessing(int startProcessing) {
        this.startProcessing = startProcessing;
    }

    public int getFinishedProcessing() {
        return finishedProcessing;
    }

    public void setFinishedProcessing(int finishedProcessing) {
        this.finishedProcessing = finishedProcessing;
    }

    private int startProcessing;
    private int finishedProcessing;
}
