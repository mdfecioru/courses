package com.mdfecioru.graph;

import com.mdfecioru.heap.Heap;
import com.mdfecioru.utils.Constants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class EdgeWeightedDirectedGraph {

    private int nr_nodes;
    private ArrayList<LinkedList<Edge>> adj;

    public EdgeWeightedDirectedGraph(int nr_nodes) {
        this.nr_nodes = nr_nodes;
        adj = new ArrayList<>(nr_nodes);
        for (int i = 0; i < nr_nodes; i++) {
            LinkedList<Edge> l = new LinkedList<>();
            adj.add(i, l);
        }
    }

    public void addEdge(Edge e) {
        adj.get(e.getTail()).add(new Edge(e.getTail(), e.getHead(), e.getWeight()));
    }

    private Edge getNextEdge(LinkedList<Integer> processedNodes, ArrayList<Integer> minDist) {
        Edge nextEdge = null;
        int minVal = Constants.INFINITY;

        for (int i = 0; i < nr_nodes; i++) {
            for (Edge e : adj.get(i)) {
                if (processedNodes.contains(e.getTail()) &&
                        (!processedNodes.contains(e.getHead())) &&
                        ((minDist.get(e.getTail()) + e.getWeight()) < minVal)) {
                    nextEdge = e;
                    minVal = minDist.get(e.getTail()) + e.getWeight();
                }
            }
        }

        return nextEdge;
    }

    public ArrayList<Integer> getMinDistDijkstra(int start_node) {
        LinkedList<Integer> processedNodes = new LinkedList<>();
        ArrayList<Integer> minDist = new ArrayList<>(nr_nodes);

        for (int i = 0; i < nr_nodes; i++) {
            minDist.add(i, Constants.INFINITY);
        }

        processedNodes.add(start_node);
        minDist.set(start_node, 0);

        while (processedNodes.size() < nr_nodes) {
            Edge nextEdge = getNextEdge(processedNodes, minDist);
            processedNodes.add(nextEdge.getHead());
            minDist.set(nextEdge.getHead(), minDist.get(nextEdge.getTail()) + nextEdge.getWeight());
        }

        return minDist;
    }

    public ArrayList<Node> getMinDistDijkstraOptimized(int start_node) {
        Heap<Node> minHeap = new Heap(nr_nodes);
        ArrayList<Node> nodeArray = new ArrayList<>(nr_nodes);

        for (int i = 0; i < nr_nodes; i++) {
            Node n = new Node(i, Constants.INFINITY);
            minHeap.add(n);
            nodeArray.add(i, n);
        }

        nodeArray.get(start_node).setValue(0);

        minHeap.remove(nodeArray.get(start_node));
        for (Edge e : adj.get(start_node)) {
            Node n = nodeArray.get(e.getHead());
            minHeap.remove(n);
            n.setValue(e.getWeight());
            minHeap.add(n);
        }

        while (minHeap.size() > 0) {
            Node w = minHeap.poll();

            for (Edge e : adj.get(w.getId())) {
                Node v = nodeArray.get(e.getHead());
                if (minHeap.contains(v) && (v.getValue() > w.getValue() + e.getWeight())) {
                    minHeap.remove(v);
                    v.setValue(w.getValue() + e.getWeight());
                    minHeap.add(v);
                }
            }
        }

        return nodeArray;
    }

}
