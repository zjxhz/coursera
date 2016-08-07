package stanford.algorithms.week5.tests;

import org.junit.Test;
import stanford.algorithms.week5.Vertice;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by wayne on 8/7/16.
 */
public class TestVertice {
    @Test
    public void testDistance(){
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        v1.addPath(v2, 3);
        assertEquals(3, v1.getDistance(v2));
    }

    @Test
    public void testOutgoingVertices(){
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);
        v1.addPath(v2, 3);
        v1.addPath(v3, 4);
        assertEquals(2, v1.getOutgoingVertices().size());
    }
}
