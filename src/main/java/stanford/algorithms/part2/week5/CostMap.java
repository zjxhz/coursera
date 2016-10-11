package stanford.algorithms.part2.week5;

import stanford.algorithms.part2.model.graph.Edge;
import stanford.algorithms.part2.model.graph.Graph;
import stanford.algorithms.part2.model.graph.Vertice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wayne on 10/6/16.
 */
public class CostMap {
    public CostMap(Graph graph){
        initCostMap(graph);
    }

    private Map<DirectedEdge, Integer> costMap;

    private void initCostMap(Graph graph) {
        costMap = new HashMap<>();
        for (int i = 1; i <= graph.size(); i++) {
            Vertice vertice = graph.getVertice(String.valueOf(i));
            for (Edge edge : vertice.getEdges()) {
                DirectedEdge de = new DirectedEdge(edge);
                costMap.put(de, edge.getCost());
            }
        }
    }

    public Integer cost(Vertice from, Vertice to) {
        DirectedEdge edge = new DirectedEdge(from, to);
        return costMap.get(edge);
    }
}


class DirectedEdge {
    Vertice from;
    Vertice to;

    public DirectedEdge(Edge edge) {
        this.from = edge.getVertice1();
        this.to = edge.getVertice2();
    }

    public DirectedEdge(Vertice from, Vertice to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirectedEdge that = (DirectedEdge) o;

        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        return to != null ? to.equals(that.to) : that.to == null;

    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DirectedEdge{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}

