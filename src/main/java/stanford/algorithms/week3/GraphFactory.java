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
            int label1 = lineScanner.nextInt();
            Vertice end1 = getOrCreate(graph, label1);
            while(lineScanner.hasNextInt()){
                int label2 = lineScanner.nextInt();
                Vertice end2 = getOrCreate(graph, label2);
                if(end1.getEdgeCountTo(end2) == 0){
                    end1.addEdgeTo(end2); //add only once
                }
            }
        }
        return graph;
    }

    private static Vertice getOrCreate(Graph graph, int verticeLabel) {
        Vertice vertice = graph.findVertice(verticeLabel);
        if(vertice == null){
            vertice = new Vertice((verticeLabel));
            graph.addVertice(vertice);
        }
        return vertice;
    }
}
