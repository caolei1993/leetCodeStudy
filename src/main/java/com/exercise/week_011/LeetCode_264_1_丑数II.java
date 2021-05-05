package main.java.com.exercise.week_011;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @Author cl
 * @Date 2021/4/11 11:15
 * @Version 1.0
 * https://leetcode-cn.com/problems/ugly-number-ii/
 */
public class LeetCode_264_1_丑数II {
    public int nthUglyNumber(int n) {
        long[] factors = new long[]{2, 3, 5};
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> keep = new PriorityQueue<>();
        set.add(1L);
        keep.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long value = keep.poll();
            ugly = (int)value;
            for (long factor : factors) {
                long a = factor * value;
                if (set.add(a)) {
                    keep.add(a);
                }
            }
        }
        return ugly;
    }
}
