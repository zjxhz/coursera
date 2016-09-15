package stanford.algorithms.part2.week2;

import stanford.algorithms.part2.model.graph.Edge;
import stanford.algorithms.part2.model.graph.Vertice;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wayne on 9/10/16.
 */
public class Cluster {
    Set<Vertice> vertices = new HashSet<>();

    public Cluster(Edge edge) {
        vertices.add(edge.getVertice1());
        vertices.add(edge.getVertice2());
    }

    public boolean contains(Vertice vertice) {
        return vertices.contains(vertice);
    }

    public void addVertice(Vertice vertice) {
        vertices.add(vertice);
    }

    public void addCluster(Cluster c2) {
        vertices.addAll(c2.vertices);
    }
}
