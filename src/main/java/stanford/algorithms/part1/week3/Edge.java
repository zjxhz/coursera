package stanford.algorithms.part1.week3;

/**
 * Created by huanze on 7/28/2016.
 */
public class Edge {
    private Vertice end1, end2;
    public Edge(Vertice end1, Vertice end2) {
        this.end1 = end1;
        this.end2 = end2;
    }

    public boolean hasVertice(Vertice vertice) {
        return end1.equals(vertice) || end2.equals(vertice);
    }

    public Vertice getTheOtherVertice(Vertice vertice) {
        assert vertice.equals(end1) || vertice.equals(end2);
        return vertice.equals(end1) ? end2 : end1;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "end1=" + end1 +
                ", end2=" + end2 +
                '}';
    }
}
