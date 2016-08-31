package stanford.algorithms.part1.week3;

import java.util.*;

/**
 * Created by huanze on 7/28/2016.
 */
public class Graph {
    List<Vertice> vertices = new ArrayList<>();
//    private Set<Edge> edges = new HashSet<>();

    public int getVerticeCount() {
        return vertices.size();
    }

    public boolean addVertice(Vertice v) {
        return vertices.add(v);
    }


    public Vertice findVertice(int i) {
        Vertice toFind = new Vertice(i);
        for(Vertice v : vertices){
            if(v.equals(toFind)){
                return v;
            }
        }
        return null;
    }

    public int findMinCut() {
        return contractEdges();
    }

    private int contractEdges() {
        Random random = new Random();
        while(vertices.size() > 2){
            List<Vertice> copy = new ArrayList<>(vertices);
            int r = random.nextInt(copy.size());
            Vertice v1 = copy.get(r);
            Vertice v2 = v1.contractAnyEdge();
            if(v2 != null){
                removeVertice(v2);
            }
        }
        return vertices.get(0).getEdgeCountTo(vertices.get(1));
    }

    private void removeVertice(Vertice vertice) {
        vertices.remove(vertice);
    }

    public static void main(String[] args) {
        int min = Integer.MAX_VALUE;
        String input  = "testdata/stanford/algorithms/part1/week3/kargerMinCut.txt";
        int CONSTANT = 10;
        Graph g = GraphFactory.createGraph(input);
        int rounds = g.getVerticeCount() * CONSTANT;
        System.out.println("Going to run: " + rounds);
        for (int i = 0; i < rounds; i++){
            g = GraphFactory.createGraph(input);
            int cuts = g.findMinCut();
            if (cuts < min){
                min = cuts;
                System.out.println("min: " + min + "  found at round: " + i);
            }
        }

    }
}
