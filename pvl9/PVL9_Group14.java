// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)

package PVL9_group14;


import java.util.*;

public class PVL9_Group14 implements Graph_III {

    private Vector<List<Edge>> nodes;
    int size;
    private final static int WHITE = 0;
    private final static int GRAY = 1;
    private final static int BLACK = 2;

    public PVL9_Group14(int n){
        nodes = new Vector<List<Edge>>();
        for(int i=0;i<n;i++){
            nodes.addElement(new LinkedList<Edge>());
        }
        this.size=size;
    }

    @Override
    public Boolean setEdge(int source, int destin) {
        List<Edge> adjList = nodes.elementAt(source);
        ListIterator<Edge> it=adjList.listIterator();

        while(it.hasNext()){
            if(it.next().getDestination()==destin){
                return false;
            }
        }

        adjList.add(new Edge(destin, 1));

        return true;
    }

    @Override
    public List<List<Integer>> getEdges() {
        List<List<Integer>> res= new LinkedList<List<Integer>>();
        for(int i=0;i<nodes.size();i++) {
            List<Edge> list = nodes.elementAt(i);
            List<Integer> temp=new LinkedList<Integer>();
            ListIterator<Edge> it=list.listIterator();
            while(it.hasNext()){
                temp.add(it.next().getDestination());
            }
            res.add(temp);
        }
        return res;
    }

    public Iterator getEdges(int node) {
        List<Edge> list = nodes.elementAt(node);
        return list.iterator();
    }

    @Override
    public Boolean hasCircle() {
        int numSteps = 0;
        DFSVertex[] vertices = new DFSVertex[nodes.size()];
        boolean checker=false;
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new DFSVertex(WHITE, -1);
        }
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].getColor() == WHITE) {
                if(dfsVisitForHasCircle(i, numSteps, vertices)) return true;
            }
        }
        return false;
    }

    private boolean dfsVisitForHasCircle(int currentNode, int numSteps, DFSVertex[] vertices) {
        vertices[currentNode].setColor(GRAY);
        vertices[currentNode].setStartProcessing(++numSteps);
        Iterator iter = getEdges(currentNode);
        while (iter.hasNext()) {
            Edge edge = (Edge) iter.next();
            if (vertices[edge.getDestination()].getColor() == WHITE) {
                vertices[edge.getDestination()].setPredecessor(currentNode);
                dfsVisitForHasCircle(edge.getDestination(), numSteps, vertices);
            }
            if(vertices[edge.getDestination()].getColor() == GRAY){
                return true;
            }
        }
        vertices[currentNode].setColor(BLACK);
        vertices[currentNode].setFinishedProcessing(++numSteps);
        return false;
    }

    @Override
    public List<Integer> topSort() {
        int numSteps = 0;
        DFSVertex[] vertices = new DFSVertex[nodes.size()];
        List<Integer> res=new LinkedList<Integer>();

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new DFSVertex(WHITE, -1);
        }
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].getColor() == WHITE) {
                dfsVisitForTopSort(i, numSteps, vertices, res);
            }
        }
        return res;
    }

    private void dfsVisitForTopSort(int currentNode, int numSteps, DFSVertex[] vertices, List<Integer> res) {
        vertices[currentNode].setColor(GRAY);
        vertices[currentNode].setStartProcessing(++numSteps);
        Iterator iter = getEdges(currentNode);
        while (iter.hasNext()) {
            Edge edge = (Edge) iter.next();
            if (vertices[edge.getDestination()].getColor() == WHITE) {
                vertices[edge.getDestination()].setPredecessor(currentNode);
                dfsVisitForTopSort(edge.getDestination(), numSteps, vertices, res);
            }
        }
        res.add(0, currentNode);
        vertices[currentNode].setColor(BLACK);
        vertices[currentNode].setFinishedProcessing(++numSteps);
    }

    @Override
    public List<List<Integer>> getStronglyConnectedComponents() {
        int numSteps = 0;
        DFSVertex[] vertices = new DFSVertex[nodes.size()];
        List<List<Integer>> res=new LinkedList<List<Integer>>();

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new DFSVertex(WHITE, -1);
        }
        for (int i = 0; i < vertices.length; i++) {
            List<Integer> temp= new LinkedList<Integer>();
            if (vertices[i].getColor() == WHITE) {
                dfsVisitForStronglyCC(i, numSteps, vertices, temp, res);
            }
           /* if(temp!=null) {
                res.add(temp);
            }*/
        }
        return res;
    }

    private void dfsVisitForStronglyCC(int currentNode, int numSteps, DFSVertex[] vertices, List<Integer> temp, List<List<Integer>> res) {
        vertices[currentNode].setColor(GRAY);
        vertices[currentNode].setStartProcessing(++numSteps);
        Iterator iter = getEdges(currentNode);
        while (iter.hasNext()) {
            Edge edge = (Edge) iter.next();
            if (vertices[edge.getDestination()].getColor() == WHITE) {
                vertices[edge.getDestination()].setPredecessor(currentNode);
                if(!temp.contains(currentNode)) {
                    temp.add(currentNode);
                }
                dfsVisitForStronglyCC(edge.getDestination(), numSteps, vertices, temp, res);
            }
            if(vertices[edge.getDestination()].getColor() == GRAY){
                temp.add(currentNode);
                res.add(temp);
            }
        }
        temp=null;
        vertices[currentNode].setColor(BLACK);
        vertices[currentNode].setFinishedProcessing(++numSteps);
    }

    public static void main(String[] args){
        PVL9_Group14 graph_1 = new PVL9_Group14(7);
        graph_1.setEdge(0,1);
        graph_1.setEdge(1,2);
        graph_1.setEdge(2,3);
        graph_1.setEdge(3,4);
        graph_1.setEdge(0,5);
        graph_1.setEdge(1,5);
        graph_1.setEdge(5,6);
        graph_1.setEdge(6,3);

        List<List<Integer>> edges=graph_1.getEdges();
        ListIterator<List<Integer>> it=edges.listIterator();
        while(it.hasNext()){
            ListIterator<Integer> it2=it.next().listIterator();
            while(it2.hasNext()){
                System.out.println(it2.next().toString());
            }
        }
        System.out.println(" ");
        System.out.println(graph_1.hasCircle());
        List<Integer> topsort=graph_1.topSort();
        ListIterator<Integer> top_it=topsort.listIterator();
        while(top_it.hasNext()){
            System.out.println("elem: " + top_it.next());
        }

        List<List<Integer>> comp=graph_1.getStronglyConnectedComponents();
        ListIterator<List<Integer>> comp_it=comp.listIterator();
        while(comp_it.hasNext()){
            ListIterator<Integer> comp_it2=comp_it.next().listIterator();
            while(comp_it2.hasNext()){
                System.out.print(comp_it2.next().toString());
            }
            System.out.println(" ");
        }

        PVL9_Group14 graph_2 = new PVL9_Group14(4);
        graph_2.setEdge(0,1);
        graph_2.setEdge(1,2);
        graph_2.setEdge(1,3);
        graph_2.setEdge(2,0);
        graph_2.setEdge(2,3);
        graph_2.hasCircle();
        System.out.println(graph_2.hasCircle());

        /*List<List<Integer>> comp=graph_2.getStronglyConnectedComponents();
        ListIterator<List<Integer>> comp_it=comp.listIterator();
        while(comp_it.hasNext()){
            ListIterator<Integer> comp_it2=comp_it.next().listIterator();
            while(comp_it2.hasNext()){
                System.out.print(comp_it2.next().toString());
            }
            System.out.println(" ");
        }*/
    }
}
