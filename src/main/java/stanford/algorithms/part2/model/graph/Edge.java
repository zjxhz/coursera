package stanford.algorithms.part2.model.graph;

/**
 * Created by wayne on 9/1/16.
 */
public class Edge {
    private Vertice vertice1;
    private Vertice vertice2;
    private int cost;

    public Vertice getVertice1() {
        return vertice1;
    }

    public Vertice getVertice2() {
        return vertice2;
    }

    public int getCost() {
        return cost;
    }


    public Edge(Vertice v1, Vertice v2, int cost) {
        this.vertice1 = v1;
        this.vertice2 = v2;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return vertice1 + "," + vertice2 + ": " + cost;
    }
}
