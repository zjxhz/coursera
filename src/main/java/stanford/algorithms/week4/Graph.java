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
}
