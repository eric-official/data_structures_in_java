package pvl8_neu;

import java.util.Iterator;
import java.util.Vector;

public class Graph implements Graph_II{

    private Vector<List<Edge>> nodes;
    int size;

    public Graph(int size) {
        nodes = new Vector<List<Edge>>();
        for(int i=0;i<size;i++){
            nodes.addElement(new List<Edge>());
        }
        this.size=size;
    }

    @Override
    public Boolean setEdge(int from, int to) {
        List<Edge> adjList = nodes.elementAt(from);
        Node<Edge> it=adjList.getFront();

        while(it!=null){
            if(it.getData().getDestination()==to){
                return false;
            }
            it=it.getNext();
        }

        adjList.addAtEnd(new Edge(to, 1));

        adjList = nodes.elementAt(to);
        adjList.addAtEnd(new Edge(from, 1));

        return true;
    }

    @Override
    public List<List<Integer>> getEdges() {
        List<List<Integer>> res= new List<List<Integer>>();
        for(int i=0;i<nodes.size();i++) {
            List<Edge> list = nodes.elementAt(i);
            List<Integer> temp=new List<Integer>();
            Node<Edge> it=list.getFront();
            while(it!=null){
                temp.addAtEnd(it.getData().getDestination());
                it=it.getNext();
            }
            res.addAtEnd(temp);
        }
        System.out.println(nodes.size());
        return res;
    }

    @Override
    public Boolean hasWay(int source, int destin) {
        return null;
    }

    @Override
    public Boolean isConnected() {
        return null;
    }

    @Override
    public Boolean isConnected(List<Integer> nodes) {
        return null;
    }

    @Override
    public List<List<Integer>> getBridges() {
        return null;
    }

    @Override
    public List<Integer> getArticulations() {
        return null;
    }

    public static void main(String[] args){
        Graph graph = new Graph(6);
        graph.setEdge(0, 1);
        graph.setEdge(0, 1);
        graph.setEdge(0, 2);
        graph.setEdge(1, 2);
        graph.setEdge(2, 3);
        graph.setEdge(3, 4);
        graph.setEdge(3, 5);
        graph.setEdge(4, 5);

        List<List<Integer>> edges=graph.getEdges();
        Node<List<Integer>> it=edges.getFront();
        while(it!=null){
            it.getData().printList();
            it=it.getNext();
        }
    }
}
