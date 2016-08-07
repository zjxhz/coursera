package stanford.algorithms.week5.tests;

import org.junit.Test;
import stanford.algorithms.week5.Graph;
import stanford.algorithms.week5.GraphFactory;
import stanford.algorithms.week5.Vertice;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by wayne on 8/7/16.
 */
public class TestGraphFactory {
    @Test
    public void testCreateGraph(){
        Graph g = GraphFactory.createGraph("testdata/week5/simple0.txt");
        Vertice v1 = g.getVertice(1);
        Vertice v2 = g.getVertice(2);
        Vertice v3 = g.getVertice(3);
        assertEquals(1, v1.getDistance(v2));
        assertEquals(5, v2.getDistance(v3));
        assertEquals(5, v1.getDistance(v3));
    }
}
