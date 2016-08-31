package stanford.algorithms.part2.week1;

import java.util.List;

/**
 * Created by wayne on 8/31/16.
 */
public class CompletionTimeCalculator {
    public static long calc(List<Job> jobs){
        long sum = 0;
        int c = 0;
        for(Job job : jobs){
            c += job.length;
            sum += c * job.weight;
        }
        return sum;
    }
}
