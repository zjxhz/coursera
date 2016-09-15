package stanford.algorithms.part2.week2;

import stanford.algorithms.part2.model.graph.Edge;
import stanford.algorithms.part2.model.graph.Graph;
import stanford.algorithms.part2.model.graph.Vertice;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wayne on 9/10/16.
 */
public class HammingClustering {
    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();
        System.out.println("started at: " + t0);
        Scanner in = new Scanner(
                ClassLoader.getSystemResourceAsStream("testdata/stanford/algorithms/part2/week2/clustering_big.txt"));
        Set<BitSet> verticeSet = new HashSet<>();
        int N = in.nextInt();
        int length = in.nextInt();
        for (int i = 0; i < N; i++) {
            BitSet vertice = new BitSet();
            for (int j = 0; j < length; j++) {
                if (in.nextInt() == 1) {
                    vertice.set(j);
                }
            }
            verticeSet.add(vertice);
        }
        System.out.println("added " + verticeSet.size() + " distinct vertices: " + (System.currentTimeMillis() - t0) / 1000);


        Graph graph = new Graph();
        List<BitSet> vertices = new ArrayList<>(verticeSet);
        long count = 0;
        for (int i = 0; i < vertices.size(); i++) {
            BitSet v1 = vertices.get(i);
            for (int j = i + 1; j < vertices.size(); j++) {
                BitSet v2 = vertices.get(j);
                BitSet temp = (BitSet) v1.clone();
                temp.xor(v2);
                int distance = temp.cardinality();
                if (distance <= 2) {
                    Vertice vertice1 = graph.addVertice(String.valueOf(i));
                    Vertice vertice2 = graph.addVertice(String.valueOf(j));
                    vertice1.addEdgeTo(vertice2, distance);
//                    System.out.println("Less than 3: " + i + "->" + j);
                    count++;
                }
            }
            if (i % 1000 == 0) {
                System.out.println("i = " + i);
            }
        }

        System.out.println("Created edges: " + (System.currentTimeMillis() - t0) / 1000);

        System.out.println("Edges: " + count);
        System.out.println("Vertices: " + graph.size());
        int c1 = vertices.size() - graph.size();
        System.out.println("Clusters with only one vertice: " + c1);

        Queue<Edge> edges = getSortedEdges(graph);
        System.out.println("Sorted edges: " + (System.currentTimeMillis() - t0) / 1000);
        int c2 = calcClusterSize(edges) + c1;
        System.out.println("total clusters: " + c2);
    }

    private static Queue<Edge> getSortedEdges(Graph graph) {
        List<Edge> edges = new ArrayList<>();
        for (Vertice vertice : graph.getVertices()) {
            edges.addAll(vertice.getEdges().stream()
                    .filter(edge -> edge.getVertice1().compareTo(edge.getVertice2()) < 0)
                    .collect(Collectors.toList()));
        }
        Collections.sort(edges, (o1, o2) -> o1.getCost() - o2.getCost());
        return new LinkedList<>(edges);
    }

    public static int calcClusterSize(Queue<Edge> edges) {
        List<Cluster> clusters = new ArrayList<>();
        while (!edges.isEmpty()) {
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
        }
        return clusters.size();
    }

    private static Cluster findCluster(List<Cluster> clusters, Vertice vertice) {
        for (Cluster cluster : clusters) {
            if (cluster.contains(vertice)) {
                return cluster;
            }
        }
        return null;
    }

}

class HEdge {
    int v1;
    int v2;
    int distance;

    public HEdge(int v1, int v2, int distance) {
        this.v1 = v1;
        this.v2 = v2;
        this.distance = distance;
    }
}