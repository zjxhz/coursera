package stanford.algorithms.part2.model.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wayne on 9/1/16.
 */
public class Vertice implements Comparable<Vertice>{
    private String id;
    private List<Edge> edges = new ArrayList<>();

    public Vertice(String id) {
        this.id = id;
    }

    public void addEdgeTo(Vertice v2, int cost) {
        addDirectedEdgeTo(v2, cost);
        v2.addDirectedEdgeTo(this, cost);
    }

    public void addDirectedEdgeTo(Vertice v2, int cost) {
        if(hasEdgeTo(v2)){
            return;
        }
        Edge edge = new Edge(this, v2, cost);
        edges.add(edge);

    }

    private boolean hasEdgeTo(Vertice v2) {
        for(Edge edge : edges){
            if(edge.getVertice2().equals(v2)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertice vertice = (Vertice) o;

        return id.equals(vertice.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public int compareTo(Vertice o) {
        return id.compareTo(o.id);
    }

    public int getIdAsInt() {
        return Integer.valueOf(id);
    }
}
