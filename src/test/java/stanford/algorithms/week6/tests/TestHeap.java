package stanford.algorithms.week6.tests;

import org.junit.Test;
import stanford.algorithms.week6.Heap;

import static org.junit.Assert.assertEquals;

/**
 * Created by huanze on 8/11/2016.
 */
public class TestHeap {
    @Test
    public void testInsert(){
        Heap heap = new Heap();
        heap.insert(1);
        assertEquals(1, heap.size());
    }

    @Test
    public void testExactMinSimple(){
        Heap heap = new Heap();
        heap.insert(1);
        assertEquals(1, heap.exact());
        assertEquals(0, heap.size());
    }

    @Test
    public void testExactMin(){
        Heap heap = new Heap();
        heap.insert(3);
        heap.insert(1);
        heap.insert(2);
        assertEquals(1, heap.exact());
        assertEquals(2, heap.exact());
        assertEquals(3, heap.exact());
        assertEquals(0, heap.size());
    }

    @Test
    public void testExactMax(){
        Heap heap = new Heap((i1, i2) -> i2 - i1);
        heap.insert(3);
        heap.insert(1);
        heap.insert(2);
        assertEquals(3, heap.exact());
        assertEquals(2, heap.exact());
        assertEquals(1, heap.exact());
        assertEquals(0, heap.size());
    }
}
