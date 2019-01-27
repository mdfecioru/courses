package com.mdfecioru.algos;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class TwoSumTest {

    @Test
    public void twoSumTest1() {
        TwoSum twoSum = new TwoSum();

        twoSum.addElem(2L);
        twoSum.addElem(3L);
        twoSum.addElem(4L);
        twoSum.addElem(4L);
        twoSum.addElem(9L);
        twoSum.addElem(4L);
        twoSum.addElem(10L);

        assertTrue(twoSum.hasTwoSum(7L));
        assertFalse(twoSum.hasTwoSum(8L));
    }

    @Test
    public void twoSumTestFile1() {

        TwoSum twoSum = new TwoSum();

        try {
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    this.getClass().getResourceAsStream("../../../2sum.txt")));
            line = in.readLine();
            while (line != null) {
                Long nextNumber = Long.parseLong(line);
                twoSum.addElem(nextNumber);
                line = in.readLine();
            }

            int matches = 0;
            for (int i = -10000; i <= 10000; i++) {
                if (twoSum.hasTwoSum(new Long(i))) matches++;
            }

            assertEquals(1, matches);

        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
    }

}