// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package pvl8_neu;


public interface Graph_II {
    Boolean setEdge(int source, int destin);
    List<List<Integer>> getEdges();
    Boolean hasWay(int source, int destin);
    Boolean isConnected();
    Boolean isConnected(List<Integer> nodes);
    List<List<Integer>> getBridges();
    List<Integer> getArticulations();
}
