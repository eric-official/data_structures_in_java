package pvl11_group14;
import java.util.List;

public interface Graph_V {
    Boolean setEdge(int source, int destin, int weight);
    List<List<Edge>> getEdges();
    List<List<Edge>> getAllPaths(int source, int destin);
    Boolean hasEulerianCircle();
    List<List<Integer>> allPairShortestPath();
}