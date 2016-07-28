package stanford.algorithms.week3;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by huanze on 7/28/2016.
 */
public class Vertice {
    private int label;
    Set<Edge> edges = new HashSet<>();
    public Vertice(int label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertice vertice = (Vertice) o;

        return label == vertice.label;

    }

    @Override
    public int hashCode() {
        return label;
    }

    public Edge addEdgeTo(Vertice v2) {
        if(equals(v2)){
            return null;
        }
        Edge edge = new Edge(this, v2);
        edges.add(edge);
        v2.addEdge(edge);
        return edge;
    }

    private void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void removeEdgeTo(Vertice v2) {
        removeEdgesTo(v2, true);
    }

    private void removeEdge(Edge edge) {
        edges.remove(edge);
    }

    public int getEdgeCountTo(Vertice v2) {
        int count = 0;
        for(Edge edge : edges){
            if(edge.hasVertice(v2)){
                count++;
            }
        }
        return count;
    }

    public void contractEdge(Vertice v2) {
        removeEdgesTo(v2);
        for (Edge edge2 : v2.edges) {
            Vertice theOtherVertice = edge2.getTheOtherVertice(v2);
            addEdgeTo(theOtherVertice);
            v2.removeEdge(edge2);
        }
    }

    private void removeEdgesTo(Vertice v2) {
        removeEdgesTo(v2, false);
    }

    private void removeEdgesTo(Vertice v2, boolean first){
        Set<Edge> copy = new HashSet<>();
        copy.addAll(edges);
        for(Edge edge : copy){
            if(edge.hasVertice(v2)){
                edges.remove(edge);
                v2.removeEdge(edge);
                if(first){
                    return;
                }
            }
        }
    }

    @Override
    public String toString() {
        return String.valueOf(label);
    }
}
