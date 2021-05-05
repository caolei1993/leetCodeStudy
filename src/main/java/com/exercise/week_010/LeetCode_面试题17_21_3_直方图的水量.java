package main.java.com.exercise.week_010;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author cl
 * @Date 2021/4/5 21:37
 * @Version 1.0
 */
public class LeetCode_面试题17_21_3_直方图的水量 {
    public int trap(int[] height) {
        int length = height.length;
        int ans = 0;
        if (length < 3) {
            return ans;
        }
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < length ; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                // 蓄水的宽度
                int curWidth = i - left - 1;
                // 蓄水的高度
                int curHeight = Math.min(height[left], height[i]) - height[top];
                ans += curWidth * curHeight;
            }
            stack.push(i);
        }
        return ans;
    }
}
