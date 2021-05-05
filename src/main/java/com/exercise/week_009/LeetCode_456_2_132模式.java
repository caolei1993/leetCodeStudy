package main.java.com.exercise.week_009;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * @Author cl
 * @Date 2021/5/4 10:45
 * @Version 1.0
 */
public class LeetCode_456_2_132模式 {
    public boolean find132pattern(int[] nums) {
        int length = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();
        int k = Integer.MIN_VALUE;
        for (int i = length- 1; i >= 0; i--) {
            if (nums[i] < k) {
                return true;
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                k = deque.pollLast();
            }
            deque.addLast(nums[i]);
        }

        return false;
    }
}
