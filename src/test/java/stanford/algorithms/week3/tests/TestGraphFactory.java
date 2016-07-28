package stanford.algorithms.week3.tests;

import org.junit.Test;
import stanford.algorithms.week3.Graph;
import stanford.algorithms.week3.GraphFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by huanze on 7/28/2016.
 */
public class TestGraphFactory {
    @Test
    public void teatAddingVertices(){
        Graph g = GraphFactory.createGraph("testdata/week3/data0.txt");
        assertEquals(4, g.getVerticeCount());
    }

    @Test
    public void testAddingEdges(){
        Graph g = GraphFactory.createGraph("testdata/week3/data0.txt");
        assertEquals(3, g.getEdgeCount());
    }

    @Test
    public void testConnectivity(){

    }
}
