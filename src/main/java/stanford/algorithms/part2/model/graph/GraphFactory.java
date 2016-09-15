package stanford.algorithms.part2.model.graph;

import java.util.Scanner;

/**
 * Created by wayne on 9/1/16.
 */
public class GraphFactory {
    public static Graph create(String resource){
        Scanner in = new Scanner(ClassLoader.getSystemResourceAsStream(resource));
        Graph graph = new Graph();
        int verticeCount = in.nextInt();
        int edgeCount = in.nextInt();
        while(in.hasNextInt()){
            Vertice v1 = graph.addVertice(in.next());
            Vertice v2 = graph.addVertice(in.next());
            v1.addEdgeTo(v2, in.nextInt());
        }
        return graph;
    }
}
