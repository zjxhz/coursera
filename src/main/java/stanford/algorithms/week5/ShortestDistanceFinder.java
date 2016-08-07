package stanford.algorithms.week5;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wayne on 8/7/16.
 */
public class ShortestDistanceFinder {
    public static String find(Graph graph, int id) {
        int size = graph.size();
        int [] shortestDistances = new int[size];
        Set<Vertice> includedVertices = new HashSet<>();
        Vertice start = graph.getVertice(id);
        includedVertices.add(start);
        int i = 1;
        while(i++ < size){
            Vertice nextToInclude = null;
            int shortestDistance = Integer.MAX_VALUE;
            for(Vertice included : includedVertices){
                for(Vertice next : included.getOutgoingVertices()){
                    if(!includedVertices.contains(next)){
                        int distance = included.getDistance(next) + shortestDistances[included.getId() - 1];
                        if(distance < shortestDistance){
                            shortestDistance = distance;
                            nextToInclude = next;
                        }
                    }
                }
            }
            includedVertices.add(nextToInclude);
            shortestDistances[nextToInclude.getId() - 1] = shortestDistance;
        }
        return shortestDistancesToString(shortestDistances);
    }

    private static String shortestDistancesToString(int[] shortestDistances) {
        if(shortestDistances.length == 0){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(int distance : shortestDistances){
            builder.append(distance).append(",");
        }
        return builder.substring(0, builder.length() - 1);

    }

    public static void main(String[] args) {
        Graph graph = GraphFactory.createGraph("testdata/week5/simple0.txt");
        System.out.println(ShortestDistanceFinder.find(graph, 1));

        graph = GraphFactory.createGraph("testdata/week5/dijkstraData.txt");
        String shortestDistances = ShortestDistanceFinder.find(graph, 1);
        int [] dest = {7,37,59,82,99,115,133,165,188,197};
        String [] arr = shortestDistances.split(",");
        System.out.println(shortestDistances);
        for(int i : dest){
            System.out.print(arr[i - 1] + ",");
        }
    }


}
