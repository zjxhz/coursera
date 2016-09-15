package stanford.algorithms.part2.model.graph;

import java.util.*;

/**
 * Created by wayne on 9/1/16.
 */
public class Graph {
    Map<String, Vertice> verticeMap = new HashMap<>();
    List<Edge> edges;

    public Vertice addVertice(String id) {
        if (verticeMap.get(id) == null){
            verticeMap.put(id, new Vertice(id));
        }
        return verticeMap.get(id);
    }

    public Collection<Vertice> getVertices(){
        return verticeMap.values();
    }
    /**
     * Returns the vertice with given Id
     * @return Vertice with given Id, or null if no such vertice exists
     */
    public Vertice getVertice(String id) {
        return verticeMap.get(id);
    }

    public int size() {
        return verticeMap.size();
    }
}
