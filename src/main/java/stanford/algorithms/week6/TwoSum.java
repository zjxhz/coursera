package stanford.algorithms.week6;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by wayne on 8/10/16.
 */
public class TwoSum {

    public static int find(Set<Long> set, int from, int to) {
        int count = 0;
        Set<Long> used = new HashSet<>();
        Set<Integer> sums = new HashSet<>();
        Set<Integer> usedSums = new HashSet<>();
        for(int i = from; i <= to; i++){
            sums.add(i);
        }
        for(long i : set){
            if(used.size() % 10000 == 0){
                System.out.println("used size:" + used.size() + ", sum size: " + sums.size());
            }
            for(int j : sums){
                long k = j - i;
                if(k != i && set.contains(k) && !used.contains(k)){
//                    System.out.println(i + " " + k);
                    usedSums.add(j);
                    count++;
                }
            }
            sums.removeAll(usedSums);
            used.add(i);
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(ClassLoader.getSystemResourceAsStream("testdata/week6/1m.txt"));//427
        Set<Long> set = new HashSet<>();
        while(in.hasNext()){
            set.add(in.nextLong());
        }
        System.out.println("Loaded data: " + set.size());
        System.out.println(find(set, -10000, 10000));
    }
}
