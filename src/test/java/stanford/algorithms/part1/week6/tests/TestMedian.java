package stanford.algorithms.part1.week6.tests;

import org.junit.Test;
import stanford.algorithms.part1.week6.MedianFinder;

import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by wayne on 8/11/16.
 */
public class TestMedian {
    @Test
    public void test1(){
        MedianFinder mf = new MedianFinder();
        assertEquals(1, mf.offer(1));
        assertEquals(1, mf.offer(2));
        assertEquals(2, mf.offer(3));

    }

    @Test
    public void test2(){
        Scanner in = new Scanner(ClassLoader.getSystemResourceAsStream("testdata/stanford/algorithms/part1/week6/pa2.txt"));
        int sum = 0;
        MedianFinder mf = new MedianFinder();
        while(in.hasNext()){
            int m = mf.offer(in.nextInt());
            System.out.println(m);
            sum += m;
        }
        assertEquals(9335, sum % 10000);
    }

}
