package stanford.algorithms.week4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huanze on 8/5/2016.
 */
public class Vertice {
    private int id;
    private boolean explored;
    List<Vertice> arcsTo = new ArrayList<>();
    private int completionTime;

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
        return arcsTo;
    }

    public void addArcTo(Vertice v2) {
        arcsTo.add(v2);
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
        if(arcsTo.isEmpty()){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(Vertice edge : arcsTo){
            builder.append(edge.getId() + ",");
        }
        return builder.toString().substring(0, builder.length() - 1);
    }

    public void setExplored() {
        explored = true;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public boolean hasArcTo(Vertice v2) {
        return arcsTo.contains(v2);
    }

    public void swapIdAndCompletionTime() {
        int temp = id;
        id = completionTime;
        completionTime = temp;
    }
}
