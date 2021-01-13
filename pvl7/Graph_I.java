// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package pvl7_group14;

public interface Graph_I {
    Boolean setEdge(int from, int to);
    List<List<Integer>> getEdges();
    List<List<Integer>> getNGons(int n);
    List<Integer> getLongestPath(int from, int to);
    Boolean hasFullCircle();
}
