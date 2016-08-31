package stanford.algorithms.part1.week3.tests;

import org.junit.Test;
import stanford.algorithms.part1.week3.Graph;
import stanford.algorithms.part1.week3.GraphFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by huanze on 7/29/2016.
 */
public class TestMinCut {
    private static int CONSTANT = 1;
    @Test
    public void testMinCut0(){
        assertEquals(2, getMinCut("testdata/stanford/algorithms/part1/week3/data1.txt"));
    }

    private int getMinCut(String resource){
        int min = Integer.MAX_VALUE;
        String input  = resource;

        Graph g = GraphFactory.createGraph(input);
        int rounds = g.getVerticeCount() * CONSTANT;
        for (int i = 0; i < rounds; i++){
            g = GraphFactory.createGraph(input);
            int cuts = g.findMinCut();
            if (cuts < min){
                min = cuts;
            }
        }
        return min;
    }

    @Test
    public void testMinCut(){
        assertEquals(17, getMinCut("testdata/stanford/algorithms/part1/week3/kargerMinCut.txt"));
    }
}
