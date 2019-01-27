package com.mdfecioru.algos;

import java.util.HashSet;

public class TwoSum {

    private HashSet<Long> hashSet;

    public TwoSum() {
        hashSet = new HashSet<>();
    }

    public void addElem(Long elem) {
        hashSet.add(elem);
    }

    public boolean hasTwoSum(Long targetSum) {
        for (Long elem1 : hashSet) {
            Long elem2 = targetSum - elem1;
            if ((elem1.compareTo(elem2) != 0) && (hashSet.contains(elem2))) return true;
        }
        return false;
    }

}
