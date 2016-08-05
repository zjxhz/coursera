package stanford.algorithms.week4;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huanze on 8/5/2016.
 */
public class Graph {
    Map<Integer, Vertice> verticeMap = new HashMap<>();
    public Vertice getVertice(int id) {
        return verticeMap.get(id);
    }

    public Vertice addVertice(int id) {
        if(verticeMap.get(id) != null){
            return verticeMap.get(id);
        }
        Vertice v =  new Vertice(id);
        verticeMap.put(id, v);
        return v;
    }

    /**
     * Returns a new graph with arcs reversed and the ids of the vertices
     *
     * are replaced with their completion time.
     * @return A newly created, and reversed graph
     */
    public Graph reverse() {
        Graph graph = new Graph();

        for(Vertice src : verticeMap.values()){
            Vertice newSrc = graph.addVertice(src.getId());
            newSrc.setCompletionTime(src.getCompletionTime());
            for(Vertice next : src.nextVertices()){
                Vertice newNext = graph.addVertice(next.getId());
                newNext.setCompletionTime(newNext.getCompletionTime());
                newNext.addArcTo(newSrc);
            }
        }
        graph.swapIdAndCompletionTime();
        return graph;
    }

    private void swapIdAndCompletionTime() {
        Map<Integer, Vertice> newMap = new HashMap<>();
        for(Vertice v : verticeMap.values()){
            v.swapIdAndCompletionTime();
            newMap.put(v.getId(), v);
        }
        verticeMap = newMap;

    }

    public int size() {
        return verticeMap.size();
    }
}
