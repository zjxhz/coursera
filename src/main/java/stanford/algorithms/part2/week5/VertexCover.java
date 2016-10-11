package stanford.algorithms.part2.week5;

import stanford.algorithms.part2.model.graph.Edge;
import stanford.algorithms.part2.model.graph.Graph;
import stanford.algorithms.part2.model.graph.GraphFactory;
import stanford.algorithms.part2.model.graph.Vertice;

import java.util.*;

/**
 * Created by wayne on 10/2/16.
 */
public class VertexCover {
    public static void main(String[] args) {
        Graph graph = GraphFactory.createUndirectedGraphWithoutCost("testdata/stanford/algorithms/part2/week5/vcp3.txt");
        List<Edge> edges = getEdges(graph);
        System.out.println("Found: " + find(graph, edges, 4));
    }

    private static boolean find(Graph graph, List<Edge> edges, int k) {
        if (k == 1) {
            return isStar(edges);
        }
        for (Edge edge : edges) {
            if (find(graph, edges, edge.getVertice1(), k - 1)) {
                return true;
            }
            if (find(graph, edges, edge.getVertice2(), k - 1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean find(Graph graph, List<Edge> edges, Vertice vertice, int k) {
        List<Edge> edges2 = new LinkedList<>(edges);
        deleteVertice(edges2, vertice);
        System.out.printf("Deteling %s, size: %d\n", vertice, edges2.size());
        return find(graph, edges2, k);
    }

    private static boolean isStar(List<Edge> edges) {
        Map<Vertice, Integer> map = new HashMap<>();
        for (Edge edge : edges) {
            updateCount(map, edge.getVertice1());
            updateCount(map, edge.getVertice2());
        }
        for (Vertice v : map.keySet()) {
            if (map.get(v) == edges.size()) {
                return true;
            }
        }
        return false;
    }

    private static void updateCount(Map<Vertice, Integer> map, Vertice vertice) {
        if (map.get(vertice) == null) {
            map.put(vertice, 1);
        } else {
            map.put(vertice, map.get(vertice) + 1);
        }
    }

    private static void deleteVertice(List<Edge> edges, Vertice vertice) {
        for (ListIterator<Edge> iterator = edges.listIterator(); iterator.hasNext(); ) {
            Edge edge = iterator.next();
            if (edge.getVertice1().equals(vertice)) {
                iterator.remove();
            }
        }
    }


    private static List<Edge> getEdges(Graph graph) {
        List<Edge> edges = new LinkedList<>();
        for (int i = 1; i <= graph.size(); i++) {
            Vertice vertice = graph.getVertice(String.valueOf(i));
            for (Edge edge : vertice.getEdges()) {
                if (edge.getVertice1().compareTo(edge.getVertice2()) < 0) {
                    edges.add(edge);
                }
            }
        }
        return edges;
    }
}
