package stanford.algorithms.week4;

import java.util.Scanner;

/**
 * Created by huanze on 8/5/2016.
 */
public class GraphFactory {
    public static Graph createGraph(String resource) {
        Graph graph = new Graph();
        Scanner in = new Scanner(ClassLoader.getSystemResourceAsStream(resource));
        while (in.hasNextInt()) {
            Vertice v1 = getOrCreateVertice(graph, in.nextInt());
            Vertice v2 = getOrCreateVertice(graph, in.nextInt());
            v1.addArcTo(v2);
        }
        return graph;
    }

    private static Vertice getOrCreateVertice(Graph graph, int id) {
        if(graph.getVertice(id) != null){
            return graph.getVertice(id);
        }
        return graph.addVertice(id);
    }


}
