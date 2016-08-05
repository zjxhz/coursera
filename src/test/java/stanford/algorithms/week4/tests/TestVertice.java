package stanford.algorithms.week4.tests;

import org.junit.Test;
import stanford.algorithms.week4.Vertice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by huanze on 8/5/2016.
 */
public class TestVertice {
    @Test
    public void notVisitedByDefault(){
        Vertice v = new Vertice(1);
        assertFalse(v.isExplored());
    }

    @Test
    public void hasNoConnectionByDefault(){
        Vertice v = new Vertice(1);
        assertTrue(v.nextVertices().isEmpty());
    }

    @Test
    public void addEdges(){
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        v1.addEdgeTo(v2);
        assertTrue(v1.nextVertices().contains(v2));
        Vertice v3 = new Vertice(3);
        v1.addEdgeTo(v3);
        assertTrue(v1.nextVertices().contains(v3));
    }
}
