package stanford.algorithms.week5.tests;

import org.junit.Ignore;
import org.junit.Test;
import stanford.algorithms.week5.Graph;
import stanford.algorithms.week5.GraphFactory;
import stanford.algorithms.week5.ShortestDistanceFinder;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by wayne on 8/7/16.
 */
public class TestShortestDistance {
    @Test
    public void testSimple(){
        Graph graph = GraphFactory.createGraph("testdata/week5/simple0.txt");
        String shortestDistances = ShortestDistanceFinder.find(graph, 1);
        assertEquals("0,1,5", shortestDistances);
    }

    @Test
    public void test1(){
        Graph graph = GraphFactory.createGraph("testdata/week5/data1.txt");
        String shortestDistances = ShortestDistanceFinder.find(graph, 1);
        assertEquals("0,1,2,3,4,4,3,2", shortestDistances);
    }

}