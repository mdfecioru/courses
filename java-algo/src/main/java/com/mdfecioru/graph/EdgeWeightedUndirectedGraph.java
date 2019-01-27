package com.mdfecioru.graph;

import com.mdfecioru.heap.Heap;
import com.mdfecioru.utils.Constants;

import java.util.ArrayList;
import java.util.LinkedList;

public class EdgeWeightedUndirectedGraph {

    private int nr_nodes;
    private ArrayList<LinkedList<Edge>> adj;

    public EdgeWeightedUndirectedGraph(int nr_nodes) {
        this.nr_nodes = nr_nodes;
        adj = new ArrayList<>(nr_nodes);
        for (int i = 0; i < nr_nodes; i++) {
            LinkedList<Edge> l = new LinkedList<>();
            adj.add(i, l);
        }
    }

    public void addEdge(Edge e) {
        adj.get(e.getTail()).add(new Edge(e.getTail(), e.getHead(), e.getWeight()));
        adj.get(e.getHead()).add(new Edge(e.getHead(), e.getTail(), e.getWeight()));
    }

    private Edge findCheapestEDGE(LinkedList<Integer> processedNodes) {
        Edge minEdge = null;
        int minVal = Constants.INFINITY;

        for (int i = 0; i < nr_nodes; i++) {
            for (Edge e : adj.get(i)) {
                if (processedNodes.contains(e.getTail()) &&
                        (!processedNodes.contains(e.getHead())) &&
                        (e.getWeight() < minVal)) {
                    minEdge = e;
                    minVal = e.getWeight();
                }
            }
        }
        return minEdge;
    }

    private long computeMTSValue(LinkedList<Edge> mst) {
        long mtsValue = 0;
        for (Edge e : mst) {
            mtsValue += e.getWeight();
        }

        return mtsValue;
    }

    public long getPrimMST() {
        LinkedList<Integer> processedNodes = new LinkedList<>();
        LinkedList<Edge> mst = new LinkedList<>();

        // Start node is the one with index 0
        processedNodes.add(0);

        while (processedNodes.size() < nr_nodes) {
            Edge e = findCheapestEDGE(processedNodes);
            processedNodes.add(e.getHead());
            mst.add(e);
        }

        return computeMTSValue(mst);
    }

    public long getPrimMSTOptimized() {
        Heap<Node> minHeap = new Heap<>(nr_nodes);
        ArrayList<Node> nodeArray = new ArrayList<>(nr_nodes);
        long mstValue = 0;

        for (int i = 0; i < nr_nodes; i++) {
            Node n = new Node(i, Constants.INFINITY);
            minHeap.add(n);
            nodeArray.add(i, n);
        }

        nodeArray.get(0).setValue(0);
        minHeap.remove(nodeArray.get(0));
        for (Edge e : adj.get(0)) {
            Node n = nodeArray.get(e.getHead());
            minHeap.remove(n);
            n.setValue(e.getWeight());
            minHeap.add(n);
        }

        while (minHeap.size() > 0) {
            Node w = minHeap.poll();
            mstValue += w.getValue();

            for (Edge e : adj.get(w.getId())) {
                Node v = nodeArray.get(e.getHead());
                if (minHeap.contains(v) && (v.getValue() > e.getWeight())) {
                    minHeap.remove(v);
                    v.setValue(e.getWeight());
                    minHeap.add(v);
                }
            }
        }

        return mstValue;
    }

}
