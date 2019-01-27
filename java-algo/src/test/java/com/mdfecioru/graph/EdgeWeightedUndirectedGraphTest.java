package com.mdfecioru.graph;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class EdgeWeightedUndirectedGraphTest {

    @Test
    public void getPrimMTSSimpleTest1() {
        EdgeWeightedUndirectedGraph ewug = new EdgeWeightedUndirectedGraph(4);

        ewug.addEdge(new Edge(0, 1, 1));
        ewug.addEdge(new Edge(0, 2, 3));
        ewug.addEdge(new Edge(0, 3, 4));
        ewug.addEdge(new Edge(1, 2, 2));
        ewug.addEdge(new Edge(2, 3, 5));

        long mstValue = ewug.getPrimMST();

        assertEquals(7L, mstValue);
    }

    private EdgeWeightedUndirectedGraph readPrimFromFile(InputStream inputStream) {

        EdgeWeightedUndirectedGraph ewug = null;

        try {
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            line = in.readLine();
            ewug = new EdgeWeightedUndirectedGraph(Integer.parseInt(line));

            line = in.readLine();
            while (line != null) {
                String[] valueStr = new String(line).trim().split("\\s+");
                ewug.addEdge(new Edge(Integer.parseInt(valueStr[0]) - 1,
                        Integer.parseInt(valueStr[1]) - 1,
                        Integer.parseInt(valueStr[2])));
                line = in.readLine();
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }

        return ewug;
    }

    @Test
    public void getPrimMTSFromFileTest1() {
        EdgeWeightedUndirectedGraph ewug =
                readPrimFromFile(this.getClass().getResourceAsStream("../../../edgesPrim.txt"));

        long mstValue = ewug.getPrimMSTOptimized();

        assertEquals(-3612829L, mstValue);
    }

}