package stanford.algorithms.week4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huanze on 8/5/2016.
 */
public class Vertice {
    private int id;
    private boolean explored;
    List<Vertice> edges = new ArrayList<>();
    public Vertice(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isExplored() {
        return explored;
    }

    public List<Vertice> nextVertices() {
        return edges;
    }

    public void addEdgeTo(Vertice v2) {
        edges.add(v2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertice vertice = (Vertice) o;

        return id == vertice.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return id + "-> ("
                + edgesToString() + "), "
                + explored;
    }

    private String edgesToString() {
        if(edges.isEmpty()){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(Vertice edge : edges){
            builder.append(edge.getId() + ",");
        }
        return builder.toString().substring(0, builder.length() - 1);
    }

    public void setExplored() {
        explored = true;
    }
}
