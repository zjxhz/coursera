package stanford.algorithms.week3;

import java.util.*;

/**
 * Created by huanze on 7/28/2016.
 */
public class Vertice {
    private int label;
    List<Edge> edges = new ArrayList<>();
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

    /**
     * Contracts an edge with v2. Other edges v2 had would be bound to this vertice.
     *
     * @param v2 Vertice that has an edge with this one
     */
    public void contractEdge(Vertice v2) {
        removeEdgesTo(v2);
        List<Edge> copy = new ArrayList<>(v2.edges);
        for (Edge edge2 :copy) {
            Vertice theOtherVertice = edge2.getTheOtherVertice(v2);
            addEdgeTo(theOtherVertice);
            v2.removeEdge(edge2);
            theOtherVertice.removeEdge(edge2);
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

    public Vertice contractAnyEdge() {
        if(edges.isEmpty()){
            return null;
        }
        int r = new Random().nextInt(edges.size());
        Edge edge = edges.get(r);
        Vertice v2 = edge.getTheOtherVertice(this);
        contractEdge(v2);
        return v2;
    }
}
