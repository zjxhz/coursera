package stanford.algorithms.part1.week4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by huanze on 8/5/2016.
 */
public class DepthFirstSearch {
    private int completionTime;
    private int sccCount;
    /**
     * Searches the graph and returns a String representation of the depth first search.
     *
     * @param graph Graph containing vertices
     * @return String representation separated with commas
     */
    public String search(Graph graph) {
        List<Vertice> vertices = new ArrayList<>();
        for(int i = 1; i <= graph.size(); i++){
            Vertice start = graph.getVertice(i);
            search(vertices, start);
        }
        return verticesToString(vertices);
    }

    private String verticesToString(List<Vertice> vertices) {
        if(vertices.isEmpty()){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(Vertice v : vertices){
            builder.append(v.getId() + ",");
        }
        return builder.toString().substring(0, builder.length() - 1);
    }

    private void search(List<Vertice> vertices, Vertice start) {
        if(start.isExplored()){
            return;
        }
        vertices.add(start);
        start.setExplored();
        for(Vertice next : start.nextVertices()){
            search(vertices, next);
        }
        start.setCompletionTime(++completionTime);
    }

    public String computeMaxSCCs(Graph graph){
        search(graph);
        Graph reversedGraph = graph.reverse();
        List<Integer> sccCounts = new ArrayList<>();
        for(int i = graph.size(); i >= 1; i--){
            sccCount = 0;
            Vertice v = reversedGraph.getVertice(i);
            searchScc(v);
            sccCounts.add(sccCount);
        }
        return sccToString(sccCounts);
    }

    private String sccToString(List<Integer> sccCounts) {
        Collections.sort(sccCounts, (o1, o2) -> o2 - o1);
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 5; i++){
            int count = i < sccCounts.size() ? sccCounts.get(i) : 0;
            builder.append(count).append(',');
        }
        return builder.toString().substring(0, builder.length() - 1);
    }

    private void searchScc(Vertice v) {
        if(v.isExplored()){
            return;
        }
        sccCount++;
        v.setExplored();
        for(Vertice next : v.nextVertices()){
            searchScc(next);
        }

    }

    public static void main(String[] args) {
        Graph graph = GraphFactory.createGraph("testdata/stanford/algorithms/part1/week4/scc.txt");
        System.out.println("Done reading data");
        System.out.println(new DepthFirstSearch().computeMaxSCCs(graph));
    }


}
