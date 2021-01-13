// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)

package pvl10_group14;

public class Edge {
    public int to;
    public int weight;

    public Edge(int to, int weight){
        this.to = to;
        this.weight = weight;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String toString() {
        return "Edge [destination=" + to + ", weight=" + weight + "]";
    }
}
