// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)

package pvl10_group14;
import java.util.*;

public class PVL10_Group14 implements Graph_IV {

    private Vector<List<Edge>> nodes;
    int size;
    private final static int WHITE = 0;
    private final static int GRAY = 1;
    private final static int BLACK = 2;

    public PVL10_Group14(int n) {
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
            if (it.next().getTo() == destin) {
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

    public Iterator getEdges(int node) {
        List<Edge> list = nodes.elementAt(node);
        return list.iterator();
    }

    private Boolean dfsVisitForHasNegCircle(int currentNode, int numSteps, DFSVertex[] vertices, int res_weight) {
        vertices[currentNode].setColor(GRAY);
        vertices[currentNode].setStartProcessing(++numSteps);
        Iterator iter = getEdges(currentNode);
        while (iter.hasNext()) {
            Edge edge = (Edge) iter.next();
            res_weight += edge.getWeight();
            if (vertices[edge.getTo()].getColor() == WHITE) {
                vertices[edge.getTo()].setPredecessor(currentNode);
                return dfsVisitForHasNegCircle(edge.getTo(), numSteps, vertices, res_weight);
            }
            if (vertices[edge.getTo()].getColor() == GRAY) {

                if (res_weight < 0) {
                    return true;
                }
            }
        }
        vertices[currentNode].setColor(BLACK);
        vertices[currentNode].setFinishedProcessing(++numSteps);
        return false;
    }

    @Override
    public Boolean hasNegativeCycle() {
        int numSteps = 0;
        DFSVertex[] vertices = new DFSVertex[nodes.size()];
        boolean checker = false;
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new DFSVertex(WHITE, -1);
        }
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].getColor() == WHITE) {
                if (dfsVisitForHasNegCircle(i, numSteps, vertices, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void dfsVisitForShortestPath(int currentNode, int numSteps, DFSVertex[] vertices, List<Edge> temp, List<List<Edge>> res, int destin) {
        if (temp.size() >= this.size) {
            return;
        }

        vertices[currentNode].setColor(GRAY);
        vertices[currentNode].setStartProcessing(++numSteps);
        Iterator iter = getEdges(currentNode);
        while (iter.hasNext()) {
            Edge edge = (Edge) iter.next();
            if (edge.getTo() != destin)
            //vertices[edge.getTo()].getColor() == WHITE)
            {
                vertices[edge.getTo()].setPredecessor(currentNode);
                temp.add(edge);
                List<Edge> temp2 = new LinkedList<Edge>();
                Iterator it = temp.listIterator();
                while (it.hasNext()) {
                    temp2.add((Edge) it.next());
                }
                dfsVisitForShortestPath(edge.getTo(), numSteps, vertices, temp2, res, destin);
            }
            if (edge.getTo() == destin) {
                temp.add(edge);
                res.add(temp);
                return;
            }
        }
        //vertices[currentNode].setColor(BLACK);
        vertices[currentNode].setFinishedProcessing(++numSteps);
    }

    @Override
    public List<Edge> shortestPath(int source, int destin) {

        if (this.hasNegativeCycle()) {
            return null;
        }

        int numSteps = 0;
        DFSVertex[] vertices = new DFSVertex[nodes.size()];
        List<List<Edge>> helper = new LinkedList<List<Edge>>();
        boolean checker = false;
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new DFSVertex(WHITE, -1);
        }
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].getColor() == WHITE) {
                List<Edge> temp = new LinkedList<Edge>();
                //dfsVisitForShortestPath(i, numSteps, vertices, temp, helper, destin);
            }
        }

        Iterator ite = getEdges(source);
        while (ite.hasNext()) {
            Edge curr = (Edge) ite.next();
            List<Edge> temp = new LinkedList<Edge>();
            dfsVisitForShortestPath(curr.getTo(), numSteps, vertices, temp, helper, destin);
        }


        Iterator it = helper.listIterator();
        boolean somein = false;
        int length = -1;
        List<Edge> res = new LinkedList<Edge>();
        while (it.hasNext()) {
            List<Edge> current_list = new LinkedList<Edge>();
            current_list = (LinkedList<Edge>) it.next();
            Iterator it2 = current_list.listIterator();
            int i = 0;
            while (it2.hasNext()) {
                Edge current = (Edge) it2.next();
                i += current.getWeight();
            }
            if (somein == false) {
                res = current_list;
                length = i;
                somein = true;
            }
            if (somein && length > i) {
                res = current_list;
                length = i;
            }
        }
        return res;
    }

    @Override
    public Integer universalSink() {

        Iterator it = nodes.listIterator();
        while (it.hasNext()) {
            List<Edge> list = new LinkedList<>();
            list = (List<Edge>) it.next();
            Iterator it2 = list.listIterator();
            while (it2.hasNext()) {
                Edge lel = (Edge) it2.next();
                if (!this.getEdges(lel.getTo()).hasNext()) {
                    return lel.getTo();
                }
            }
        }
        return -1;
    }

    private void findthemostdis(int i, List<List<Edge>> allposs, List<List<Edge>> themost) {
        if(i >= allposs.size()) {
            return;
        }
        List<Edge> first = allposs.get(i);
        List<List<Edge>> maybe= new LinkedList<List<Edge>>();


        maybe.add(first);
        for(int j = i+1;j<allposs.size();j++) {
            List<Edge> sec = allposs.get(j);
            if(isdis(maybe,sec)) {
                maybe.add(sec);
            }
        }

        if(maybe.size() >= themost.size()) {
            themost.clear();
            themost.addAll(maybe);
        }

        findthemostdis(i+1, allposs, themost);
    }


    private boolean isdis(List<List<Edge>> maybe, List<Edge> sec) {
        Iterator showall = maybe.iterator();
        while(showall.hasNext()) {
            List<Edge> neuelist= (List<Edge>) showall.next();
            Iterator showlist = neuelist.listIterator();
            while(showlist.hasNext()) {
                Edge neueedge = (Edge) showlist.next();
                if(sec.contains(neueedge)) {
                    return false;
                }
            }
        }
        return true;
    }


    private void findallposs(List<List<Edge>> allposs, int source, int destin, List<Edge> temp) {
        if(source == destin) {
            allposs.add(temp);
        }
        Iterator it = getEdges(source);
        while(it.hasNext()) {
            List<Edge> temp2 = new LinkedList<Edge>();
            temp2.addAll(temp);
            Edge neu = (Edge) it.next();
            if(temp2.contains(neu)) {
                continue;
            }
            temp2.add(neu);
            findallposs(allposs,neu.getTo(), destin, temp2);
        }
    }


    @Override
    public List<List<Edge>> maxEdgeDisjointPaths(int source, int destin) {
        List<List<Edge>> allposs = new LinkedList<List<Edge>>();
        List<Edge> temp = new LinkedList<Edge>();
        findallposs(allposs, source, destin, temp);



        List<List<Edge>> themost = new LinkedList<List<Edge>>();
        findthemostdis(0, allposs, themost);
        return themost;
    }

    private void dfsVisitForDisPaths(int currentNode, int numSteps, DFSVertex[] vertices, int destin, List<List<Edge>> res, List<Edge> temp, List<Edge> checker) {
        vertices[currentNode].setColor(GRAY);
        vertices[currentNode].setStartProcessing(++numSteps);
        Iterator iter = getEdges(currentNode);
        while (iter.hasNext()) {
            Edge edge = (Edge) iter.next();
            List<Edge> temp2=new LinkedList<Edge>();
            Iterator it=temp.listIterator();
            while(it.hasNext()){
                temp2.add((Edge)it.next());
            }
            temp2.add(edge);
            if (vertices[edge.getTo()].getColor() == WHITE) {
                vertices[edge.getTo()].setPredecessor(currentNode);
                dfsVisitForDisPaths(edge.getTo(), numSteps, vertices, destin, res, temp2, checker);
            }
            if(edge.getTo()==destin) {
                System.out.println(edge.toString());
                res.add(temp2);
            }
            if(vertices[edge.getTo()].getColor() == BLACK){
                System.out.println("GRAY" + edge.toString());
                return;
            }
        }
        vertices[currentNode].setColor(BLACK);
        vertices[currentNode].setFinishedProcessing(++numSteps);
        return;
    }
}

