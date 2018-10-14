package com.mdfecioru.heap;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeapTest {

    @Test
    public void getHeapTest1() {
        Heap<Integer> heap = new Heap<>(10);

        heap.add(4);
        heap.add(10);
        heap.add(2);
        heap.add(7);
        heap.add(9);
        heap.add(6);
        heap.add(1);
        heap.add(3);
        heap.add(5);
        heap.add(8);

        heap.remove(3);
        heap.remove(6);

        assertEquals(1L, heap.poll().intValue());
        assertEquals(2L, heap.poll().intValue());
        assertEquals(3L, heap.poll().intValue());
        assertEquals(6L, heap.poll().intValue());
        assertEquals(7L, heap.poll().intValue());
        assertEquals(8L, heap.poll().intValue());
        assertEquals(9L, heap.poll().intValue());
        assertEquals(10L, heap.poll().intValue());

    }

}