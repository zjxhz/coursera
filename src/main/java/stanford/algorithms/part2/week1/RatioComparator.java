package stanford.algorithms.part2.week1;

import java.util.Comparator;

/**
 * Created by wayne on 8/31/16.
 */
public class RatioComparator implements Comparator<Job>  {
    @Override
    public int compare(Job o1, Job o2) {
        double temp = ratio(o2) - ratio(o1);
        if (temp == 0){
            return 0;
        }
        return temp > 0 ? 1 : -1;
    }

    private double ratio(Job job){
        return job.weight * 1.0 / job.length;
    }
}
