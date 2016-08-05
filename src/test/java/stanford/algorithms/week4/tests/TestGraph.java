package stanford.algorithms.week4.tests;

import org.junit.Test;
import stanford.algorithms.week4.Graph;
import stanford.algorithms.week4.GraphFactory;
import stanford.algorithms.week4.Vertice;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by wayne on 8/6/16.
 */
public class TestGraph {

    @Test
    public void testSize(){
        Graph graph = new Graph();
        assertEquals(0, graph.size());
        graph.addVertice(1);
        assertEquals(1, graph.size());
    }
    @Test
    public void testReverseArcs() {
        Graph graph = GraphFactory.createGraph("testdata/week4/singleOutgoingArc.txt");
        Vertice v1 = graph.getVertice(1);
        Vertice v2 = graph.getVertice(2);
        Vertice v3 = graph.getVertice(3);
        v1.setCompletionTime(3);
        v2.setCompletionTime(2);
        v3.setCompletionTime(1);
        graph = graph.reverse();
        v1 = graph.getVertice(1);
        v2 = graph.getVertice(2);
        v3 = graph.getVertice(3);
        assertFalse(v1.hasArcTo(v3));
        assertFalse(v3.hasArcTo(v2));
        assertFalse(v2.hasArcTo(v1));
        assertTrue(v1.hasArcTo(v2));
        assertTrue(v2.hasArcTo(v3));
        assertTrue(v3.hasArcTo(v1));
    }
}
