package stanford.algorithms.part1.week4.tests;

import org.junit.Test;
import stanford.algorithms.part1.week4.Vertice;

import static org.junit.Assert.assertEquals;
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
    public void addArcs(){
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        v1.addArcTo(v2);
        assertTrue(v1.nextVertices().contains(v2));
        Vertice v3 = new Vertice(3);
        v1.addArcTo(v3);
        assertTrue(v1.nextVertices().contains(v3));
    }

    @Test
    public void swapIdAndCompletionTime(){
        Vertice v1 = new Vertice(1);
        v1.setCompletionTime(2);
        v1.swapIdAndCompletionTime();
        assertEquals(2, v1.getId());
        assertEquals(1, v1.getCompletionTime());
    }

}
