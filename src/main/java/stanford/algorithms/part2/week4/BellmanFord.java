package stanford.algorithms.part2.week4;

import stanford.algorithms.part2.model.graph.Edge;
import stanford.algorithms.part2.model.graph.Graph;
import stanford.algorithms.part2.model.graph.GraphFactory;
import stanford.algorithms.part2.model.graph.Vertice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by wayne on 9/27/16.
 */
public class BellmanFord {
    private static int INFINITE = 9999;
    public static void main(String[] args) {
        //small1.txt: -10003
        //small2.txt: -6
        //small3.txt: 3(0 1 2 2 3)

        Graph g = GraphFactory.createDirectedGraph("testdata/stanford/algorithms/part2/week4/g1.txt");
        int shortestOfAll = INFINITE;
        for (int i = 1; i <= g.size(); i++) {
//            if(i % 50 == 0)
            {
                System.out.println("Checking shortest distance for vertice: " + i);
            }
            int shortest = findSingleShortestPage(g, i);
            if(shortest == INFINITE){
                System.out.println("Negative cycle detected.");
                return;
            }
            if(shortest < shortestOfAll){
                shortestOfAll = shortest;
            }
            System.out.printf("Shortest from %s: %s\n", i, shortest);
        }
        System.out.println("Shortest of all: " + shortestOfAll);


    }

    private static int findSingleShortestPage(Graph graph, int id) {
        Vertice s = graph.getVertice(String.valueOf(id));
        int n = graph.size();
        int[][] L = new int[n + 1][n];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < n; j++) {
                L[i][j] = INFINITE;
            }
        }
        L[0][id - 1] = 0;
        Queue<Vertice> queue = new LinkedList<>();
        queue.add(s);
        int k = 1;
        while (k <= n) {
            //inherit solutions from the previous round
            for (int i = 0; i < n; i++) {
                L[k][i] = Math.min(L[k][i], L[k - 1][i]);
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {//empty current queue to add one to the edge budget
                Vertice v = queue.poll();
                int id1 = v.getIdAsInt() - 1;
                L[k][id1] = Math.min(L[k][id1], L[k - 1][id1]);
                for (Edge e : v.getEdges()) {
                    Vertice v2 = e.getVertice2();
                    int id2 = v2.getIdAsInt() - 1;
                    int d = L[k - 1][id2];
                    int d2 = L[k - 1][id1] + e.getCost();
                    L[k][id2] = Math.min(d, d2);
                    queue.add(e.getVertice2());
                }
            }
            if(canStopEarlier(L, k)){
                System.out.println("Stopped earlier at round: " + k);
//                for (int[] arr : L) {
//                    System.out.println(Arrays.toString(arr));
//                }
                return findShortest(L, k);
            }
            k++;
        }
        for (int[] arr : L) {
            System.out.println(Arrays.toString(arr));
        }
        if(hasNegativeLoop(L)){
            return INFINITE;
        } else {
            return findShortest(L, n);
        }
    }

    /**
     * Returns shortest at round k
     */
    private static int findShortest(int[][] arr, int k){
        int shortest = INFINITE;
        for(int i = 0; i < arr[k].length; i++){
            int distance = arr[k][i];
            if(distance < shortest){
                shortest = distance;
            }
        }
        return shortest;
    }

    /**
     * Stops earlier when last round has no improvements
     */
    private static boolean canStopEarlier(int[][] distances, int k) {
        return !hasImprovements(distances, k);
    }

    private static boolean hasNegativeLoop(int[][] distances) {
        return hasImprovements(distances, distances.length - 1);
    }

    private static boolean hasImprovements(int[][] distances, int k) {
        for(int i = 0; i < distances[0].length; i++){
            if(distances[k][i] < distances[k-1][i]){
                return true;
            }
        }
        return false;
    }
}
