package stanford.algorithms.part1.week3.tests;

import org.junit.Test;
import stanford.algorithms.part1.week3.Graph;
import stanford.algorithms.part1.week3.GraphFactory;
import stanford.algorithms.part1.week3.Vertice;

import static org.junit.Assert.assertEquals;

/**
 * Created by huanze on 7/28/2016.
 */
public class TestGraphFactory {
    /*
            data0.text
                1
                |
                4
               / \
              2   3
    */

    @Test
    public void teatAddingVertices(){
        Graph g = GraphFactory.createGraph("testdata/stanford/algorithms/part1/week3/data0.txt");
        assertEquals(4, g.getVerticeCount());
    }

    @Test
    public void testEdgeCount(){
        Graph g = GraphFactory.createGraph("testdata/stanford/algorithms/part1/week3/data0.txt");
        Vertice v1 = g.findVertice(1);
        Vertice v2 = g.findVertice(2);
        Vertice v3 = g.findVertice(3);
        Vertice v4 = g.findVertice(4);
        assertEquals(1, v1.getEdgeCountTo(v4));
        assertEquals(1, v2.getEdgeCountTo(v4));
        assertEquals(1, v3.getEdgeCountTo(v4));

        assertEquals(1, v4.getEdgeCountTo(v1));
        assertEquals(1, v4.getEdgeCountTo(v2));
        assertEquals(1, v4.getEdgeCountTo(v3));
    }
}
