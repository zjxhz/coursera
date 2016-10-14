package stanford.algorithms.part2.week6;

import java.util.*;

/**
 * Created by wayne on 10/13/16.
 */
public class DataReducer {
    public static void main(String[] args) {
        DataReducer reducer = new DataReducer("testdata/stanford/algorithms/part2/week6/2sat1.txt");
        reducer.reduce();
    }

    private List<Pair> pairs = new ArrayList<>();;

    public DataReducer(String resource){
        Scanner in = new Scanner(
                ClassLoader.getSystemResourceAsStream(resource));
        int n = in.nextInt();
        while(in.hasNext()){
            int k = in.nextInt();
            int j = in.nextInt();
            pairs.add(new Pair(k, j));
        }
    }
    public List<Pair> reduce(){
        System.out.println(pairs.size());
        while(reduce(pairs)){
            System.out.println(pairs.size());
        }

        Set<Integer> variables = new HashSet<>();
        for(Pair pair : pairs){
            variables.add(Math.abs(pair.k));
            variables.add(Math.abs(pair.j));
        }
        List<Integer> sorted = new ArrayList(variables);
        Collections.sort(sorted);
        Map<Integer, Integer> map = new HashMap<>();
        int newVal = 1;
        for(int i : sorted){
            map.put(i, newVal++);
        }
        for(Pair pair : pairs){
            pair.k = newVal(pair.k, map);
            pair.j = newVal(pair.j, map);
        }
        System.out.println("variables: " + variables.size());

        return pairs;
    }

    private int newVal(int k, Map<Integer, Integer> map) {
        if(k > 0){
            return map.get(k);
        }
        return -map.get(-k);
    }

    private int mod(int k, int n){
        if(k > 0){
            return k % n + 1;
        } else {
            return k % n - 1;
        }
    }
    private static boolean reduce(List<Pair> pairs) {
        Map<Integer, Integer> map = new HashMap<>();
        for(Pair pair : pairs){
            updateCount(map, pair.k);
            updateCount(map, pair.j);
        }
        Set<Integer> singles = findSingles(map);
        return remove(pairs, singles);
    }

    private static boolean remove(List<Pair> pairs, Set<Integer> singles) {
        boolean removed = false;
        for(ListIterator<Pair> it = pairs.listIterator(); it.hasNext();){
            Pair pair = it.next();
            if(singles.contains(Math.abs(pair.k)) || singles.contains(Math.abs(pair.j))){
                it.remove();
                removed = true;
            }
        }
        return removed;
    }

    private static Set<Integer> findSingles(Map<Integer, Integer> map) {
        Set<Integer> set = new HashSet<>();
        for(int key : map.keySet()){
            if(map.get(key) == 1){
                set.add(key);
            }
        }
        return set;
    }

    private static void updateCount(Map<Integer, Integer> map, int k) {
        k = Math.abs(k);
        if(map.get(k) == null){
            map.put(k, 1);
        } else {
            map.put(k, map.get(k) + 1);
        }
    }
}


class Pair{
    int k;
    int j;

    public Pair(int k, int j) {
        this.k = k;
        this.j = j;
    }
}