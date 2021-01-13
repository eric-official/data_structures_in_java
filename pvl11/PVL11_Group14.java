package pvl11_group14;

import java.util.*;

public class PVL11_Group14 implements Graph_V {
    private Vector<List<Edge>> nodes;
    int size;
    private final static int WHITE = 0;
    private final static int GRAY = 1;
    private final static int BLACK = 2;

    public PVL11_Group14(int n) {
        nodes = new Vector<List<Edge>>();
        for (int i = 0; i < n; i++) {
            nodes.addElement(new LinkedList<Edge>());
        }
        this.size = n;
    }


    @Override
    public Boolean setEdge(int source, int destin, int weight) {
        List<Edge> adjList = nodes.elementAt(source);
        ListIterator<Edge> it = adjList.listIterator();

        while (it.hasNext()) {
            if (it.next().to == destin) {
                return false;
            }
        }

        adjList.add(new Edge(destin, weight));

        return true;
    }

    @Override
    public List<List<Edge>> getEdges() {
        List<List<Edge>> res = new LinkedList<List<Edge>>();
        for (int i = 0; i < nodes.size(); i++) {
            List<Edge> list = nodes.elementAt(i);
            List<Edge> temp = new LinkedList<Edge>();
            ListIterator<Edge> it = list.listIterator();
            while (it.hasNext()) {
                temp.add(it.next());
            }
            res.add(temp);
        }
        return res;
    }

    @Override
    public List<List<Edge>> getAllPaths(int source, int destin) {
        return null;
    }

    @Override
    public Boolean hasEulerianCircle() {
        return null;
    }

    @Override
    public List<List<Integer>> allPairShortestPath() {
        return null;
    }

    public Iterator getEdges(int node) {
        List<Edge> list = nodes.elementAt(node);
        return list.iterator();
    }

    public static void main(String[] args){

    }

}
