package stanford.algorithms.part1.week6.tests;

import org.junit.Test;
import stanford.algorithms.part1.week6.TwoSum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by wayne on 8/10/16.
 */
public class TestTwoSum {
    @Test
    public void test1(){
        long [] arr = {1, 2, 3, 4};

        Set set = new HashSet<>(Arrays.asList(arr));
        assertEquals(4L, TwoSum.find(set, 3, 5));//1+2, 1+3, 1+4, 2+3
    }
}