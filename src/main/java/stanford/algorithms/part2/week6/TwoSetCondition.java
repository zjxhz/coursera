package stanford.algorithms.part2.week6;

import java.util.*;

/**
 * Created by wayne on 10/11/16.
 */
public class TwoSetCondition {
    private TwoSet[] twoSets;
    private Map<Integer, List<Integer>> positionMap = new HashMap<>();
    private boolean[] variables;
    private int failedIndex;
    private int flippedIndex = 0;
    private Map<Integer, Integer> flipCounts = new HashMap<>();
    //todo flipped count for a single variable shouldn't be too big

    public TwoSetCondition(TwoSet[] twoSets) {
        this.twoSets = twoSets;
        initMap();
        variables = initTwoSets(twoSets.length);
    }

    private void initMap() {
        for(int i = 0; i < twoSets.length; i++){
            TwoSet twoSet = twoSets[i];
            updateMap(twoSet.one);
            updateMap(twoSet.two);
        }
    }

    private void updateMap(int i) {
        int index = Math.abs(i) - 1;
        List<Integer> positions = positionMap.get(index);
        if(positions == null){
            positions = new LinkedList<>();
            positionMap.put(index, positions);
        }
        positions.add(index);
    }

    private boolean [] initTwoSets(int n){
        boolean[] twoSets = new boolean[n];
        for(int i = 0; i < n; i++){
            if(Math.random() > 0.5){
                twoSets[i] = true;
            }
        }
        return twoSets;
    }

    public static TwoSetCondition createReducedConditions(String resource){
        DataReducer reducer = new DataReducer(resource);
        List<Pair> pairs = reducer.reduce();
        TwoSet[] twoSets = new TwoSet[pairs.size()];
        for(int i = 0; i < pairs.size(); i++){
            twoSets[i] = new TwoSet(pairs.get(i).k, pairs.get(i).j);
        }
        return new TwoSetCondition(twoSets);
    }

    public TwoSetCondition(String resource){
        Scanner in = new Scanner(ClassLoader.getSystemResourceAsStream(resource));
        int n = in.nextInt();
        TwoSet[] twoSets = new TwoSet[n];
        for(int i = 0; i < n; i++){
            twoSets[i] = new TwoSet(in.nextInt(), in.nextInt());
        }
        this.twoSets = twoSets;
    }
    public int size() {
        return twoSets.length;
    }

    /**
     * Checks from random position and reports the first index where the check fails, or -1 if all satisfied.
     */
    public boolean met(boolean[] v/*todo delete this v*/) {
        if(!flippedMet()){
            return false;
        }
        int n = twoSets.length;
        int start = (int) (Math.random() * n);
        for (int i = 0; i < n; i++) {
            int j = i + start;
            TwoSet twoSet = twoSets[j % n];
            if (!twoSet.met(variables)) {
                failedIndex = j % n;
                return false;
            }
        }
        return true;
    }

    private boolean flippedMet() {
        for(int i : positionMap.get(flippedIndex)){
            TwoSet twoSet = twoSets[i];
            if (!twoSet.met(variables)) {
                failedIndex = i;
                return false;
            }
        }
        return true;
    }

    public void flip(boolean[] v){
        TwoSet set = twoSets[failedIndex];
        flippedIndex = set.flip(variables);
        updateCounts();
    }

    private void updateCounts() {
        Integer count = flipCounts.get(flippedIndex);
        if(count == null){
            flipCounts.put(flippedIndex, 1);
        } else {
            flipCounts.put(flippedIndex, count + 1);
        }
        if(count != null && count > 1000){
            System.out.printf("Warning: flipped counts of %d is too high: %d. \n", flippedIndex, count);
        }
    }

    public TwoSet get(int i) {
        return twoSets[i];
    }
}
