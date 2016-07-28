package stanford.algorithms.week3;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by huanze on 7/28/2016.
 */
public class Graph {
    Set<Vertice> vertices = new HashSet<>();
    private Set<Edge> edges = new HashSet<>();

    public int getVerticeCount() {
        return vertices.size();
    }

    public boolean addVertice(Vertice v) {
        return vertices.add(v);
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public boolean addEdge(Edge edge) {
        return edges.add(edge);
    }
}
