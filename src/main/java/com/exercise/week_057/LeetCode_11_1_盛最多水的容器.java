package main.java.com.exercise.week_057;

/**
 * @author cl
 * @version 1.0
 * @description： TODO
 * @date 2024/1/8 19:04
 */
public class LeetCode_11_1_盛最多水的容器 {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int sum = 0;
        while (l < r) {
            sum = Math.max(sum, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return sum;
    }
}
