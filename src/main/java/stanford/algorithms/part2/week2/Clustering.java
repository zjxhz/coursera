package stanford.algorithms.part2.week2;

import stanford.algorithms.part2.model.graph.Edge;
import stanford.algorithms.part2.model.graph.Graph;
import stanford.algorithms.part2.model.graph.GraphFactory;
import stanford.algorithms.part2.model.graph.Vertice;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wayne on 9/10/16.
 */
public class Clustering {
    public static void main(String[] args) {
        Graph graph = GraphFactory.create("testdata/stanford/algorithms/part2/week2/clustering.txt");
        Clustering clustering = new Clustering();
        int spacing = clustering.findMaxSpacing(graph, 4);
        System.out.println(spacing);
    }

    public int findMaxSpacing(Graph graph, int custerNumber) {
        Queue<Edge> edges = getSortedEdges(graph);
        int count = graph.size();
        List<Cluster> clusters = new ArrayList<>();
        while(true) {
            Edge edge = edges.poll();
            Cluster c1 = findCluster(clusters, edge.getVertice1());
            Cluster c2 = findCluster(clusters, edge.getVertice2());
            if (c1 == null && c2 == null) {
                Cluster cluster = new Cluster(edge);
                clusters.add(cluster);
            } else if (c1 == null && c2 != null) {
                c2.addVertice(edge.getVertice1());
            } else if (c1 != null && c2 == null) {
                c1.addVertice(edge.getVertice2());
            } else if (c1 != null && c2 != null) {
                if (c1 == c2) {
                    continue;
                }
                c1.addCluster(c2);
                clusters.remove(c2);
            }
            if (count == custerNumber) {
                return edge.getCost();
            }
            count--;
        }
    }

    private Cluster findCluster(List<Cluster> clusters, Vertice vertice) {
        for (Cluster cluster : clusters) {
            if (cluster.contains(vertice)) {
                return cluster;
            }
        }
        return null;
    }


    private Queue<Edge> getSortedEdges(Graph graph) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i <= graph.size(); i++) {
            Vertice vertice = graph.getVertice(String.valueOf(i));
            edges.addAll(vertice.getEdges().stream()
                    .filter(edge -> edge.getVertice1().compareTo(edge.getVertice2()) < 0)
                    .collect(Collectors.toList()));
        }
        Collections.sort(edges, (o1, o2) -> o1.getCost() - o2.getCost());
        return new LinkedList<>(edges);
    }
}
