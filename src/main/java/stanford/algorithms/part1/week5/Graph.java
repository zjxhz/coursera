package stanford.algorithms.part1.week5;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wayne on 8/7/16.
 */
public class Graph {
    Map<Integer, Vertice> verticeMap = new HashMap<>();

    public Vertice addVertice(int id) {
        Vertice v = verticeMap.get(id);
        if(v == null){
            v = new Vertice(id);
            verticeMap.put(id, v);
        }
        return v;
    }

    public Vertice getVertice(int id) {
        return verticeMap.get(id);
    }

    public int size() {
        return verticeMap.size();
    }
}
