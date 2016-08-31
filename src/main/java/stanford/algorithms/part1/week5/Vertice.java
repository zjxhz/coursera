package stanford.algorithms.part1.week5;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by wayne on 8/7/16.
 */
public class Vertice {

    private int id;
    private Map<Vertice, Integer> distancesTo = new HashMap<>();

    public Vertice(int id) {
        this.id = id;

    }

    public void addPath(Vertice v2, int distance) {
        distancesTo.put(v2, distance);
    }

    public int getDistance(Vertice v2) {
        return distancesTo.get(v2);
    }

    public Set<Vertice> getOutgoingVertices() {
        return distancesTo.keySet();
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
