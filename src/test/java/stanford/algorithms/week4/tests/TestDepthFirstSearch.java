package stanford.algorithms.week4.tests;

import org.junit.Test;
import stanford.algorithms.week4.DepthFirstSearch;
import stanford.algorithms.week4.Graph;
import stanford.algorithms.week4.GraphFactory;
import stanford.algorithms.week4.Vertice;

import static org.junit.Assert.assertEquals;

/**
 * Created by huanze on 8/5/2016.
 */
public class TestDepthFirstSearch {
    DepthFirstSearch dfs = new DepthFirstSearch();

    @Test
    public void testSingleOutgoingArc(){
        Graph graph = GraphFactory.createGraph("testdata/week4/singleOutgoingArc.txt");
        assertEquals("1,2,3", dfs.search(graph));
    }

    @Test
    public void testMultipleOutgoingArcs(){
        Graph graph = GraphFactory.createGraph("testdata/week4/multipleOutgoingArcs.txt");
        assertEquals("1,2,3,4", dfs.search(graph));
    }

    @Test
    public void testDFS1(){
        Graph graph = GraphFactory.createGraph("testdata/week4/data1.txt");
        assertEquals("1,4,7,2,8,5,6,9,3", dfs.search(graph));
        int [] completionTimes = {3,9,5,2,4,7,1,8,6};
        int id = 1;
        for(int completionTime : completionTimes){
            assertEquals(completionTime, graph.getVertice(id++).getCompletionTime());
        }
    }

    @Test
    public void testScc1(){
        Graph graph = GraphFactory.createGraph("testdata/week4/data1.txt");
        assertEquals("3,3,3,0,0", dfs.computeMaxSCCs(graph));
    }

    @Test
    public void testScc2(){
        Graph graph = GraphFactory.createGraph("testdata/week4/data2.txt");
        assertEquals("3,3,2,0,0", dfs.computeMaxSCCs(graph));
    }

    @Test
    public void testScc(){
        Graph graph = GraphFactory.createGraph("testdata/week4/scc.txt");
        assertEquals("3,3,2,0,0", dfs.computeMaxSCCs(graph));
    }

}
