package stanford.algorithms.part2.week5;

import java.util.*;

/**
 * Created by wayne on 10/5/16.
 */
public class Tsp2G {
    public static void main(String[] args) {
        Scanner in = new Scanner(
                ClassLoader.getSystemResourceAsStream("testdata/stanford/algorithms/part2/week5/tsp.txt"));
        int n = in.nextInt();
        System.out.println( n + " 0");
        Map<Integer, Position> coordinates = new HashMap<>();
        for(int i = 1; i <= n; i++){
            Position p = new Position(in.nextDouble(), in.nextDouble());
            coordinates.put(i, p);
//            System.out.printf("%.0f, %.0f\n",p.x, p.y);
        }
        for(int i = 1; i < n; i++){
            for(int j = i + 1; j <= n; j++){
                System.out.printf("%d %d %.0f\n", i, j, distance(coordinates, i, j) * 10000);
            }
        }

        printOrded(n, coordinates);
    }

    private static void printOrded(int n, Map<Integer, Position> coordinates) {
        List<Integer> distances = new ArrayList<>();
        Map<Integer, PointSet> map = new TreeMap<>();
        for(int i = 1; i < n; i++){
            for(int j = i + 1; j <= n; j++){
                int d = (int)distance(coordinates, i, j);
                distances.add(d);
                map.put(d, new PointSet(i,j));
            }
        }

        System.out.println();
        for(Map.Entry entry : map.entrySet()){
            System.out.println(entry);
        }
    }

    private static double distance(Map<Integer, Position> coordinates, int i, int j) {
        Position p1 = coordinates.get(i);
        Position p2 = coordinates.get(j);
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}

class PointSet{
    int p1;
    int p2;

    public PointSet(int p1, int p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public String toString() {
        return "PointSet{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }
}
class Position{
    double x;
    double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }


}