package stanford.algorithms.part2.week4;

import stanford.algorithms.part2.model.graph.Edge;
import stanford.algorithms.part2.model.graph.Graph;
import stanford.algorithms.part2.model.graph.GraphFactory;
import stanford.algorithms.part2.model.graph.Vertice;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wayne on 9/28/16.
 */
public class FloydWarshall {
    private static int INFINITE = 9999;

    public static void main(String[] args) {
        //small1.txt: -10003
        //small2.txt: -6
        //small3.txt: 1(0 1 2 2 3)
        Graph graph = GraphFactory.createDirectedGraph("testdata/stanford/algorithms/part2/week4/g3.txt");
        int n = graph.size() + 1;
        int[][] distances = init(graph, n);
        for (int k = 1; k < n; k++) {
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    int d1 = distances[i][j];
                    int d21 = distances[i][k];
                    int d22 = distances[k][j];
                    if (d21 != INFINITE && d22 != INFINITE) {
                        distances[i][j] = Math.min(d1, d21 + d22);
                    } else {
                        distances[i][j] = d1;
                    }

                }
            }
        }

        if(hasNegativeCycle(distances)){
            System.out.println("Negative cycle detected");
        } else {
            System.out.println(shortestShortest(distances));
        }
//        for (int[] last : distances) {
//            System.out.println(Arrays.toString(last));
//        }

        //        Vertice start = graph.getVertice("1");
//        Vertice end = graph.getVertice(String.valueOf(graph.size()));
//        Set<Vertice> set = new HashSet<>();
//
//        for (int i = 1; i <= graph.size(); i++) {
//            int shortest = findShortestPath(set, start, end);
//            System.out.println(shortest);
//            set.add(graph.getVertice(String.valueOf(i)));
//        }

    }

    private static int shortestShortest(int[][] distances) {
        int shortest = INFINITE;
        for(int i = 1; i < distances.length; i++){
            for(int j = 1; j < distances.length; j++){
                if(i != j && distances[i][j] < shortest){
                    shortest = distances[i][j];
                }
            }
        }
        return shortest;
    }

    private static boolean hasNegativeCycle(int[][] distances) {
        for(int i = 0; i < distances.length; i++){
            if(distances[i][i] < 0){
                return true;
            }
        }
        return false;
    }

    private static int[][] init(Graph graph, int n) {
        int[][] distances = new int[n][n];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                Vertice from = graph.getVertice(String.valueOf(i));
                Vertice to = graph.getVertice(String.valueOf(j));
                if (hasEdge(from, to)) {
                    distances[i][j] = cost(from, to);
                } else {
                    if (i == j) {
                        distances[i][j] = 0;
                    } else {
                        distances[i][j] = INFINITE;
                    }
                }
            }
        }
        return distances;
    }

    private static int findShortestPath(Set<Vertice> set, Vertice start, Vertice end) {
        if (start == end) {
            return 0;
        }
        if (hasEdge(start, end)) {
            return cost(start, end);
        }
        if (set.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        int shortest = Integer.MAX_VALUE;
        for (Vertice through : set) {
            Set<Vertice> copy = new HashSet<>(set);
            copy.remove(through);
            int d1 = findShortestPath(copy, start, end);
            int d21 = findShortestPath(copy, start, through);
            int d22 = findShortestPath(copy, through, end);
            int d = Math.min(d1, d21 + d22);
            if (d > shortest) {
                shortest = d;
            }
        }
        return shortest;
    }

    private static int cost(Vertice from, Vertice to) {
        for (Edge edge : from.getEdges()) {
            if (edge.getVertice2().equals(to)) {
                return edge.getCost();
            }
        }
        return Integer.MAX_VALUE;
    }

    private static boolean hasEdge(Vertice start, Vertice end) {
        for (Edge edge : start.getEdges()) {
            if (edge.getVertice2().equals(end)) {
                return true;
            }
        }
        return false;
    }
}
