package stanford.algorithms.part1.week5.tests;

import org.junit.Test;
import stanford.algorithms.part1.week5.Graph;
import stanford.algorithms.part1.week5.Vertice;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by wayne on 8/7/16.
 */
public class TestGraph {
    @Test
    public void testAddVertice(){
        Graph graph = new Graph();
        Vertice v = graph.addVertice(1);
        assertNotNull(v);
        assertEquals(v, graph.getVertice(1));
    }

    @Test
    public void testSize(){
        Graph graph = new Graph();
        graph.addVertice(1);
        graph.addVertice(2);
        assertEquals(2, graph.size());
        graph.addVertice(2);
        assertEquals(2, graph.size());
    }


}
