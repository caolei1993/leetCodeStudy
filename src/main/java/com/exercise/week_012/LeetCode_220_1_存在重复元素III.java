package main.java.com.exercise.week_012;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @Author cl
 * @Date 2021/4/17 10:01
 * @Version 1.0
 */
public class LeetCode_220_1_存在重复元素III {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int length = nums.length;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < length; i++) {
            Long value = (long) nums[i];
            Long floor = set.floor(value);
            Long ceil = set.ceiling(value);
            if (floor != null && value - floor <= t) {
                return true;
            }
            if (ceil != null && ceil - value <= t) {
                return true;
            }
            set.add(value);
            if (i >= k) {
                set.remove((long)nums[i - k]);
            }
        }
        return false;
    }
}
