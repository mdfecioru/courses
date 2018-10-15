package com.mdfecioru.algos;

import com.mdfecioru.heap.Heap;

import java.util.Comparator;

public class MedianMaintenance {

    private Heap<Integer> maxHeap; /* Heap for elements smaller than the Median */
    private Heap<Integer> minHeap; /* Heap for elements bigger than the Median  */

    public MedianMaintenance() {
        this.minHeap = new Heap<>(10);
        this.maxHeap = new Heap<>(10, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0 - o1.compareTo(o2);
            }
        });
    }

    public int runMedianMaintenance(int nextNnumber) {
        if (maxHeap.size() == 0) {
            maxHeap.add(nextNnumber);
            return nextNnumber;
        }

        int maxFromMaxHeap = maxHeap.peak();
        if (nextNnumber < maxFromMaxHeap) maxHeap.add(nextNnumber);
        else minHeap.add(nextNnumber);

        if (maxHeap.size() == minHeap.size() + 2) {
            minHeap.add(maxHeap.poll());
        }

        if (minHeap.size() == maxHeap.size() + 1) {
            maxHeap.add(minHeap.poll());
        }

        return maxHeap.peak();
    }
}
