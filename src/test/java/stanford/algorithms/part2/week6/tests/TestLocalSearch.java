package stanford.algorithms.part2.week6.tests;

import org.junit.Test;
import stanford.algorithms.part2.week6.TwoSetCondition;
import stanford.algorithms.part2.week6.TwoSetsBacktrack;
import stanford.algorithms.part2.week6.TwoSetsLocalSearch;

import static org.junit.Assert.assertEquals;

/**
 * Created by wayne on 10/14/16.
 */
public class TestLocalSearch {
    @Test
    public void test2SAT() {
        check("2sat0s", true);
        check("2sat0u", false);
        check("2sat1s", true);
        check("2sat1u", true);
    }

    private void check(String resource, boolean expected) {
        TwoSetCondition condition = TwoSetCondition.createReducedConditions("testdata/stanford/algorithms/part2/week6/" + resource + ".txt");
        TwoSetsLocalSearch backtrack = new TwoSetsLocalSearch(condition);
        boolean satisfiable = backtrack.eval();
        assertEquals(satisfiable, expected);
    }
}
