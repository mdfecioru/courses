package com.mdfecioru.graph;

public class Edge implements Comparable<Edge> {

    private final int tail, head;
    private final int weight;

    public Edge(int tail, int head, int weight) {
        this.tail = tail;
        this.head = head;
        this.weight = weight;
    }

    public int getTail() {
        return this.tail;
    }

    public int getHead() {
        return this.head;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight < o.weight) return -1;
        if (this.weight > o.weight) return 1;
        return 0;
    }
}
