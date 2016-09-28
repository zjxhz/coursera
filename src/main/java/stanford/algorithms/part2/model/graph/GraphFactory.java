package stanford.algorithms.part2.model.graph;

import java.util.Scanner;

/**
 * Created by wayne on 9/1/16.
 */
public class GraphFactory {
    public static Graph createUndirectedGraph(String resource){
        return createGraph(resource, false);
    }

    public static Graph createDirectedGraph(String resource){
        return createGraph(resource, true);
    }

    private static Graph createGraph(String resource, boolean directed){
        Scanner in = new Scanner(ClassLoader.getSystemResourceAsStream(resource));
        Graph graph = new Graph();
        int verticeCount = in.nextInt();
        int edgeCount = in.nextInt();
        while(in.hasNextInt()){
            Vertice v1 = graph.addVertice(in.next());
            Vertice v2 = graph.addVertice(in.next());
            if(directed){
                v1.addDirectedEdgeTo(v2, in.nextInt());
            } else {
                v1.addEdgeTo(v2, in.nextInt());
            }
        }
        return graph;
    }
}
