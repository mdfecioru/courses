package com.mdfecioru.algos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class JobSchedule {

    public static final int DIFF_MODE = 0;
    public static final int RATIO_MODE = 1;
    private static ArrayList<Comparator<Job>> comparatorArray = null;
    private ArrayList<Job> jobList;

    private class Job implements Comparable<Job> {
        private int w;
        private int l;

        Job(int weight, int length) {
            this.w = weight;
            this.l = length;
        }

        @Override
        public int compareTo(Job o) {
            int thisScore = this.w - this.l;
            int oScore = o.w - o.l;
            if (thisScore > oScore) return -1;
            if (thisScore < oScore) return 1;
            if (this.w > o.w) return -1;
            if (this.w < o.w) return 1;
            return 0;
        }
    }

    public JobSchedule() {
        jobList = new ArrayList<>();
        comparatorArray = new ArrayList<>();

        comparatorArray.add((o1, o2) -> {
            int o1Score = o1.w - o1.l;
            int o2Score = o2.w - o2.l;
            if (o1Score > o2Score) return -1;
            if (o1Score < o2Score) return 1;
            if (o1.w > o2.w) return -1;
            if (o1.w < o2.w) return 1;
            return 0;
        });

        comparatorArray.add((o1, o2) -> {
            double o1Score = (double) o1.w / (double) o1.l;
            double o2Score = (double) o2.w / (double) o2.l;
            if (o1Score > o2Score) return -1;
            if (o1Score < o2Score) return 1;
            return 0;
        });
    }

    public void addJobToSchedule(int weight, int length) {
        jobList.add(new Job(weight, length));
    }

    public long computeJobSchedule(int runMode) {
        long completionTime = 0;
        long weightedSum = 0;

        Collections.sort(jobList, comparatorArray.get(runMode));

        for (Job job : jobList) {
            completionTime += (long) job.l;
            weightedSum += completionTime * (long) job.w;
        }

        return weightedSum;
    }
}
