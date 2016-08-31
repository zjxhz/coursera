package stanford.algorithms.part2.week1;

import java.util.Collections;
import java.util.List;

/**
 * Created by wayne on 8/31/16.
 */
public class Greed2 {

    public static void main(String[] args) {
        List<Job> jobs = JobsReader.read("testdata/stanford/algorithms/part2/week1/jobs.txt");
        System.out.println(CompletionTimeCalculator.calc(jobs));
        Collections.sort(jobs, new RatioComparator());
        System.out.println(CompletionTimeCalculator.calc(jobs));
    }
}
