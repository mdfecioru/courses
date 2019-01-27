package com.mdfecioru.algos;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class MedianMaintenanceTest {

    @Test
    public void getMedianMaintenanceTest1() {

        MedianMaintenance mm = new MedianMaintenance();
        int sumMedian = 0;

        try {
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    this.getClass().getResourceAsStream("../../../median.txt")));
            line = in.readLine();
            while (line != null) {
                int nextNumber = Integer.parseInt(line);
                int nextMM = mm.runMedianMaintenance(nextNumber);
                sumMedian += nextMM;
                line = in.readLine();
            }

            assertEquals(1213L, sumMedian % 10000);
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
    }

}