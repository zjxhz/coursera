package stanford.algorithms.part1.week5;

import java.util.Scanner;

/**
 * Created by wayne on 8/7/16.
 */
public class GraphFactory {
    public static Graph createGraph(String resource) {
        Graph graph = new Graph();
        Scanner in = new Scanner(ClassLoader.getSystemResourceAsStream(resource));
        while(in.hasNextLine()){
            String line = in.nextLine();
            Scanner lineScanner = new Scanner(line);
            int id = lineScanner.nextInt();
            Vertice vertice = graph.addVertice(id);
            while(lineScanner.hasNext()){
                String vd = lineScanner.next();
                String [] vdArr = vd.split(",");
                int idTo = Integer.valueOf(vdArr[0]);
                int distance = Integer.valueOf(vdArr[1]);
                Vertice to = graph.addVertice(idTo);
                vertice.addPath(to, distance);
            }
        }
        return graph;
    }
}
