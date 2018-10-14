package com.mdfecioru.heap;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<E> {

    private final Comparator<? super E> comparator;
    private int size;
    private final ArrayList<E> arr;

    public Heap(int initialCapacity, Comparator<? super E> comparator) {
        this.comparator = comparator;
        arr = new ArrayList<>(initialCapacity);
        size = 0;
    }

    public Heap(int initialCapacity) {
        this(initialCapacity, null);
    }

    public int size() {
        return size;
    }

    private int compareElem(E e1, E e2) {
        if (this.comparator != null) return this.comparator.compare(e1, e2);
        else {
            Comparable<? super E> el1 = (Comparable<? super E>) e1;
            return el1.compareTo(e2);
        }
    }

    private int getParentIndex(int index) {
        if (index == 0) return -1;
        return (index-1) / 2;
    }

    private int getChildLeftIndex(int index) {
        if (index * 2 + 1 >= size) return -1;
        return index * 2 + 1;
    }

    private int getChildRightIndex(int index) {
        if (index * 2 + 2 >= size) return -1;
        return index * 2 + 2;
    }

    private void switchElem(int index1, int index2) {
        E aux = arr.get(index1);
        arr.set(index1, arr.get(index2));
        arr.set(index2, aux);
    }

    private void bubbleUp(int index) {

        if (index >= size) return;
        int parentIndex = getParentIndex(index);
        if (parentIndex == -1) return;
        if (compareElem(arr.get(index), arr.get(parentIndex)) > 0) return;
        switchElem(index, parentIndex);
        bubbleUp(parentIndex);
    }

    private void bubbleDown(int index) {
        if (index >= size) return;
        int leftChildIndex = getChildLeftIndex(index);
        int rightChildIndex = getChildRightIndex(index);
        int minChild = leftChildIndex;

        if (leftChildIndex == -1) return;
        if ( (rightChildIndex != -1) && (compareElem(arr.get(leftChildIndex), arr.get(rightChildIndex)) > 0) )
            minChild = rightChildIndex;
        if (compareElem(arr.get(index), arr.get(minChild)) <= 0) return;
        switchElem(index, minChild);
        bubbleDown(minChild);
    }

    public void add(E e) {
        arr.add(size, e);
        size++;
        bubbleUp(size-1);
    }

    public E poll() {
        if (size == 0) return null;
        switchElem(0, size-1);
        E elem = arr.remove(size-1);
        size--;
        bubbleDown(0);
        return elem;
    }

    public E remove(int index) {
        if (size == 0) return null;
        switchElem(index, size-1);
        E elem = arr.remove(size-1);
        size--;
        bubbleUp(index);
        bubbleDown(index);
        return elem;
    }

    public int find(E e) {
        int index = 0;

        for (E elem: arr) {
            if (e.equals(elem)) return index;
            index++;
        }

        return -1;
    }

    public boolean contains(E e) {
        if (find(e) == -1) return false;
        return true;
    }

    public E remove(E e) {
        int index = find(e);
        if (index == -1) return null;
        return remove(index);
    }
}
