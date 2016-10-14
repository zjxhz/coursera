package stanford.algorithms.part2.week6.tests;

import org.junit.Test;
import stanford.algorithms.part2.week6.TwoSetCondition;
import stanford.algorithms.part2.week6.TwoSetsBacktrack;

import static org.junit.Assert.assertEquals;

/**
 * Created by wayne on 10/13/16.
 */
public class TestBacktrack {
    @Test
    public void test2SAT() {
        check("2sat0s", true);
        check("2sat0u", false);
        check("2sat1s", true);
        check("2sat1u", true);
    }

    private void check(String resource, boolean expected) {
        int count = 100;//for a random algorithm, test multiple times to see if it is always true
        for (int i = 0; i < count; i++) {
            TwoSetCondition condition = new TwoSetCondition("testdata/stanford/algorithms/part2/week6/" + resource + ".txt");
            TwoSetsBacktrack backtrack = new TwoSetsBacktrack(condition);
            boolean satisfiable = backtrack.eval();
            assertEquals(satisfiable, expected);
        }
    }
}
