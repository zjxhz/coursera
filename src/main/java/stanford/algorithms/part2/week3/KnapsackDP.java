package stanford.algorithms.part2.week3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huanze on 8/31/2016.
 */
public class KnapsackDP {
    private static int topDownCount = 0;
    private static int bottomUpCount = 0;
    private static Map<Solution, Integer> cache = new HashMap<>();
    public static void main(String[] args) {
        Knapsack knapsack = KnapsackFactory.createKnapsack("testdata/stanford/algorithms/part2/week3/knapsack_big.txt");
        System.out.println(findOptimalTopDown(knapsack));
//        System.out.println(findOptimalBottomUp(knapsack));
    }

    private static int findOptimalBottomUp(Knapsack knapsack) {
        int n = knapsack.getItems().size();
        int w = knapsack.getCapacity();
        int lightest = knapsack.getLightestWeight();
        int [][] A = new int[n][w + 1];
        for(int i = 0; i < n; i++){
            for(int x = lightest; x <= w; x++){
                bottomUpCount++;
                if(x == 0){
                    A[i][x] = 0;
                    continue;
                }
                int Wi = knapsack.getItems().get(i).weight;
                int Vi = knapsack.getItems().get(i).value;
                if(i == 0){ //includes only the 0th item
                    if(x >= Wi){
                        A[i][x] = Vi;
                    }
                    continue;
                }

                if(Wi > x){//not possible to include ith item as its weight is too large
                    A[i][x] = A[i - 1][x];
                } else {
                    A[i][x] = Math.max( A[i - 1][x], A[i-1][x-Wi] + Vi);
                }
            }
        }
        System.out.println("bottomUpCount: " + bottomUpCount);
        return A[n-1][w-1];
    }

    private static int findOptimalTopDown(Knapsack knapsack) {
        int n = knapsack.getItems().size();
        int w = knapsack.getCapacity();
        int optimal = findOptimalTopDown(knapsack, n, w);
        System.out.println("topDownCount: " + topDownCount);
        return optimal;
    }

    private static int findOptimalTopDown(Knapsack knapsack, int n, int w) {
        topDownCount++;
        if(n == 0 || w <= 0){
            return 0;
        }
        Solution solution = new Solution(n, w);
        if(cache.get(solution) != null){
            return cache.get(solution);
        }
        Item last = knapsack.getItems().get(n - 1);
        int withoutLast = findOptimalTopDown(knapsack, n - 1, w);
        int withLast = w - last.weight > 0 ? findOptimalTopDown(knapsack, n - 1, w - last.weight) + last.value : -1;
        int optimal = Math.max(withoutLast, withLast);
        cache.put(solution, optimal);
        if(w % 10000 == 0){
//            System.out.printf("optimal for %d items with weight %d: %d\n", n, w, optimal);
        }
        return optimal;
    }
}

class Solution{
    int items;
    int size;

    public Solution(int items, int size) {
        this.items = items;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Solution solution = (Solution) o;

        if (items != solution.items) return false;
        return size == solution.size;

    }

    @Override
    public int hashCode() {
        int result = items;
        result = 31 * result + size;
        return result;
    }
}