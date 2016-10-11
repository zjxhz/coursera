package stanford.algorithms.part2.week5;

import stanford.algorithms.part2.model.graph.Graph;
import stanford.algorithms.part2.model.graph.GraphFactory;
import stanford.algorithms.part2.model.graph.Vertice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * Adding points to TSP and calculate the final result
 */
public class TSP2 {
    private CostMap costMap;
//    private Graph graph;
    public TSP2(Graph graph) {
        costMap = new CostMap(graph);
//        this.graph = graph;
    }

    public static void main(String[] args) {
        Graph graph = GraphFactory.createUndirectedGraph("testdata/stanford/algorithms/part2/week5/tsp20g.txt");
        int [] ids = {1, 5, 9, 10, 13, 17, 16, 19, 15, 18, 20, 14, 12, 11, 8, 6, 2, 3, 7, 4};
        List<Vertice> vertices = toVertices(graph, ids);
        TSP2 tsp2 = new TSP2(graph);
        System.out.println("total: " + tsp2.total(vertices));
        System.out.println(Arrays.toString(ids));
        System.out.println(Arrays.toString(tsp2.toOriginalIds(ids)));
        System.out.println();
        List<List<Integer>> allPaths = tsp2.allPaths(tsp2.toOriginalIds(ids));
        for(List<Integer> list : allPaths){
            System.out.println(list);
        }

        Graph graph2 = GraphFactory.createUndirectedGraph("testdata/stanford/algorithms/part2/week5/tspg.txt");
        TSP2 tsp21 = new TSP2(graph2);
        int min = Integer.MAX_VALUE;
        for(List<Integer> list : allPaths){
            vertices = toVertices(graph2, list);
            int total = tsp21.total(vertices);
            if(total < min){
                min = total;
            }
            System.out.println("total: " + total);
        }
        System.out.println("min: " + min);



    }

    private List<List<Integer>> allPaths(int[] originalIds){
        List<Integer> ids = new ArrayList<>();
        for(int id : originalIds){
            ids.add(id);
            if(id == 1){
                ids.add(2);//always add 2 after 1
            }
        }
        List<Integer> sub1 = new ArrayList<>(Arrays.asList(10, 11));
        List<Integer> sub2 = new ArrayList<>(Arrays.asList(21, 22, 23));
        List<Integer> sub3 = new ArrayList<>(Arrays.asList(24, 25));
        Permutation<Integer> p = new Permutation();
        List<List<Integer>> p1 = p.generatePerm(sub1);
        List<List<Integer>> p2 = p.generatePerm(sub2);
        List<List<Integer>> p3 = p.generatePerm(sub3);
        List<List<Integer>> result = new ArrayList<>();
        for(List<Integer> l1 : p1){
            for(List<Integer> l2 : p2){
                for(List<Integer> l3 : p3){
//                    List<Integer> temp = new ArrayList<>(l1);
//                    temp.addAll(l2);
//                    temp.addAll(l3);
//                    result.add(temp);
                    result.add(update(ids, l1, l2, l3));
                }
            }
        }
        return result;
    }

    private List<Integer> update(List<Integer> ids, List<Integer> l1, List<Integer> l2, List<Integer> l3) {
        List<Integer> copy = new ArrayList<>(ids);
        for(ListIterator<Integer> it = copy.listIterator(); it.hasNext();){
            int id = it.next();
            if(id == 10){
                it.remove();
                l1.forEach(it::add);
            }
            if(id == 21){
                it.remove();
                l2.forEach(it::add);
            }
            if(id == 24){
                it.remove();
                l3.forEach(it::add);
            }
        }
        return copy;
    }

    private int[] toOriginalIds(int[] ids){
        int [] ids0 = new int[ids.length];
        for(int i = 0; i < ids.length; i++){
            int id = ids[i];

            if(id == 20){
                ids0[i] = id + 4;
            } else if(id >= 10){
                ids0[i] = id + 2;
            } else if(id > 1) {
                ids0[i] = id + 1;
            } else {
                ids0[i] = id;
            }
        }
        return ids0;
    }

    /**
     * Caculates the total cost of vertices
     */
    private int total(List<Vertice> vertices){
        int total = 0;
        for(int i = 0; i < vertices.size(); i++){
            int from = i;
            int to = i + 1;
            if(to == vertices.size()){
                to = 0;
            }
            total += costMap.cost(vertices.get(from), vertices.get(to));
        }
        return total;
    }

    private static List<Vertice> toVertices(Graph graph, int[] ids){
        List<Vertice> vertices = new ArrayList<>();
        for(int id : ids){
            Vertice vertice = graph.getVertice(String.valueOf(id));
            vertices.add(vertice);
        }
        return vertices;
    }

    private static List<Vertice> toVertices(Graph graph, List<Integer> ids){
        int [] idArr = new int[ids.size()];
        for(int i = 0; i < ids.size(); i++){
            int id = ids.get(i);
            idArr[i] = id;
        }
        return toVertices(graph, idArr);
    }
}
