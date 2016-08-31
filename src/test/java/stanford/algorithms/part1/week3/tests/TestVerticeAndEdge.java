package stanford.algorithms.part1.week3.tests;

import org.junit.Test;
import stanford.algorithms.part1.week3.Vertice;

import static org.junit.Assert.*;

/**
 * Created by huanze on 7/28/2016.
 */
public class TestVerticeAndEdge {
    @Test
    public void testEquality(){
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(1);
        Vertice v3 = new Vertice(3);
        assertEquals(v1, v2);
        assertNotEquals(v1, v3);
    }

    @Test
    public void testHashCode(){
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(1);
        Vertice v3 = new Vertice(3);
        assertEquals(v1.hashCode(), v2.hashCode());
        assertNotEquals(v1.hashCode(), v3.hashCode());
    }

    @Test
    public void testAddingEdge(){
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        assertEquals(0, v1.getEdgeCountTo(v2));
        v1.addEdgeTo(v2);
        assertEquals(1, v1.getEdgeCountTo(v2));
        assertEquals(1, v2.getEdgeCountTo(v1));
    }

    @Test
    public void testAddingSelfLoopEdge(){
        Vertice v1 = new Vertice(1);
        v1.addEdgeTo(v1);
        assertEquals(0, v1.getEdgeCountTo(v1));
    }

    @Test
    public void testAddingParallelEdges(){
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        v1.addEdgeTo(v2);
        v1.addEdgeTo(v2);
        assertEquals(2, v1.getEdgeCountTo(v2));
        assertEquals(2, v2.getEdgeCountTo(v1));
    }

    @Test
    public void testRemovingEdge(){
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        v1.addEdgeTo(v2);
        v1.removeEdgeTo(v2);
        assertEquals(0, v1.getEdgeCountTo(v2));
    }

    @Test
    public void testRemovingParallelEdges(){
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        v1.addEdgeTo(v2);
        v1.addEdgeTo(v2);
        v1.removeEdgeTo(v2);
        assertEquals(1, v1.getEdgeCountTo(v2));
        v1.removeEdgeTo(v2);
        assertEquals(0, v1.getEdgeCountTo(v2));
    }

    @Test
    public void testContractingEdge(){
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        v1.addEdgeTo(v2);
        v1.contractEdge(v2);
        assertEquals(0, v1.getEdgeCountTo(v2));
        assertEquals(0, v2.getEdgeCountTo(v1));
    }

    @Test
    public void testContractingParallelEdges(){
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        v1.addEdgeTo(v2);
        v1.addEdgeTo(v2);
        v1.contractEdge(v2);
        assertEquals("Self looping edges should be deleted", 0, v1.getEdgeCountTo(v2));
        assertEquals("Self looping edges should be deleted", 0, v2.getEdgeCountTo(v1));
    }

    @Test
    public void testFusingEdges(){
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);
        v1.addEdgeTo(v2);
        v2.addEdgeTo(v3);
        assertEquals(0, v1.getEdgeCountTo(v3));
        v1.contractEdge(v2);
        assertEquals(1, v1.getEdgeCountTo(v3));
    }

    @Test
    public void testFusingEdges2(){
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);
        Vertice v4 = new Vertice(4);
        v1.addEdgeTo(v2);
        v1.addEdgeTo(v3);
        v2.addEdgeTo(v3);
        v2.addEdgeTo(v4);
        v3.addEdgeTo(v4);
        v1.contractEdge(v2);
        v1.contractEdge(v3);

        assertEquals(2, v1.getEdgeCountTo(v4));
    }

}
