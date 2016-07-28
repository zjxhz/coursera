package stanford.algorithms.week3;

import java.util.Scanner;

/**
 * Created by huanze on 7/28/2016.
 */
public class GraphFactory {

    public static Graph createGraph(String resource) {
        Graph graph = new Graph();
        Scanner in = new Scanner(ClassLoader.getSystemResourceAsStream(resource));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            Scanner lineScanner = new Scanner(line);
            int label = lineScanner.nextInt();
            Vertice end1 = new Vertice(label);
            graph.addVertice(end1);
            while(lineScanner.hasNextInt()){
                Vertice end2 = new Vertice(lineScanner.nextInt());
                Edge edge = new Edge(end1, end2);
                graph.addEdge(edge);
            }
        }
        return graph;
    }
}
