package stanford.algorithms.week4.tests;

import org.junit.Test;
import stanford.algorithms.week4.DepthFirstSearch;
import stanford.algorithms.week4.Graph;
import stanford.algorithms.week4.GraphFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by huanze on 8/5/2016.
 */
public class TestDepthFirstSearch {
    DepthFirstSearch dfs = new DepthFirstSearch();

    @Test
    public void testSingleOutgoingArc(){
        Graph graph = GraphFactory.createGraph("testdata/week4/singleOutgoingArc.txt");
        assertEquals("1,2,3", dfs.search(graph, 3));
    }

    @Test
    public void testMultipleOutgoingArcs(){
        Graph graph = GraphFactory.createGraph("testdata/week4/multipleOutgoingArcs.txt");
        assertEquals("1,2,3,4", dfs.search(graph, 4));
    }

    @Test
    public void test1(){
        Graph graph = GraphFactory.createGraph("testdata/week4/data1.txt");
        assertEquals("1,4,7,2,8,5,6,9,3", dfs.search(graph, 9));
    }
}
