package com.mdfecioru.graph;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class EdgeWeightedDirectedGraphTest {

    @Test
    public void getMinDistDijkstraTest1() {
        EdgeWeightedDirectedGraph ewdg = new EdgeWeightedDirectedGraph(4);

        ewdg.addEdge(new Edge(0, 1, 1));
        ewdg.addEdge(new Edge(0, 2, 4));
        ewdg.addEdge(new Edge(1, 2, 2));
        ewdg.addEdge(new Edge(1, 3, 6));
        ewdg.addEdge(new Edge(2, 3, 3));

        ArrayList<Integer> minDist = ewdg.getMinDistDijkstra(0);

        assertEquals(0L, (long)minDist.get(0));
        assertEquals(1L, (long)minDist.get(1));
        assertEquals(3L, (long)minDist.get(2));
        assertEquals(6L, (long)minDist.get(3));
    }

    private EdgeWeightedDirectedGraph readDistDijkstraFromFile(InputStream inputStream) {

        EdgeWeightedDirectedGraph ewdg = null;

        try {
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            line = in.readLine();
            ewdg = new EdgeWeightedDirectedGraph(Integer.parseInt(line));

            line = in.readLine();
            while (line != null) {
                String[] valueStr = new String(line).trim().split("\\s+");
                int index = Integer.parseInt(valueStr[0]) - 1;
                for (int i=1; i<valueStr.length; i++) {
                    String[] pair = new String(valueStr[i]).trim().split(",");
                    ewdg.addEdge(new Edge(index, Integer.parseInt(pair[0])-1, Integer.parseInt(pair[1])));
                }
                line = in.readLine();
            }
        }
        catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }

        return ewdg;
    }

    @Test
    public void getMinDistDijkstraTest2() {
        EdgeWeightedDirectedGraph ewdg =
                readDistDijkstraFromFile(this.getClass().getResourceAsStream("../../../dijkstraData.txt"));

            ArrayList<Integer> minDist = ewdg.getMinDistDijkstra(0);

            assertEquals(2599L, (long)minDist.get(6));
            assertEquals(2610L, (long)minDist.get(36));
            assertEquals(2947L, (long)minDist.get(58));
            assertEquals(2052L, (long)minDist.get(81));
            assertEquals(2367L, (long)minDist.get(98));
            assertEquals(2399L, (long)minDist.get(114));
            assertEquals(2029L, (long)minDist.get(132));
            assertEquals(2442L, (long)minDist.get(164));
            assertEquals(2505L, (long)minDist.get(187));
            assertEquals(3068L, (long)minDist.get(196));

    }

    @Test
    public void getMinDistDijkstraOptimizedTest1() {
        EdgeWeightedDirectedGraph ewdg = new EdgeWeightedDirectedGraph(4);

        ewdg.addEdge(new Edge(0, 1, 1));
        ewdg.addEdge(new Edge(0, 2, 4));
        ewdg.addEdge(new Edge(1, 2, 2));
        ewdg.addEdge(new Edge(1, 3, 6));
        ewdg.addEdge(new Edge(2, 3, 3));

        ArrayList<Node> minDist = ewdg.getMinDistDijkstraOptimized(0);

        assertEquals(0L, (long)minDist.get(0).getValue());
        assertEquals(1L, (long)minDist.get(1).getValue());
        assertEquals(3L, (long)minDist.get(2).getValue());
        assertEquals(6L, (long)minDist.get(3).getValue());
    }

    @Test
    public void getMinDistDijkstraOptimizedTest2() {
        EdgeWeightedDirectedGraph ewdg =
                readDistDijkstraFromFile(this.getClass().getResourceAsStream("../../../dijkstraData.txt"));

        ArrayList<Node> minDist = ewdg.getMinDistDijkstraOptimized(0);

        assertEquals(2599L, (long)minDist.get(6).getValue());
        assertEquals(2610L, (long)minDist.get(36).getValue());
        assertEquals(2947L, (long)minDist.get(58).getValue());
        assertEquals(2052L, (long)minDist.get(81).getValue());
        assertEquals(2367L, (long)minDist.get(98).getValue());
        assertEquals(2399L, (long)minDist.get(114).getValue());
        assertEquals(2029L, (long)minDist.get(132).getValue());
        assertEquals(2442L, (long)minDist.get(164).getValue());
        assertEquals(2505L, (long)minDist.get(187).getValue());
        assertEquals(3068L, (long)minDist.get(196).getValue());

    }

}