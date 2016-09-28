package stanford.algorithms.part2.week1;

import stanford.algorithms.part2.model.graph.Edge;
import stanford.algorithms.part2.model.graph.Graph;
import stanford.algorithms.part2.model.graph.GraphFactory;
import stanford.algorithms.part2.model.graph.Vertice;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wayne on 9/1/16.
 */
public class PrimMST {
    public static void main(String[] args) {
        Graph graph = GraphFactory.createUndirectedGraph("testdata/stanford/algorithms/part2/week1/edges.txt");
        Vertice v1 = graph.getVertice("1");
        Set<Vertice> included = new HashSet<>();
        included.add(v1);
        int cost = 0;
        for(int i = 1; i < graph.size(); i++){
            int cheapest = Integer.MAX_VALUE;
            Vertice next = null;
            for(Vertice v : included){
                for(Edge edge : v.getEdges()){
                    Vertice to = edge.getVertice2();
                    if(!included.contains(to) && edge.getCost() < cheapest){
                        cheapest = edge.getCost();
                        next = to;
                    }
                }
            }
            included.add(next);
            cost += cheapest;
        }
        System.out.println(cost);
    }

}
