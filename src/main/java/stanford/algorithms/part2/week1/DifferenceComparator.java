package stanford.algorithms.part2.week1;

import jdk.nashorn.internal.scripts.JO;

import java.util.Comparator;

/**
 * Created by wayne on 8/31/16.
 */
public class DifferenceComparator implements Comparator<Job> {

    @Override
    public int compare(Job o1, Job o2) {
        int diff1 = o1.weight - o1.length;
        int diff2 = o2.weight - o2.length;
        if (diff1 == diff2) {
            return o2.weight - o1.weight;
        }

        return diff2 - diff1;

    }
}
