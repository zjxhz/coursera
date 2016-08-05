package stanford.algorithms.week4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huanze on 8/5/2016.
 */
public class DepthFirstSearch {
    /**
     * Searches the graph and returns a String representation of the depth first search.
     *
     * @param graph Graph containing vertices
     * @param max Max ID of the vertice
     * @return String representation separated with commas
     */
    public String search(Graph graph, int max) {
        List<Vertice> vertices = new ArrayList<>();
        for(int i = 1; i <= max; i++){
            Vertice start = graph.getVertice(i);
            search(vertices, graph, start);
        }
        return toString(vertices);
    }

    private String toString(List<Vertice> vertices) {
        if(vertices.isEmpty()){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(Vertice v : vertices){
            builder.append(v.getId() + ",");
        }
        return builder.toString().substring(0, builder.length() - 1);
    }

    private void search(List<Vertice> vertices, Graph graph, Vertice start) {
        if(start.isExplored()){
            return;
        }
        vertices.add(start);
        start.setExplored();
        for(Vertice next : start.nextVertices()){
            search(vertices, graph, next);
        }
    }


}
