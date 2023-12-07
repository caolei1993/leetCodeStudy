package main.java.com.exercise.week_053;

/**
 * @author cl
 * @version 1.0
 * @description： 双指针形式优化空间复杂度
 * @date 2023/12/6 20:15
 */
public class LeetCode_42_3_接雨水 {
    public int trap(int[] height) {
        int n = height.length;
        int leftMax = 0, left = 0;
        int rightMax = 0, right = n - 1;
        int sum = 0;
        while (left < right) {
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);
            if (height[left] < height[right]) {
                sum += leftMax - height[left];
                left++;
            } else {
                sum += rightMax - height[right];
                right--;
            }
        }
        return sum;
    }
}
