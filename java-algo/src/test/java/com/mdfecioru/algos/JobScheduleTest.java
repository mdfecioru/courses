package com.mdfecioru.algos;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class JobScheduleTest {

    private void readFromFile1(String fileName, JobSchedule jobSchedule) throws Exception {
        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream(fileName)));
        in.readLine(); // skip the number of elements. We will simply read until end of file.
        line = in.readLine();
        while (line != null) {
            String[] valueStr = new String(line).trim().split("\\s+");
            int weight = Integer.parseInt(valueStr[0]);
            int length = Integer.parseInt(valueStr[1]);
            jobSchedule.addJobToSchedule(weight, length);
            line = in.readLine();
        }
    }

    @Test
    public void jobScheduleSimpleTestDiff1() {

        JobSchedule jobSchedule = new JobSchedule();

        jobSchedule.addJobToSchedule(3, 5);
        jobSchedule.addJobToSchedule(1, 2);

        long weightedSum = jobSchedule.computeJobSchedule(JobSchedule.DIFF_MODE);
        assertEquals(23, weightedSum);
    }

    @Test
    public void jobScheduleSimpleTestRatio1() {

        JobSchedule jobSchedule = new JobSchedule();

        jobSchedule.addJobToSchedule(3, 5);
        jobSchedule.addJobToSchedule(1, 2);

        long weightedSum = jobSchedule.computeJobSchedule(JobSchedule.RATIO_MODE);
        assertEquals(22, weightedSum);
    }

    @Test
    public void jobScheduleTestFileDiff1() {

        JobSchedule jobSchedule = new JobSchedule();

        try {
            readFromFile1("../../../jobs.txt", jobSchedule);
            long weightedSum = jobSchedule.computeJobSchedule(JobSchedule.DIFF_MODE);
            assertEquals(69119377652L, weightedSum);
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
    }

    @Test
    public void jobScheduleTestFileRatio1() {

        JobSchedule jobSchedule = new JobSchedule();

        try {
            readFromFile1("../../../jobs.txt", jobSchedule);
            long weightedSum = jobSchedule.computeJobSchedule(JobSchedule.RATIO_MODE);
            assertEquals(67311454237L, weightedSum);
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
    }
}