package stanford.algorithms.part1.week4.tests;

import org.junit.Test;
import stanford.algorithms.part1.week4.Graph;
import stanford.algorithms.part1.week4.Vertice;
import stanford.algorithms.part1.week4.GraphFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by huanze on 8/5/2016.
 */
public class TestGraphFactory {
    @Test
    public void testCreatingVertices(){
        Graph graph = GraphFactory.createGraph("testdata/stanford/algorithms/part1/week4/singleOutgoingArc.txt");
        Vertice v1 = graph.getVertice(1);
        Vertice v2 = graph.getVertice(2);
        Vertice v3 = graph.getVertice(3);
        assertNotNull(v1);
        assertNotNull(v2);
        assertNotNull(v3);
        assertTrue(v1.hasArcTo(v2));
        assertTrue(v2.hasArcTo(v3));
        assertTrue(v3.hasArcTo(v1));
    }
}
