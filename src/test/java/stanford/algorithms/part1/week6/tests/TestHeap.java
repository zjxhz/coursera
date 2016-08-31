package stanford.algorithms.part1.week6.tests;

import org.junit.Test;
import stanford.algorithms.part1.week6.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by huanze on 8/11/2016.
 */
public class TestHeap {
    @Test
    public void testInsert(){
        Heap heap = Heap.createMinHeap();
        heap.insert(1);
        assertEquals(1, heap.size());
    }

    @Test
    public void testExactMinSimple(){
        Heap heap = Heap.createMinHeap();
        heap.insert(1);
        assertEquals(1, heap.exact());
        assertEquals(0, heap.size());
    }

    @Test
    public void testExactMin(){
        Heap heap = Heap.createMinHeap();
        heap.insert(3);
        heap.insert(1);
        heap.insert(2);
        assertEquals(1, heap.exact());
        assertEquals(2, heap.exact());
        assertEquals(3, heap.exact());
        assertEquals(0, heap.size());
    }

    @Test
    public void testExactMinRandom(){
        Heap heap = Heap.createMinHeap();
        List<Integer> input = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            input.add(i);
        }

        Collections.shuffle(input);
        input.forEach(heap::insert);
        for(int i = 0; i < 100; i++){
            assertEquals(i, heap.exact());
        }
    }

    @Test
    public void testExactMax(){
        Heap heap = Heap.createMaxHeap();
        heap.insert(2);
        heap.insert(1);
        heap.insert(3);
        assertEquals(3, heap.exact());
        assertEquals(2, heap.exact());
        assertEquals(1, heap.exact());
        assertEquals(0, heap.size());
    }

    @Test
    public void testExactMaxRandom(){
        Heap heap = Heap.createMaxHeap();
        List<Integer> input = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            input.add(i);
        }

        Collections.shuffle(input);
        input.forEach(heap::insert);
        for(int i = 99; i >= 0; i--){
            assertEquals(i, heap.exact());
        }
    }
}
