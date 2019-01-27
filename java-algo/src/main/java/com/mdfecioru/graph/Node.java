package com.mdfecioru.graph;

public class Node implements Comparable<Node> {
    private int id;
    private int value;

    public Node(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Node o) {
        if (this.value < o.value) return -1;
        if (this.value > o.value) return 1;
        return 0;
    }
}
