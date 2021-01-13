// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package PVL9_group14;

import java.util.List;

public interface Graph_III {
    Boolean setEdge(int source, int destin);
    List<List<Integer>> getEdges();
    Boolean hasCircle();
    List<Integer> topSort();
    List<List<Integer>> getStronglyConnectedComponents();
}
