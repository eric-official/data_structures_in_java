// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)

package pvl10_group14;

import java.util.Iterator;
import java.util.List;

public interface Graph_IV {
    Boolean setEdge(int source, int destin, int weight);
    List<List<Edge>> getEdges();
    Boolean hasNegativeCycle();
    List<Edge> shortestPath(int source, int destin);
    Integer universalSink();
    List<List<Edge>> maxEdgeDisjointPaths(int source, int destin);
}

class Test{
    public static void main(String[] args){
        Graph_IV graph_1 = new PVL10_Group14(4);
        graph_1.setEdge(0,1,1);
        graph_1.setEdge(1,2,2);
        graph_1.setEdge(2,0,-4);
        graph_1.setEdge(2,3,6);

        System.out.println(graph_1.hasNegativeCycle());                      // True
        List<Edge> test1=graph_1.shortestPath(0,3);
        System.out.println(graph_1.universalSink());                         // 3
        graph_1.maxEdgeDisjointPaths(0,3);  // {{Edge(1,1), Edge(2,2), Edge(3,6)}}

        Graph_IV graph_2 = new PVL10_Group14(6);
        graph_2.setEdge(0,1,8);
        graph_2.setEdge(0,2,1);
        graph_2.setEdge(1,2,1);
        graph_2.setEdge(1,3,1);
        graph_2.setEdge(2,4,1);
        graph_2.setEdge(3,1,1);
        graph_2.setEdge(3,5,1);
        graph_2.setEdge(4,3,1);
        graph_2.setEdge(4,5,8);

        graph_2.hasNegativeCycle();                        // False
        graph_2.shortestPath(0,5);            // {Edge(2,1), Edge(4,1), Edge(3,1), Edge(5,1)}
       /* List<Edge> test2=graph_2.shortestPath(0,5);
        Iterator it=test2.listIterator();
        while(it.hasNext()){
            System.out.println(it.next().toString());
        }*/
        System.out.println(graph_2.universalSink());                           // 5
        List<List<Edge>> check=graph_2.maxEdgeDisjointPaths(0,5);
        Iterator iter=check.listIterator();

        while(iter.hasNext()){
            List<Edge> current=(List<Edge>)iter.next();
            Iterator iter2=current.listIterator();
            while(iter2.hasNext()){
                System.out.print(iter2.next().toString());
            }
            System.out.println(" ");
        }
        //{
        //{Edge(1,8), Edge(3,1), Edge(5,1)}
        //{Edge(2,1), Edge(4,1), Edge(5,8)}
        //}
    }
}
