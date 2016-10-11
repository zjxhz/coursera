package stanford.algorithms.part2.week5;

import stanford.algorithms.part2.model.graph.Edge;
import stanford.algorithms.part2.model.graph.Graph;
import stanford.algorithms.part2.model.graph.GraphFactory;
import stanford.algorithms.part2.model.graph.Vertice;

import java.util.*;

/**
 * Created by wayne on 10/4/16.
 */
public class TSP {
    private static int INFINITE = Integer.MAX_VALUE;
    private static Map<SubStructure, Integer> cache = new HashMap<>();
    private Graph graph;
    private Vertice start;
    private Vertice end;
    private Set<Vertice> all;
    private int cacheHits = 0;
    private int min = INFINITE;
    private CostMap costMap;

    public TSP(Graph graph, Vertice start) {
        this.graph = graph;
        this.start = start;
    }
    

    public static void main(String[] args) {
        Graph graph = GraphFactory.createUndirectedGraph("testdata/stanford/algorithms/part2/week5/tsp20g.txt");
        Vertice start = graph.getVertice("1");
        TSP tsp = new TSP(graph, start);
        System.out.println("min: " + tsp.min());
//        for (Map.Entry entry : cache.entrySet()) {
//            System.out.println(entry);
//        }
        System.out.println("path: " + tsp.path());

    }

    public int min(){
        costMap = new CostMap(graph);
        Set<Vertice> all = getAll();
        for (int i = 2; i <= graph.size(); i++) {
            Vertice end = graph.getVertice(String.valueOf(i));
            System.out.println("checking end: " + end);
            int temp = min(all, end) + costMap.cost(end, start);
            if (temp < min) {
                min = temp;
                System.out.printf("end: %s, cost: %d\n", end, costMap.cost(end, start));
                this.end = end;
            }
        }

        return min;
    }

    private Set<Vertice> getAll() {
        if(all != null){
            return all;
        }
        all = new HashSet<>();
        for (int i = 1; i <= graph.size(); i++) {
            all.add(graph.getVertice(String.valueOf(i)));
        }
        return all;
    }

    public List<Vertice> path(){
        List<Vertice> path = new Stack<>();
        path.add(end);

        Set<Vertice> set = new HashSet<>(all);
        Vertice current = end;
        int value = min - costMap.cost(end, start);
        while(set.size() > 1){//only 1 left
            set.remove(current);
            Vertice previous = findPrevious(set, value, current);
            if(previous != null){
                value -= costMap.cost(previous, current);
                current = previous;
                path.add(current);
            }
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }

    private Vertice findPrevious(Set<Vertice> set, int value, Vertice to) {
        for(Vertice through : set){
            if(through.getIdAsInt() == 1){
                continue;
            }
            if(min(set, through) + costMap.cost(through, to) == value){
                return through;
            }
        }
        return null;
    }

    public int min(Set<Vertice> candidates, Vertice end) {
        SubStructure sub = new SubStructure(candidates, end);
        Integer min = cache.get(sub);
        if (min != null) {
            cacheHits++;
            if (cacheHits % 1_000_000 == 0) {
                System.out.println("cache hits: " + cacheHits);
            }
            return min;
        }
        if (candidates.size() == 2) {
            return costMap.cost(start, end);
        }
        Set<Vertice> copy = new HashSet<>(candidates);
        copy.remove(end);
        min = INFINITE;
        for (Vertice through : copy) {
            if (through.getIdAsInt() == 1) {
                continue;
            }
            int temp = INFINITE;
            int submin = min(copy, through);
            if (submin != INFINITE) {
                temp = submin + costMap.cost(through, end);
            }
            if (temp < min) {
                min = temp;
            }
        }
        cache.put(sub, min);
        return min;
    }


}

class SubStructure {
    Set<Vertice> candidates;
    Vertice end;

    public SubStructure(Set<Vertice> candidates, Vertice end) {
        this.candidates = candidates;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubStructure that = (SubStructure) o;

        if (!candidates.equals(that.candidates)) return false;
        return end.equals(that.end);

    }

    @Override
    public int hashCode() {
        int result = candidates.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SubStructure{" +
                "candidates=" + candidates +
                ", end=" + end +
                '}';
    }
}
