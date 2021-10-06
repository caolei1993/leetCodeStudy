package main.java.com.exercise.week_036;

import java.util.*;

/**
 * @Author cl
 * @Date 2021/10/6 10:48
 * @Version 1.0
 */
public class LeetCode_414_1_第三大的数 {
    public int thirdMax(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        if (list.size() <= 2) {
            return list.get(list.size() - 1);
        } else {
            return list.get(list.size() - 3);
        }
    }
}
