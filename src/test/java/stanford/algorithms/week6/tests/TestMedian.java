package stanford.algorithms.week6.tests;

import org.junit.Test;
import stanford.algorithms.week6.MedianFinder;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by wayne on 8/11/16.
 */
public class TestMedian {
    @Test
    public void test1(){
        int [] arr = {1,2,3};
        assertEquals(4, MedianFinder.find(arr));//1,1,2
    }

    @Test
    public void test2(){
        int [] arr = {1,2,3};//pa2.txt -> 9335
        assertEquals(4, MedianFinder.find(arr));//1,1,2
    }

}
