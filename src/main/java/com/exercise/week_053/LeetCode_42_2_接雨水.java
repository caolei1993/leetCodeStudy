package main.java.com.exercise.week_053;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author cl
 * @version 1.0
 * @description： 单调栈解法
 * @date 2023/12/6 20:03
 */
public class LeetCode_42_2_接雨水 {
    public int trap(int[] height) {
        int n = height.length;
        Deque<Integer> deque = new LinkedList<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && height[i] > height[deque.peek()]) {
                int top = deque.pop();
                if (deque.isEmpty()) {
                    break;
                }
                int left = deque.peek();
                int wide = i - left - 1;
                int high = Math.min(height[left], height[i]) - height[top];
                sum += wide * high;
            }
            deque.push(i);
        }
        return sum;
    }
}
